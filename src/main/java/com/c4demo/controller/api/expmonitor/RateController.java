package com.c4demo.controller.api.expmonitor;

//api 3.2
import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

@RestController
@RequestMapping("api")
@CrossOrigin
@Api(tags = "RateController")
public class RateController {
    private SessionService sessionService;
    @Value("${expmonitor.RateController.path}")
    private String path;

    @Autowired
    public RateController(SessionService sessionService) {
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

    @RequestMapping(value = "exp/rate", method = RequestMethod.POST)
    @ApiOperation(value = "get_data")
    public String get_data(
//            @RequestParam(value = "regionType") String regionType,
//            @RequestParam(value = "level") int level,
//            @RequestParam(value = "id") String id,
//            @RequestParam(value = "startTime") Long startTime,
//            @RequestParam(value = "endTime") Long endTime
    ) {
        String url = "?param=";

        JSONObject param = new JSONObject();
//        param.put("regionType", regionType);
        param.put("regionType", "site");
//        param.put("level", level);
        param.put("level", "1");
        param.put("tenantId", "default-organization-id");
//        param.put("startTime", startTime);
        param.put("startTime", "1624549463000");
//        param.put("endTime", endTime);
        param.put("endTime", "1624635863000");
//        param.put("id", id);
        param.put("id", "/");

        url += UriEncoder.encode(param.toJSONString());
        ResponseEntity<String> resJson = sessionService.getJsonData(path + url, null, SessionService.GET);
        JSONObject json = JSONObject.parseObject(resJson.getBody()), retJson = new JSONObject();

        if (json == null || json.getInteger("resultCode") != 0) {
            // error
            retJson.put("ok", 0);
            retJson.put("reason", (json == null) ? "Empty response from upstream server" : ("Error: " + json.getString("errorReson")));
            return retJson.toJSONString();
        }

        retJson.put("ok", 1);
        retJson.put("data", json.getJSONObject("data"));

        return retJson.toJSONString();
    }
}
