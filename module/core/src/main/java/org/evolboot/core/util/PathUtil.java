package org.evolboot.core.util;

import org.apache.commons.io.FilenameUtils;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class PathUtil {

    public static final String PATH_SEPARATOR = "/";

    public static final String ROOT_PATH = PATH_SEPARATOR;

    public static String normalize(String path) {
        return Optional.of(path)
                .map(s -> FilenameUtils.normalize(s, true))
                .map(s -> s.replaceAll("/+", "/"))
                .map(s -> s.startsWith("/") ? s : "/" + s)
                .orElseThrow();
    }

    public static String concat(String first, String... more) {
        return normalize(concat0(first, more));
    }

    private static String concat0(String first, String... more) {
        String path = Stream.concat(Stream.of(first), Stream.of(more))
                .map(Object::toString)
                .reduce((a, b) -> String.join("/", a, b))
                .orElseThrow();
        return FilenameUtils.normalize(path, true);
    }

    public static String removePrefixSeparator(String path) {
        return Optional.of(path).map(PathUtil::normalize)
                .map(s -> s.startsWith("/") ? s.substring(1) : s)
                .orElseThrow();
    }

    public static void main(String[] args) {
        System.out.println(normalize("http://abc/a/a///a"));
    }
}
