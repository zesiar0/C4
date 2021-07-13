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
public class UserInfoController {
    @Resource
    private SessionService sessionService;
    @Value("${user.UserInfoController.path}")
    private String path;

    @RequestMapping(value = "/user/userinfo", method = RequestMethod.POST)
    public String getUserInfo(
//            @RequestParam(value = "intervals") String intervals,
//            @RequestParam(value = "level") String level,
//            @RequestParam(value = "tenantId") String tenantId,
//            @RequestParam(value = "accType") String accType,
//            @RequestParam(value = "usermac") String usermac,
//            @RequestParam(value = "id") String id
    ) {

        Map<String, String> body = new HashMap<>();
//        body.put("intervals", intervals);
//        body.put("level", level);
//        body.put("tenantId", tenantId);
//        body.put("accType", accType);
//        body.put("usermac", usermac);
//        body.put("id", id);
        body.put("intervals", "1624556576000-1624614176000");
        body.put("level", "0");
        body.put("tenantId", "default-organization-id");
        body.put("accType", "1");
        body.put("usermac", "fc-9c-3f-c5-0e-a7");
        body.put("id", "/");

        ResponseEntity<String> resJson = sessionService.getJsonData(path, body, SessionService.POST);
        return resJson.getBody();
    }
}
