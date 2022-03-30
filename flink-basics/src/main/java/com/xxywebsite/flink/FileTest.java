package com.xxywebsite.flink;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuxiaoyin
 * @since 2022/3/16
 **/
public class FileTest {
    public static void main(String[] args) throws IOException {
        String path = String.format("%s/%s/checkpoint", System.getProperty("user.dir"), "flink-basics");
        // 找到当前目录下最新的目录
        System.out.println(findCheckpointPath(path).toString());

        // 找到最新目录下名字含有"chk-"的目录
    }

    public static Path findCheckpointPath(String path) throws IOException {

        return Files.list(Paths.get(path))
                .filter(f -> Files.isDirectory(f))
                .max(Comparator.comparingInt(p -> {
                    try {
                        Files
                                .list(p)
                                .map(p2 -> {
                                    String regex = "chk-(\\d{1,})";
                                    Pattern pattern = Pattern.compile(regex);

                                    String fileName = p2.getFileName().toString();

                                    Matcher matcher = pattern.matcher(fileName);

                                    if (matcher.find()) {
                                        int number = Integer.parseInt(matcher.group(1));
                                        return number;
                                    }
                                    return -1;
                                })
                                .max(Integer::compareTo);




                    } catch (IOException e) {
                    }
                    return -1;
                }))
                .get();
    }
}
