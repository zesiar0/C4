package com.c4demo.controller.expmonitor;

import com.c4demo.service.SessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class NetworkHealthController {
    @Resource
    private SessionService sessionService;
    @Value("${expmonitor.NetworkHealthController.path}")
    private String path;

    @RequestMapping(value = "/exp/netHealth", method = RequestMethod.POST)
    public String searchNetworkHealth(
            @RequestParam(value = "statDimens") String statDimens,
            @RequestParam(value = "beginTime") String startTime,
            @RequestParam(value = "endTime") String endTime,
            @RequestParam(value = "siteInfo") String siteInfo
    ) {
        HashMap<String, String> body = new HashMap<>();
        body.put("statDimens", statDimens);
        body.put("beginTime", startTime);
        body.put("endTime", endTime);
        body.put("siteInfo", siteInfo);

        ResponseEntity<String> resJson = sessionService.getJsonData(path, body, SessionService.GET);
        return resJson.getBody();
    }
}
