package com.fu.service.impl;

import com.fu.bean.Course;
import com.fu.dao.CourseDao;
import com.fu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/1/8 14:46
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> getCourseList() {
        return courseDao.getCourseList();
    }
}
