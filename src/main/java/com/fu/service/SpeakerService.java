package com.fu.service;

import com.fu.bean.Speaker;

import java.util.List;

/**
 * 主讲人业务类
 * @Author Administrator
 * @Date 2020/1/8 13:57
 */
public interface SpeakerService {

    /**
     *  查询所有讲师信息
     * @return 返回查询的信息
     */
    List<Speaker> getSpeakerList();
}
