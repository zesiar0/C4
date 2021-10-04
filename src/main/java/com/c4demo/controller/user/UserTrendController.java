package com.c4demo.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin
@Api(tags = "UserTrendController")
public class UserTrendController {
    @Resource
    private SessionService sessionService;
    @Value("${user.UserTrendController.path}")
    private String path;
    @Value("${user.UserTrendController.data}")
    private String resp;

    @RequestMapping(value = "/user/usertrend")
    @ApiOperation(value = "getUserTrend")
    public String getUserTrend(
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "showType", required = false) String showType
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("regionType", "site");
        map.put("level", 0);
        map.put("tenantId", "default-organization-id");
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("id", "/");
        map.put("showType", showType);
        map.put("isAutoRefresh", false);

        String json = JSONObject.toJSONString(map);
        Map<String, String> body = new HashMap<>();
        body.put("param", json);

        ResponseEntity<String> resJson = sessionService.getJsonData(path, body, SessionService.GET);
        return resp;
    }
}
