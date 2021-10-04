package com.c4demo.controller.user.card;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@CrossOrigin
@Api(tags = "UserCardController")
public class UserCardController {
    @Value("${user.UserCardController.data}")
    private String resp;

    @RequestMapping(value = "/user/card")
    public String getUserCard() {
        return resp;
    }
}
