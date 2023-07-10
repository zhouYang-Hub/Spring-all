package com.zhouyang.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author user
 */
@Data
@AllArgsConstructor
@ToString
public class Student {
    private long termId;//学期id
    private long classId;//班级id
    private long studentId;//班级id
    private String name;//学生名称
}