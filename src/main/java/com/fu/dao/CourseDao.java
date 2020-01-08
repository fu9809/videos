package com.fu.dao;

import com.fu.bean.Course;

import java.util.List;

/**
 * 课程信息数据访问层
 * @Author Administrator
 * @Date 2020/1/8 14:45
 */
public interface CourseDao {
    /**
     *  查询所有的课程
     * @return 返回查询到的课程
     */
    List<Course> getCourseList();
}
