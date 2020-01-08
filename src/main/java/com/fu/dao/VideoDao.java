package com.fu.dao;

import com.fu.bean.Video;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/1/7 22:38
 */
public interface VideoDao {
    /**
     * 查询视频列表，分页
     * @return  返回查询到的数据
     */
     List<Video> getVideoList();

    /**
     *  添加视频信息
     * @param video 视频信息
     * @return 成功返回 1
     */
    int addVideo(Video video);
}
