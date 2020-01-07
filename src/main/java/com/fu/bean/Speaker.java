package com.fu.bean;

import lombok.Data;

/**
 * 讲师信息
 * @Author Administrator
 * @Date 2020/1/7 15:50
 */
@Data
public class Speaker {
    private Integer id;
    private String speakerName;
    private String speakerDesc;
    private String speakerJob;
    private String headImgUrl;

}
