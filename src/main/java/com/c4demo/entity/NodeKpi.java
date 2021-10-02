package com.c4demo.entity;

import lombok.Data;

@Data
public class NodeKpi {
    private float avgRssi;
    private float avgDownLinkSpeed;
    private float acgUpLinkSpeed;
    private float avgDiscardRadio;
    private float avgLatency;
    private float duration;
    private float badTime;
    private float maxAvgRssiUserMac;
    private float minAvgRssi;
    private float traffic;
    private float timeLenAsso;
    private float timeLenAuth;
    private float timeLenDhcp;
    private float upTraffic;
    private float downTraffic;
    private float upSpeed;
    private float downSpeed;
    private float retransRate;
    private float snr;
    private float stickyTag;
    private float channel;
    private float psTime;
    private float support802k;
    private float support802v;
    private float support802r;
    private float authType;
    private float upExpSpeed;
    private float downExpSpeed;
}
