package com.c4demo.controller.user;

import com.c4demo.service.SessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("api")
@CrossOrigin
public class UserJourneyController {
    @Resource
    private SessionService sessionService;
    @Value("${user.UserJourneyController.path}")
    private String path;

    @RequestMapping(value = "/user/nodelist", method = RequestMethod.POST)
    public String getNodeList(
            @RequestParam(value = "startTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "usermac") String usermac
    ) {
        HashMap<String, String> body = new HashMap<>();
        body.put("startTime", startTime);
        body.put("endTime", endTime);
        body.put("usermac", usermac);

        ResponseEntity<String> resJson = sessionService.getJsonData(path, body, SessionService.POST);
        return resJson.getBody();
    }
}
