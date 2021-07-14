package com.c4demo.controller.api.topography;

import com.c4demo.service.session.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin
@Api(tags = "SubtreeController")
public class SubtreeController {

    @Value("${topography.subtree.path}")
    private String path;
    private SessionService sessionService;

    public SubtreeController() {  }

    @Autowired
    public SubtreeController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @RequestMapping(value = "topography/subtree", method = RequestMethod.POST)
    @ApiOperation(value = "getSubtree")
    public String getSubtree(@RequestParam(value = "locationId") String locationId) {
        MultiValueMap<String, String> idHeader = new LinkedMultiValueMap<>();
        idHeader.add("id", locationId);

        return sessionService.getJsonData(path, idHeader, null, SessionService.GET).getBody();
    }
}
