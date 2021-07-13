package com.c4demo.controller.api.expmonitor;

//api 3.3
import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.annotation.Resource;

@RestController
@RequestMapping("api")
@CrossOrigin

public class RankController {
    @Resource
    private SessionService sessionService;
    @Value("${expmonitor.rankcontroller.url}")
    private String url;

    @RequestMapping(value = "exp/rank", method = RequestMethod.GET)
    public String get_data() {
        JSONObject param = new JSONObject();
        param.put("regionType","site");
        param.put("level","1");
        param.put("tenantId","default-organization-id");
        param.put("startTime","1624549463000");
        param.put("endTime","1624635863000");
        param.put("id","/");

        String path = UriEncoder.encode(param.toJSONString());

        ResponseEntity<String> resJson = sessionService.getJsonData(url + path, null, SessionService.GET);

        return resJson.getBody();
    }
}
