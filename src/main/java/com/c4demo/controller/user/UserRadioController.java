package com.c4demo.controller.user;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@CrossOrigin
@Api(tags = "UserRadioController")
public class UserRadioController {
    @Value("${user.UserRadioController.data}")
    private String resp;

    @RequestMapping(value = "/user/radio")
    public String getUserRadio() {
        return resp;
    }
}
