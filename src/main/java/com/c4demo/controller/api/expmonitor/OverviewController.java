package com.c4demo.controller.api.expmonitor;

//api 3.1

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

public class OverviewController {
    @Resource
    private SessionService sessionService;
    @Value("${expmonitor.overview.url}")
    private String url;

    @RequestMapping(value = "exp/overview", method = RequestMethod.GET)
    public String overviewController() {

        JSONObject param = new JSONObject();
        param.put("regionType","site");
        param.put("level","1");
        param.put("tenantId","default-organization-id");
        param.put("startTime","1624549463000");
        param.put("endTime","1624635863000");
        param.put("id","857b706e-67d9-49c0-b3cd-4bd1e6963c07");

        String path = UriEncoder.encode(param.toJSONString());

        ResponseEntity<String> resJson = sessionService.getJsonData(url + path, null, SessionService.GET);

        return resJson.getBody();

    }
}
