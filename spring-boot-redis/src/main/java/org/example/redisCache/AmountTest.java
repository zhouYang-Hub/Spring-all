package org.example.redisCache;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmountTest {
    public static void main(String[] args) {
        String[] testStrings = {
                "10",         // 符合条件
                "999999.99",  // 符合条件
                "1000000",    // 超过最大值，不符合条件
                "9.5",        // 符合条件
                "9.55",       // 符合条件
                "9.555",      // 超过最大值，不符合条件
                "0.5",        // 符合条件
                "0.05",       // 符合条件
                "abc",        // 非数字，不符合条件
                "-5",         // 负数，不符合条件
        };

        String regex = "^(?:\\d{1,6}(\\.\\d{1,2})?)$"; // 匹配金额不超过999999.99的数字

        Pattern pattern = Pattern.compile(regex);

        for (String testString : testStrings) {
            Matcher matcher = pattern.matcher(testString);
            if (matcher.matches()) {
                System.out.println("'" + testString + "' 符合条件");
            } else {
                System.out.println("'" + testString + "' 不符合条件");
            }
        }
    }
}
