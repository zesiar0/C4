package com.c4demo.controller.api.topography;

import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

@RestController
@RequestMapping(path = "api/topography")
@CrossOrigin
public class ClientPositionController {
    @Value("${topography.clientPosition.path}")
    private String path;
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

    @GetMapping("clientposition")
    public String getClientPosition(@RequestParam int level,
                                    @RequestParam String clientId) {
        String param = "?param=";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("level", level);
        jsonObject.put("id", clientId);
        jsonObject.put("type", "floor");

        param += UriEncoder.encode(jsonObject.toJSONString());
        return sessionService.getJsonData(path + param, null, SessionService.POST).getBody();
    }
}
