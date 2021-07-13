package com.c4demo.controller.api.user;

import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin
public class UserTrendController {
    @Resource
    private SessionService sessionService;
    @Value("${user.UserTrendController.path}")
    private String path;

    @RequestMapping(value = "/user/usertrend", method = RequestMethod.POST)
    public String getUserTrend(
            @RequestParam(value = "regionType") String regionType,
            @RequestParam(value = "level") String level,
            @RequestParam(value = "tenantId") String tenantId,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "id") String id,
            @RequestParam(value = "showType") String showType,
            @RequestParam(value = "isAutoRefresh") String isAutoRefresh
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("regionType", regionType);
        map.put("level", level);
        map.put("tenantId", tenantId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("id", id);
        map.put("showType", showType);
        map.put("isAutoRefresh", isAutoRefresh);

        String json = JSONObject.toJSONString(map);
        Map<String, String> body = new HashMap<>();
        body.put("param", json);

        ResponseEntity<String> resJson = sessionService.getJsonData(path, body, SessionService.GET);
        return resJson.getBody();
    }
}
