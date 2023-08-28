package org.example.redisCache;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTest {
    public static void main(String[] args) {
        String[] testStrings = {
                "2023/08/24",     // 符合条件
                "2023/8/24",      // 符合条件
                "2023-08-24",     // 不符合条件，使用了不正确的分隔符
                "23/08/24",       // 不符合条件，年份过短
                "2023/13/24",     // 不符合条件，月份超出范围
                "2023/08/32",     // 不符合条件，日期超出范围
                "2023-08/24",     // 不符合条件，使用了不正确的分隔符
                "abc/def/ghi",    // 不符合条件，非数字
        };

//        String regex = "^(?:(?:(?:19|20)\\d\\d)/(?:(?:0[1-9])|(?:1[0-2]))/(?:0[1-9]|1\\d|2[0-9]|3[0-1]))$";
        String regex = "^(?:(?:(?:19|20)\\d\\d)/(?:(?:0?[1-9])|(?:1[0-2]))/(?:0?[1-9]|1\\d|2[0-9]|3[0-1]))$";

        Pattern pattern = Pattern.compile(regex);

        for (String testString : testStrings) {
            Matcher matcher = pattern.matcher(testString);
            if (matcher.matches()) {
                System.out.println("'" + testString + "' 符合条件");
            } else {
                System.out.println("'" + testString + "' 不符合条件");
            }
        }
//        System.out.println(regexDate("2022/08/12"));

//        String aa = "2022/8/01";
//        String regex = "^(?:(?:(?:19|20)\\d\\d)/(?:(?:0[1-9])|(?:1[0-2]))/(?:0[1-9]|1\\d|2[0-9]|3[0-1]))$";
////        String regex = "^(?:(?:(?:19|20)\\d\\d)/(?:(?:0[1-9])|(?:1[0-2]))/(?:0[1-9]|1\\d|2[0-9]|3[0-1]))$";
//
//        if (aa.matches(regex)) {
//            System.out.println("ok");
//        } else {
//            System.out.println("error");
//        }

    }

    static boolean regexDate(String str) {
        String regex = "^(?:(?:(?:19|20)\\d\\d)/(?:(?:0[1-9])|(?:1[0-2]))/(?:0[1-9]|1\\d|2[0-9]|3[0-1]))$";
//        String regex = "^(?:(?:(?:19|20)\\d\\d)/(?:(?:0[1-9])|(?:1[0-2]))/(?:0[1-9]|1\\d|2[0-9]|3[0-1]))$";

        boolean fla = false;
        if (str.matches(regex)) {
            fla = true;
        }
        return fla;
    }
}
