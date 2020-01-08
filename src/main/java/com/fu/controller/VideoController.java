package com.fu.controller;

import com.fu.bean.Video;
import com.fu.service.VideoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频控制器
 * @Author Administrator
 * @Date 2020/1/7 22:30
 */
@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("/list.do")
    @ResponseBody
    public Map<String, Object> videoList(Integer page, Integer limit) {
        List<Video> videoList = videoService.getVideoList(page, limit);
        PageInfo<Video> pageInfo = PageInfo.of(videoList);
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", "0");
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", videoList);
        return map;
    }
}
