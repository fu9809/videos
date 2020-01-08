package com.fu.bean;

import lombok.Data;

/**
 * 视频信息
 * @Author Administrator
 * @Date 2020/1/7 15:53
 */
@Data
public class Video {
    private Integer id;         // id
    private String title;       // 名称
    private String detail;      // 详情
    private Integer time;       // 时长
    private Integer speakerId;  // 讲师id
    private Integer courseId;   // 课程编号
    private String videoUrl;    // 视频网址
    private String imageUrl;    // 图片网页
    private Integer playNum;    // 播放次数

    private Speaker speaker;
    private Course course;
}
