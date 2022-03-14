package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        var path = "/Users/vahid/Books_/فارسی/ترجمه-دستور-زبان\u200Cشناسی";
        renamePersian(path);
        System.out.println("--------------------------------------");
        listDir(path);
    }

    private static void renameEnglish(String path) throws IOException {
        Pattern pattern = Pattern.compile("(\\[.*\\])(.*)(\\[.*\\])");
        Pattern pattern2 = Pattern.compile("(.*)(\\[.*\\])");

        rename(path, s -> {
            var fname = getFname(s);
            if (fname.isBlank())
                return null;
            var matcher = pattern.matcher(fname);
            if (matcher.find()) {
                return new Book(matcher.group(1).trim().replaceAll("[\\[\\]]",""),
                        parseAuthors(matcher.group(3).trim().replaceAll("[\\[\\]]","")),
                        matcher.group(2).trim(),
                        getExt(s));
            } else {
                var matcher2 = pattern2.matcher(fname);
                if (matcher2.find()) {
                    return new Book(null,
                            parseAuthors(matcher2.group(2).trim().replaceAll("[\\[\\]]","")),
                            matcher2.group(1).trim(),
                            getExt(s));
                }
            }
            return null;
        });
    }

    private static void renamePersian(String path) throws IOException {
        rename(path, s -> {
            var fname = getFname(s);
            if (fname.isBlank())
                return null;
            var parts = Arrays.stream(fname.split("؛"))
                    .filter(x -> !x.isBlank())
                    .map(String::trim)
                    .toArray(String[]::new);

            return new Book(null, parts.length == 1 ? null : parseAuthors(parts[1]), parts[0], getExt(s));
        });
    }

    static void rename(String path, Function<String, Book> parseSource) throws IOException {
        Files.list(Path.of(path)).forEach(f -> {
                    var fileName = f.getFileName().toString();
                    try {
                        rename(path, fileName, parseSource);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    static void rename(String path, String name, Function<String, Book> parseSource) throws IOException {
        try {
            Files.move(Paths.get(path + "/" + name), Paths.get(path + "/" + parseSource.apply(name).asString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listDir(String path) throws IOException {
        Files.list(Path.of(path)).forEach(f -> {
                    var fileName = f.getFileName().toString();
                    System.out.println(parse(fileName));
                }
        );
    }

    static Book parse(String fileName) {
        var ext = getExt(fileName);
        var fname = getFname(fileName);

        var parts = Arrays.stream(fname.split("[\\[\\]]+"))
                .filter(x -> !x.isBlank())
                .map(String::trim)
                .collect(Collectors.toList());

        return switch (parts.size()) {
            case 1 -> new Book(null, null, parts.get(0), ext);
            case 2 -> new Book(null, parseAuthors(parts.get(0)), parts.get(1), ext);
            case 3 -> new Book(parts.get(0), parseAuthors(parts.get(1)), parts.get(2), ext);
            default -> null;
        };
    }

    private static String getFname(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    private static String getExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    static List<String> parseAuthors(String authors) {
        return Arrays.stream(authors.split("[,،\\[\\]]")).map(String::trim).collect(Collectors.toList());
    }

    record Book(String year, List<String> authors, String name, String ext) {
        public String asString() {
            var builder = new StringBuilder();
            if (year != null && !year.isBlank()) {
                builder.append("[").append(year).append("]");
            }
            if (authors != null && !authors.isEmpty()) {
                builder.append("[").append(String.join(",", authors)).append("]");
            }
            if (name != null && !name.isBlank()) {
                builder.append("[").append(name).append("]");
            }
            if (ext != null && !ext.isBlank()) {
                builder.append(".").append(ext);
            }
            return builder.toString();
        }
    }
}
