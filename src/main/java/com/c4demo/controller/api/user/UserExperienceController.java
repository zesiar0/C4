package com.c4demo.controller.api.user;

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
public class UserExperienceController {
    @Resource
    private SessionService sessionService;
    @Value("${user.UserExperienceController.path}")
    private String path;

    @RequestMapping(value = "/user/userexpr", method = RequestMethod.GET)
    public String getUserExpr(
            @RequestParam(value = "userType") String userType,
            @RequestParam(value = "key") String key,
            @RequestParam(value = "tenantId") String tenantId,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "userMac") String userMac,
            @RequestParam(value = "trendType") String trendType
    ) {
        Map<String, String> body = new HashMap<>();
        body.put("userType", userType);
        body.put("key", key);
        body.put("tenantId", tenantId);
        body.put("startTime", startTime);
        body.put("endTime", endTime);
        body.put("userMac", userMac);
        body.put("trendType", trendType);

        ResponseEntity<String> resJson = sessionService.getJsonData(path, body, SessionService.POST);
        return resJson.getBody();
    }
}
