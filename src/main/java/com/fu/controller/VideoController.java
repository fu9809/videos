package com.fu.controller;

import com.fu.bean.Video;
import com.fu.comment.Msg;
import com.fu.service.VideoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list.do")
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

    @PostMapping("/addVideo.do")
    @ResponseBody
    public Msg addVideo(Video video) {
        int num = videoService.addVideo(video);
        if (num > 0) {
            return new Msg(0, num);
        } else {
            return new Msg(1, "瞎写数据，想让老子数据库炸掉么！！！");
        }
    }

    @DeleteMapping("/delete.do/{ids}")
    @ResponseBody
    public Msg delete(@PathVariable("ids") String ids) {
        String[] idArr = ids.split(",");
        int num = videoService.delete(idArr);
        if (num > 0) {
            return new Msg(0, num);
        } else {
            return new Msg(1, "你传入的数据有毛病吧，一条都没删除成功");
        }
    }

    @GetMapping("/getVideoById.do/{id}")
    @ResponseBody
    public Msg getVideoById(@PathVariable("id") String id) {
        Video video = videoService.getVideoById(id);
        if (video != null) {
            return new Msg(0, video);
        } else {
            return new Msg(1, "出错啦，好像没有该条信息");
        }
    }

    @PutMapping("/updateVideo.do")
    @ResponseBody
    public Msg updateVideo(Video video) {
        int num = videoService.updateVideo(video);
        if (num > 0) {
            return new Msg(0, num);
        } else {
            return new Msg(1, "你确定你输入的东西合法？？？");
        }
    }
}
