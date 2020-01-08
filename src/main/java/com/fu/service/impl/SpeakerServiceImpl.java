package com.fu.service.impl;

import com.fu.bean.Speaker;
import com.fu.dao.SpeakerDao;
import com.fu.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主讲人业务层实现类
 * @Author Administrator
 * @Date 2020/1/8 13:57
 */
@Service
public class SpeakerServiceImpl implements SpeakerService {
    @Autowired
    private SpeakerDao speakerDao;


    @Override
    public List<Speaker> getSpeakerList() {
        return speakerDao.getSpeakerList();
    }
}
