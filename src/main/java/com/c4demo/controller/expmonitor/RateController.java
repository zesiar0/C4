package com.c4demo.controller.expmonitor;

//api 3.2
import com.alibaba.fastjson.JSONObject;
import com.c4demo.entity.basictable.RateValueModel;
import com.c4demo.service.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin
@Api(tags = "RateController")
public class RateController {
    private SessionService sessionService;
    @Value("${expmonitor.RateController.path}")
    private String path;

    @Autowired
    public RateController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @RequestMapping(value = "exp/rate", method = RequestMethod.POST)
    @ApiOperation(value = "get_data")
    public String get_data(
//            @RequestParam(value = "level") int level,
//            @RequestParam(value = "startTime") Long startTime,
//            @RequestParam(value = "endTime") Long endTime
    ) {
        String url = "?param=";

        JSONObject param = new JSONObject();
        param.put("regionType", "site");
//        param.put("level", level);
        param.put("level", "0");
        param.put("tenantId", "default-organization-id");
//        param.put("startTime", startTime);
        param.put("startTime", "1597766400000");
//        param.put("endTime", endTime);
        param.put("endTime", "1597816800000");
        param.put("id", "/");

//        url += UriEncoder.encode(param.toJSONString());
        url += param.toJSONString();
        System.out.println(url);
        ResponseEntity<String> resJson = sessionService.getJsonData(path + url, null, SessionService.GET);
        JSONObject json = JSONObject.parseObject(resJson.getBody());
//        List<RateValueModel> rateList = json.getJSONObject("data").getJSONArray("values").toJavaList(RateValueModel.class);
        return json.toString();
    }
}
