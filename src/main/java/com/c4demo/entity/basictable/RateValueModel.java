package com.c4demo.entity.basictable;

import lombok.Data;

@Data
public class RateValueModel {
    private Long timestamp;
    private Double rate;
    private Double successCon;
    private Double timeCon;
    private Double roaming;
    private Double coverage;
    private Double capacity;
    private Double throughput;
}
