package com.c4demo.entity.nodelist;

import com.c4demo.entity.nodelist.NodeKpi;
import lombok.Data;

import java.util.List;

@Data
public class SessionOnModel {
    private Long startTime;
    private Long endTime;
    private Long accTime;
    private String ssid;
    private String radioId;
    private String band;
    private Integer apMac;
    private String apName;
    private String regionId;
    private String errCode;
    private Integer brief;
    private String ip;
    private String nodeType;
    private String radiusId;
    private String selectRadiusIp;
    private String authId;
    private String issues;
    private List<Object> eventList;
    private String offLineInfo;
    private NodeKpi nodeKpi;
}
