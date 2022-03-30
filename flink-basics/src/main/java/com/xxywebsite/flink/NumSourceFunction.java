package com.xxywebsite.flink;

import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Iterator;

/**
 * @author xuxiaoyin
 * @since 2022/3/16
 **/
public class NumSourceFunction implements SourceFunction<Integer>, CheckpointedFunction {
    private transient ListState<Integer> checkpointedState;
    private int num = 0;

    @Override
    public void snapshotState(FunctionSnapshotContext context) throws Exception {
        checkpointedState.clear();
        checkpointedState.add(num);
    }

    @Override
    public void initializeState(FunctionInitializationContext context) throws Exception {
        ListStateDescriptor<Integer> descriptor =
                new ListStateDescriptor<>(
                        "buffered-elements",
                        TypeInformation.of(new TypeHint<Integer>() {
                        }));

        checkpointedState = context.getOperatorStateStore().getListState(descriptor);

        if (context.isRestored()) {
            Iterator<Integer> iterator = checkpointedState.get().iterator();
            if (iterator.hasNext()) {
                num = iterator.next();
            }
        }

    }


    @Override
    public void run(SourceContext<Integer> ctx) throws Exception {
        Object lock = ctx.getCheckpointLock();
        while (true) {
            ctx.collect(num++);
            Thread.sleep(2000);
        }
    }

    @Override
    public void cancel() {

    }
}
