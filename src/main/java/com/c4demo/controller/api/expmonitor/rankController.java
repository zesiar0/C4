package com.c4demo.controller.api.expmonitor;

//api 3.3
import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api")
@CrossOrigin

public class rankController {
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "exp/rank", method = RequestMethod.GET)

    public String get_data() {

        String url = "/rest/campuswlanqualityservice/v1/expmonitor/rank/basictable?param=";

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
