package com.c4demo.entity.nodelist;

import lombok.Data;

import java.util.List;

@Data
public class SessionListResp {
    private String resultCode;
    private String errorDes;
    private String errorReson;
    private String errorDetail;
    private String errorAdvice;
    private List<SessionOnModel> data;
//    private Long endTime;
    private String nextEndTime;
}
