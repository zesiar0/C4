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
@Api(tags = "UserInfoController")
public class UserInfoController {
    @Resource
    private SessionService sessionService;
    @Value("${user.UserInfoController.path}")
    private String path;

    @RequestMapping(value = "/user/userinfo", method = RequestMethod.POST)
    @ApiOperation(value = "getUserInfo")
    public String getUserInfo(
            @RequestParam(value = "intervals") String intervals,
            @RequestParam(value = "accType") String accType,
            @RequestParam(value = "usermac") String usermac
    ) {

        Map<String, String> body = new HashMap<>();
        body.put("intervals", intervals);
        body.put("level", "0");
        body.put("tenantId", "default-organization-id");
        body.put("accType", accType);
        body.put("usermac", usermac);
        body.put("id", "/");
//        body.put("intervals", "1624556576000-1624614176000");
//        body.put("level", "0");
//        body.put("tenantId", "default-organization-id");
//        body.put("accType", "1");
//        body.put("usermac", "fc-9c-3f-c5-0e-a7");
//        body.put("id", "/");

        ResponseEntity<String> resJson = sessionService.getJsonData(path, body, SessionService.POST);
        return resJson.getBody();
    }
}
