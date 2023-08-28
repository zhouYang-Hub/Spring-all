package org.example.redisCache;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author user
 */
public class Main {
    public static void main(String[] args) {
        String input = "Hello, 你好，12<>```";
        String pattern = "^[\\p{L}\\p{N}\\p{P}\\p{Z}]{1,15}$"; // 匹配汉字、英文、数字、普通符号，不超过15位

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);

        if (matcher.matches()) {
            System.out.println("ok");
        } else {
            System.out.println("error");
        }
    }
}
