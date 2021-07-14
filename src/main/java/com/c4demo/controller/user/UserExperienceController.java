package com.c4demo.controller.user;

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
@Api(tags = "UserExperienceController")
public class UserExperienceController {
    @Resource
    private SessionService sessionService;
    @Value("${user.UserExperienceController.path}")
    private String path;

    @RequestMapping(value = "/user/userexpr", method = RequestMethod.POST)
    @ApiOperation(value = "getUserExpr")
    public String getUserExpr(
            @RequestParam(value = "userType") String userType,
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "userMac") String userMac,
            @RequestParam(value = "trendType") String trendType
    ) {
        Map<String, String> body = new HashMap<>();
        body.put("userType", userType);
        body.put("key", "");
        body.put("tenantId", "default-organization-id");
        body.put("startTime", startTime);
        body.put("endTime", endTime);
        body.put("userMac", userMac);
        body.put("trendType", trendType);

        ResponseEntity<String> resJson = sessionService.getJsonData(path, body, SessionService.POST);
        return resJson.getBody();
    }
}
