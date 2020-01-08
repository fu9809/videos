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

    @Override
    public int addVideo(Video video) {
        int i = -1;
        try {
            i = videoDao.addVideo(video);
        } catch (Exception e) {
            throw new RuntimeException("瞎写数据，想让老子数据库炸掉么！！！");
        }
        return i;
    }

    @Override
    public int delete(String[] idArr) {
        int num = -1;
        try {
           num = videoDao.delete(idArr);
        } catch (Exception e) {
            throw new RuntimeException("你传入的数据有毛病吧，一条都没删除成功");
        }
        return num;
    }
}
