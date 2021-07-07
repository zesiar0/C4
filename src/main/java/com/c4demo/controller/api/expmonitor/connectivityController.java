package com.c4demo.controller.api.expmonitor;

import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

@RestController
@RequestMapping("api")
@CrossOrigin
public class connectivityController {
    private SessionService sessionService;

    @Autowired
    public connectivityController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public connectivityController() {
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @RequestMapping(value = "expmonitor/connectivity", method = RequestMethod.GET)
    public String getConnectivity(
            @RequestParam(value = "regionType") String regionType,
            @RequestParam(value = "level") int level,
            @RequestParam(value = "tenantId") String tenantId,
            @RequestParam(value = "startTime") long startTime,
            @RequestParam(value = "endTime") long endTime,
            @RequestParam(value = "id") String id,
            @RequestParam(value = "accType") int accType,
            @RequestParam(value = "dateFrom") long dateFrom,
            @RequestParam(value = "dateTo") long dateTo
    ) {
        JSONObject res = null, param = new JSONObject();
        String url = "/rest/campuswlanqualityservice/v1/connectivity/connect-trend";

        param.put("regionType", regionType);
        param.put("level", String.valueOf(level));
        param.put("tenantId", tenantId);
        param.put("startTime", String.valueOf(startTime));
        param.put("endTime", String.valueOf(endTime));
        param.put("id", id);
        param.put("accType", String.valueOf(accType));
        param.put("dateFrom", String.valueOf(dateFrom));
        param.put("dateTo", String.valueOf(dateTo));

        url += "?param=" + UriEncoder.encode(param.toJSONString());

        ResponseEntity<String> resJson = sessionService.getJsonData(url, null, SessionService.GET);
        return resJson.getBody();
    }
}
