package com.c4demo.controller.api.topography;

import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

@RestController
@RequestMapping("api")
@CrossOrigin
@Api(tags = "FloorTopographyController")
public class FloorTopographyController {
    @Value("${topography.floorTopography.path}")
    private String path;
    private SessionService sessionService;

    public FloorTopographyController() { }

    @Autowired
    public FloorTopographyController(SessionService sessionService) {
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

    @RequestMapping(value = "topography/floortopography", method = RequestMethod.POST)
    @ApiOperation(value = "getFloorTopology")
    public String getFloorTopology(@RequestParam(value = "level") int level,
                                   @RequestParam(value = "clientId") String clientId) {
        String param = "?param=";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("level", String.valueOf(level));
        jsonObject.put("id", clientId);
        jsonObject.put("type", "floor");

        param += UriEncoder.encode(jsonObject.toJSONString());
        return sessionService.getJsonData(path + param, null, SessionService.GET).getBody();
    }
}
