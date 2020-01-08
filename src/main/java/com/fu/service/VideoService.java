package com.fu.service;

import com.fu.bean.Video;

import java.util.List;

/**
 * 视频管理业务层
 * @Author Administrator
 * @Date 2020/1/7 22:31
 */
public interface VideoService {
    /**
     * 查询视频列表，分页
     * @param page  当前页数
     * @param limit 每页数据条数
     * @return  返回查询到的数据
     */
    List<Video> getVideoList(Integer page, Integer limit);

    /**
     *  添加视频信息
     * @param video 视频信息
     * @return 成功返回 1
     */
    int addVideo(Video video);

    /**
     * 删除、批量删除
     * @param idArr 视频的id
     * @return  返回操作成功的条数
     */
    int delete(String[] idArr);
}
