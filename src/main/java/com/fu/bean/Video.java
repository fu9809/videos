package com.fu.bean;

import lombok.Data;

/**
 * 视频信息
 * @Author Administrator
 * @Date 2020/1/7 15:53
 */
@Data
public class Video {
    private Integer id;
    private String title;
    private String detail;
    private Integer time;
    private Integer speakerId;
    private Integer courseId;
    private String videoUrl;
    private String imageUrl;

    private Speaker speaker;
    private Course course;
}
