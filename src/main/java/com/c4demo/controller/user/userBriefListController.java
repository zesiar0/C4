package com.c4demo.controller.user;

import com.c4demo.service.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@CrossOrigin
@Api(tags = "userBriefListController")
public class userBriefListController {
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/user/brieflist", method = RequestMethod.POST)
    public String briefList() {
        return "";
    }
}
