package com.fu.service.impl;

import com.fu.bean.Video;
import com.fu.dao.VideoDao;
import com.fu.service.VideoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 视频管理业务层实现类
 * @Author Administrator
 * @Date 2020/1/7 22:31
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;

    @Override
    public List<Video> getVideoList(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Video> videoList = videoDao.getVideoList();
        return videoList;
    }
}
