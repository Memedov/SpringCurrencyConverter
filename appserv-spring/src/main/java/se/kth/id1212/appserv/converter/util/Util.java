package se.kth.id1212.appserv.converter.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Util {
    private Util() {
    }

    public static String toString(Object objToRepresent) {
        StringBuilder builder =
                new StringBuilder(objToRepresent.getClass().getName());
        builder.append("[");
        builder.append(
                Arrays.stream(objToRepresent.getClass().getDeclaredFields())
                        .map((field) -> {
                            field.setAccessible(true);
                            try {
                                return field.getName() + ":" +
                                        field.get(objToRepresent);
                            } catch (Exception e) {
                                return field.getName() + ":" + e.getMessage();
                            }
                        }).collect(Collectors.joining(", ")));
        builder.append("]");
        return builder.toString();
    }
}
