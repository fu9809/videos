package com.fu.bean;

import lombok.Data;

/**
 * 课程信息
 * @Author Administrator
 * @Date 2020/1/7 15:48
 */
@Data
public class Course {
    private Integer id;
    private String courseTitle;     // 课程名称
    private String courseDesc;      // 课程说明
    private Integer subjectId;      // 学科id

    private Subject subject;
}
