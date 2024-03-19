package com.myth.utils;

import io.quarkus.panache.common.Sort;

public class APIUtils {
    public static Sort.Direction getSortDirection(String value) {
        if (value.equalsIgnoreCase("desc")) {
            return Sort.Direction.Descending;
        }
        return Sort.Direction.Ascending;
    }
}
