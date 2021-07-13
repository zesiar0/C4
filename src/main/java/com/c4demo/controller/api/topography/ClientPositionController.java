package com.c4demo.controller.api.topography;

import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.annotation.Resource;

@RestController
@RequestMapping(path = "api")
@CrossOrigin
public class ClientPositionController {
    @Value("${topography.clientPosition.path}")
    private String path;
    @Resource
    private SessionService sessionService;

    public ClientPositionController() { }

    @Autowired
    public ClientPositionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @RequestMapping(value = "topography/clientposition", method = RequestMethod.POST)
    public String getClientPosition(@RequestParam(value = "level") int level,
                                    @RequestParam(value = "clientId") String clientId) {
        String param = "?param=";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("level", level);
        jsonObject.put("id", clientId);
        jsonObject.put("type", "floor");

        param += UriEncoder.encode(jsonObject.toJSONString());
        return sessionService.getJsonData(path + param, null, SessionService.POST).getBody();
    }
}
