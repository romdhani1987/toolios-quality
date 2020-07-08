package fr.romdhani.aymen.toolios.utils;

public class StringUtils {
    public static boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static boolean isNull(String text) {
        return text == null;
    }

    public static boolean isEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

}
