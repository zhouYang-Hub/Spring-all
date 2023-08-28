package org.example.redisCache;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author user
 */
public class RegexTest {
    public static void main(String[] args) {
        String[] testStrings = {
                "HelloWorld",     // 只包含字母，应匹配
                "你好世界",         // 只包含汉字，应匹配
                "Hello 你好",      // 包含空格，不应匹配
                "123你好abc",      // 包含数字，不应匹配
                "Hello 世界123"    // 包含字母、汉字和数字，不应匹配
        };
        String regex = "^[\u4E00-\u9FA5a-zA-Z]+$"; // 匹配汉字和字母
        Pattern pattern = Pattern.compile(regex);
        for (String testString : testStrings) {
            Matcher matcher = pattern.matcher(testString);
            if (matcher.matches()) {
                System.out.println("'" + testString + "' 匹配正则表达式");
            } else {
                System.out.println("'" + testString + "' 不匹配正则表达式");
            }
        }
    }
}


