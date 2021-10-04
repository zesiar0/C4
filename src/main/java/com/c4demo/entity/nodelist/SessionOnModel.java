package com.c4demo.entity.nodelist;

import com.c4demo.entity.nodelist.NodeKpi;
import lombok.Data;

import java.util.List;

@Data
public class SessionOnModel {
    private Long startTime;
    private Long endTime;
    private String accTime;
    private String badTime;
    private String ssid;
    private String radioId;
    private String band;
    private String apMac;
    private String apName;
    private String regionId;
    private String errCode;
    private Integer brief;
    private String ip;
    private String nodeType;
    private String radiusIp;
    private String selectRadiusIp;
    private String authId;
    private List<Object> issues;
    private List<Object> eventList;
    private String offLineInfo;
    private NodeKpi nodeKpi;
}
