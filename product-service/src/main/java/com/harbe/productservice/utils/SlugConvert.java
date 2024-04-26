package com.harbe.productservice.utils;

public class SlugConvert {
    public static String convert(String str) {
        // Thay thế dấu gạch ngang trước và sau khoảng trắng bằng khoảng trắng
        str = str.replaceAll("\\s*\\-\\s*", " ");

        // Thay thế khoảng trắng bằng dấu gạch ngang
        str = str.replaceAll("\\s+", "-");

        return str;
    }
}
