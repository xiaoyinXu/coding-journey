package com.xxywebsite.flink;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author xuxiaoyin
 * @since 2022/3/16
 **/
public class StreamUtil {
    public static StreamExecutionEnvironment getStreamExecutionEnvironment(String checkpointPath) throws IOException {
        return getStreamExecutionEnvironment(checkpointPath, new Configuration());
    }
    public static StreamExecutionEnvironment getStreamExecutionEnvironment(String checkpointPath, Configuration configuration) throws IOException {
        if (Files.exists(Paths.get(checkpointPath))) {
            String chkPath = Optional.ofNullable(findCheckpointPath(checkpointPath)).orElse(null);
            if (StringUtils.isNotBlank(checkpointPath)) {
                configuration.setString("execution.savepoint.path", "file://" + chkPath);
            }
        }

        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment(configuration);

        env.getCheckpointConfig().setCheckpointStorage("file://" + checkpointPath);
        return env;
    }

    public static String findCheckpointPath(String path) throws IOException {
        return Files
                .list(Paths.get(path))
                .filter(p1 -> {
                    try {
                        return Files
                                .list(p1)
                                .anyMatch(p2 -> Files.isDirectory(p2) && p2.getFileName().toString().startsWith("chk-"));
                    } catch (IOException e) {
                        return false;
                    }
                })
                .max(Comparator.comparingLong(p -> p.toFile().lastModified()))
                .map(p1 -> {
                    // 取到最新的chk-

                    try {
                        String chkPath = Files
                                .list(p1)
                                .filter(p2 -> Files.isDirectory(p2) && p2.getFileName().toString().startsWith("chk-"))
                                .max(Comparator.comparingInt(p2 -> {
                                    String regex = "chk-(\\d{1,})";
                                    Pattern pattern = Pattern.compile(regex);

                                    Matcher matcher = pattern.matcher(p2.getFileName().toString());

                                    if (matcher.find()) {
                                        return Integer.parseInt(matcher.group(1));
                                    }
                                    return -1;
                                }))
                                .map(p3 -> p3.getFileName().toString())
                                .orElse(null);
                        if (StringUtils.isNotBlank(chkPath)) {
                            return p1.toString() + "/" + chkPath;
                        }
                    } catch (IOException e) {
                    }
                    return null;
                })
                .orElse(null);

    }
}
