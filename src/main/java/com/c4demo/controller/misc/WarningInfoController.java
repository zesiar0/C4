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

    @Value("${info.WarnInfoController.brief}")
    private String warningBrief;

    public void setWarningInfo(String warningInfo) {
        this.warningInfo = warningInfo;
    }

    public String getWarningInfo() {
        return warningInfo;
    }

    public String getWarningBrief() {
        return warningBrief;
    }

    public void setWarningBrief(String warningBrief) {
        this.warningBrief = warningBrief;
    }

    @RequestMapping(value = "info/warninfo", method = RequestMethod.GET)
    public String requestWarningInfo() {
        return getWarningInfo();
    }

    @RequestMapping(value = "info/warnbrief", method = RequestMethod.GET)
    public String requestWarningBrief() {
        return getWarningBrief();
    }
}
