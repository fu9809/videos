package com.fu.controller;

import com.fu.bean.Speaker;
import com.fu.comment.Msg;
import com.fu.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 主讲人控制器
 * @Author Administrator
 * @Date 2020/1/8 13:56
 */
@Controller
@RequestMapping("/speaker")
public class SpeakerController {
    @Autowired
    private SpeakerService speakerService;

    @RequestMapping("speakerList.do")
    @ResponseBody
    public Msg speakerList() {
        List<Speaker> speakerList = speakerService.getSpeakerList();
        return new Msg(0, speakerList);
    }
}
