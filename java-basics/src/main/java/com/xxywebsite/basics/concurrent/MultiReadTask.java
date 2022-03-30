package com.xxywebsite.basics.concurrent;

import lombok.Data;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 有一个List<String>, 多个线程并发读取这个list, 如果发现"前缀是自己"，则读取这一条，否则交由其它线程处理
 * a:{"name": "a", "taskId": "abc"}
 * a:{"name": "a", "taskId": "abc"}
 * b:{"name": "b", "taskId": "abc"}
 * a:{"name": "a", "taskId": "abc"}
 * a:{"name": "a", "taskId": "abc"}
 *
 * @author xuxiaoyin
 * @since 2022/3/29
 **/
@Data
public class MultiReadTask extends Thread {
    private String taskName;
    private List<String> dataList;
    private AtomicInteger currentIndex;

    public MultiReadTask(String name, List<String> dataList, AtomicInteger currentIndex) {
        this.taskName = name;
        this.dataList = dataList;
        this.currentIndex = currentIndex;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (currentIndex.intValue() < dataList.size()) {
            synchronized (MultiReadTask.class) {
                    String currentData = dataList.get(currentIndex.intValue());
                    String[] strArr = divideStringBy(currentData, ':');
                    String name = strArr[0];
                    String data = strArr[1];
                    if (this.getTaskName().equals(name)) {
                        System.out.println(String.format("名称：%s；数据:%s", name, data));
                        currentIndex.incrementAndGet();
                        if (currentIndex.intValue() >= dataList.size()) {
                            MultiReadTask.class.notifyAll();
                            break;
                        }
                    } else { // 交给其它线程处理
                        MultiReadTask.class.notifyAll();
                        MultiReadTask.class.wait();
                    }
            }
        }
    }

    private String[] divideStringBy(String string, char delimiter) {
        int index = 0;
        while (string.charAt(index) != delimiter) {
            index++;
        }

        if (index >= string.length()) {
            return null;
        } else {
            return new String[]{string.substring(0, index), string.substring(index + 1)};
        }
    }

    public static void main(String[] args) {
        List<String> dataList = new ArrayList<>(Arrays.asList(
                "a:{'name': 'a', 'taskId': 'abc'}",
                "a:{'name': 'a', 'taskId': 'abc'}",
                "a:{'name': 'a', 'taskId': 'abc'}",
                "a:{'name': 'a', 'taskId': 'abc'}",
                "a:{'name': 'a', 'taskId': 'abc'}",
                "a:{'name': 'a', 'taskId': 'abc'}",
                "a:{'name': 'a', 'taskId': 'abc'}",
                "b:{'name': 'b', 'taskId': 'abc'}",
                "a:{'name': 'a', 'taskId': 'abc'}",
                "c:{'name': 'c', 'taskId': 'abc'}"
        ));

        AtomicInteger currentIndex = new AtomicInteger(0);
        MultiReadTask aTask = new MultiReadTask("a", dataList, currentIndex);
        MultiReadTask bTask = new MultiReadTask("b", dataList, currentIndex);
        MultiReadTask cTask = new MultiReadTask("c", dataList, currentIndex);

        aTask.start();
        bTask.start();
        cTask.start();;
    }
}
