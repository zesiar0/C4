package com.c4demo.entity.nodelist;

import lombok.Data;

@Data
public class NodeKpi {
    private Float avgRssi;
    private Float avgDownLinkSpeed;
    private Float acgUpLinkSpeed;
    private Float avgDiscardRadio;
    private Float avgLatency;
    private Float duration;
    private Float badTime;
    private Float maxAvgRssiUserMac;
    private Float minAvgRssi;
    private Float traffic;
    private Float timeLenAsso;
    private Float timeLenAuth;
    private Float timeLenDhcp;
    private Float upTraffic;
    private Float downTraffic;
    private Float upSpeed;
    private Float downSpeed;
    private Float retransRate;
    private Float snr;
    private Float mode;
    private Float stickyTag;
    private Float channel;
    private Float psTime;
    private Float support802k;
    private Float support802v;
    private Float support802r;
    private String authType;
    private Float upExpSpeed;
    private Float downExpSpeed;
}
