package com.fu.service;

import com.fu.bean.Course;

import java.util.List;

/**
 * 课程业务层
 * @Author Administrator
 * @Date 2020/1/8 14:41
 */
public interface CourseService {
    /**
     *  查询所有的课程
     * @return 返回查询到的课程
     */
    List<Course> getCourseList();
}
