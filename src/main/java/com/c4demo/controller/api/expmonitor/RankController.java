package com.c4demo.controller.api.expmonitor;

//api 3.3
import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

@RestController
@RequestMapping("api")
@CrossOrigin
public class RankController {
    private SessionService sessionService;
    @Value("${expmonitor.RankController.path}")
    private String path;

    @Autowired
    public RankController(SessionService sessionService) {
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

    @GetMapping(value = "exp/rank")
    public String get_data(@RequestParam String regionType, @RequestParam int level) {
        String url = "?param=";

        JSONObject param = new JSONObject();
        param.put("regionType", regionType);
        param.put("level", level);
        param.put("tenantId", "default-organization-id");
        param.put("startTime", "1624549463000");
        param.put("endTime", "1624635863000");
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
