package com.fu.controller;

import com.fu.bean.Course;
import com.fu.comment.Msg;
import com.fu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 课程控制器
 * @Author Administrator
 * @Date 2020/1/8 14:39
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/courseList.do")
    @ResponseBody
    public Msg courseList() {
        List<Course> courseList = courseService.getCourseList();
        return new Msg(0, courseList);
    }
}
