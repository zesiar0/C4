package com.c4demo.controller.card;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin
@Api(tags = "MeetingCardController")
public class MeetingCardController {
    @Value("${user.UserMeetingCardController.data}")
    private String resp;

    @RequestMapping(value = "/card/meeting")
    public String getMeetingCard() {

        return resp;
    }
}
