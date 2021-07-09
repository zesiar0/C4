package com.c4demo.controller.api.topography;

import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

@RestController
@RequestMapping("api/topography")
@CrossOrigin
public class floorTopographyController {
    @Value("${topography.floorTopography.path}")
    private String path;
    private SessionService sessionService;

    public floorTopographyController() { }

    @Autowired
    public floorTopographyController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @GetMapping(value = "floortopography")
    public String getFloorTopology(@RequestParam int level,
                                   @RequestParam String clientId) {
        String param = "?param=";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("level", String.valueOf(level));
        jsonObject.put("id", clientId);
        jsonObject.put("type", "floor");

        param += UriEncoder.encode(jsonObject.toJSONString());
        return sessionService.getJsonData(path + param, null, SessionService.GET).getBody();
    }
}
