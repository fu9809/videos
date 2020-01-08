package com.fu.dao;

import com.fu.bean.Speaker;

import java.util.List;

/**
 * 主讲人数据操纵
 * @Author Administrator
 * @Date 2020/1/8 13:59
 */
public interface SpeakerDao {
    /**
     *  查询所有讲师信息
     * @return 返回查询的信息
     */
    List<Speaker> getSpeakerList();
}
