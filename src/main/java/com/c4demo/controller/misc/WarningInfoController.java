package com.c4demo.controller.misc;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@CrossOrigin
@Api(tags = "WarningInfoController")
public class WarningInfoController {

    @Value("${info.WarnInfoController.data}")
    private String warningInfo;

    public void setWarningInfo(String warningInfo) {
        this.warningInfo = warningInfo;
    }

    public String getWarningInfo() {
        return warningInfo;
    }

    @RequestMapping(value = "info/warninfo", method = RequestMethod.GET)
    public String requestWarningInfo() {
        return getWarningInfo();
    }
}
