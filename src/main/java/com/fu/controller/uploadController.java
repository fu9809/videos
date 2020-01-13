package com.fu.controller;

import com.fu.comment.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传控制器
 * @Author Administrator
 * @Date 2020/1/12 17:11
 */
@Controller
@RequestMapping("/upload")
public class uploadController {

    @PostMapping("/headImg.do")
    @ResponseBody
    public Msg headImg(@RequestParam MultipartFile file, HttpSession session) {
        String path = "static/upload/";
        String dir = session.getServletContext().getRealPath("") + path;
        String filename = "";
        try {
            filename = file.getOriginalFilename();
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            File newFile = new File(dir, filename);
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new Msg(1, "文件上传时出错了");
        }
        String imgUrl = "/videos/" + path + filename;
        return new Msg(0, imgUrl);
    }
}
