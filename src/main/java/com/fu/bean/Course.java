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
    private String courseTitle;
    private String courseDesc;
    private Integer subjectId;

    private Subject subject;
}
