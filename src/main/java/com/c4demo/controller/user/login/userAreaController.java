package com.c4demo.controller.user.login;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin
@Api(tags = "userAreaController")
public class userAreaController {
    @RequestMapping(value = "/user/area")
    public String getUserArea() {
        Map<String, Object> map = new HashMap<>();
        List<Map> areaList = new ArrayList<>();
        Map<String, Object> data1 = new HashMap<>();
        Map<String, Object> data2 = new HashMap<>();
        Map<String, Object> data3 = new HashMap<>();
        Map<String, Object> data4 = new HashMap<>();

        data1.put("city", "深圳");
        data1.put("area", "华为坂田基地");
        data1.put("healthy", 96.61);
        data1.put("ioRatio", 100);
        data1.put("roamingRatio", 100);
        data1.put("accessRatio", 64.95);

        data2.put("city", "南京");
        data2.put("area", "华为研究所");
        data2.put("healthy", 93.56);
        data2.put("ioRatio", 100);
        data2.put("roamingRatio", 100);
        data2.put("accessRatio", 75.19);

        data3.put("city", "苏州");
        data3.put("area", "华为研发中心");
        data3.put("healthy", 99.43);
        data3.put("ioRadio", 100);
        data3.put("roamingRatio", 100);
        data3.put("accessRatio", 53.76);

        data4.put("city", "上海");
        data4.put("area", "华为研究所");
        data4.put("healthy", 91.46);
        data4.put("ioRadio", 99.46);
        data4.put("roamingRatio", 100);
        data4.put("accessRatio", 100);

        areaList.add(data1);
        areaList.add(data2);
        areaList.add(data3);
        areaList.add(data4);

        map.put("code", 200);
        map.put("message", "success");
        map.put("data", areaList);

        return JSON.toJSONString(map);
    }
}
