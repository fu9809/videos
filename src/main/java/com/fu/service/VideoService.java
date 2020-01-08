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
}
