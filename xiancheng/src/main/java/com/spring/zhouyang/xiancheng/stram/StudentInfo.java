package com.spring.zhouyang.xiancheng.stram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * StudentInfo:
 *
 * @author zhouYang
 * @date 2024/02/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfo {
    //姓名
    private String name;
    //年龄
    private Integer age;
    // 性别 true 男 false 女
    private Boolean gender;
    // 身高
    private double height;
    // 生日
    private LocalDateTime birthday;
}