package io.github.mirrormingzz.file;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java8 读取文件
 *
 * @author Mireal
 * @version V1.0
 */
public class ReadAllFile {
    public static void main(String[] args) throws IOException {
        List<Path> list = Files.list(Paths.get("D:\\Mirror\\Coding\\python\\nlp")).collect(Collectors.toList());
        list.forEach(System.out::println);

//        Files.walkFileTree(Paths.get("D:\\Mirror\\Coding\\xmpp\\Openfire"),)

        List<Path> collect = Files
                .walk(Paths.get("D:\\Mirror\\Coding\\python\\nlp"), FileVisitOption.FOLLOW_LINKS)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println();
        collect.forEach(c -> System.out.println(c.getFileName()));

        //列出有所有文件夹
        try (Stream<Path> walk = Files.walk(Paths.get("D:\\Mirror\\Coding\\python\\nlp"))) {

            List<String> result = walk.filter(Files::isDirectory)
                    .map(Path::toString).collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //列出所有py结尾的
        try (Stream<Path> walk = Files.walk(Paths.get("D:\\Mirror\\Coding\\python\\nlp"))) {

            List<String> result = walk.map(Path::toString)
                    .filter(f -> f.endsWith(".py"))
                    .collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
