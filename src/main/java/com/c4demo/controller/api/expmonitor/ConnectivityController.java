package com.c4demo.controller.api.expmonitor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin
public class ConnectivityController {
    private SessionService sessionService;
    @Value("${expmonitor.connectivity.path}")
    private String path;

    @Autowired
    public ConnectivityController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public ConnectivityController() { }

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

    @RequestMapping(value = "expmonitor/connectivity", method = RequestMethod.POST)
    public String getConnectivity(
//            @RequestParam(value = "regionType") String regionType,
//            @RequestParam(value = "level") int level,
//            @RequestParam(value = "tenantId") String tenantId,
//            @RequestParam(value = "startTime") long startTime,
//            @RequestParam(value = "endTime") long endTime,
//            @RequestParam(value = "id") String id,
//            @RequestParam(value = "accType") int accType,
//            @RequestParam(value = "dateFrom") long dateFrom,
//            @RequestParam(value = "dateTo") long dateTo
    ) {
        int associateFailNum = 0;
        int associateSuccNum = 0;
        int authFailNum = 0;
        int authSuccNum = 0;
        int dhcpFailNum = 0;
        int dhcpSuccNum = 0;
        int accessRatio = 0;
        int accessSuccNum = 0;
        int accessTotalNum = 0;
        float authSuccRatio;
        List<Map> retList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        JSONArray resArray;
        JSONObject param = new JSONObject();
        String url = path + "?param=";

//        param.put("regionType", regionType);
//        param.put("level", String.valueOf(level));
//        param.put("tenantId", tenantId);
//        param.put("startTime", String.valueOf(startTime));
//        param.put("endTime", String.valueOf(endTime));
//        param.put("id", id);
//        param.put("accType", String.valueOf(accType));
//        param.put("dateFrom", String.valueOf(dateFrom));
//        param.put("dateTo", String.valueOf(dateTo));

        param.put("regionType", "site");
        param.put("level", "0");
        param.put("tenantId", "default-organization-id");
        param.put("startTime", "1626092895000");
        param.put("endTime", "1626179295000");
        param.put("id", "/");
        param.put("accType", "1");
        param.put("dateFrom", "1626092895000");
        param.put("dateTo", "1626179295000");

        url += UriEncoder.encode(param.toJSONString());

        ResponseEntity<String> resJson = sessionService.getJsonData(url, null, SessionService.GET);
        resArray = JSONArray.parseArray(String.valueOf(JSONObject.parseObject(resJson.getBody()).get("data")));

        for (int i = 0; i < resArray.size(); i++) {
            JSONObject json = resArray.getJSONObject(i);
            associateFailNum += (int) json.get("associateFailNum");
            associateSuccNum += (int) json.get("associateSuccNum");
            authFailNum += (int) json.get("authFailNum");
            authSuccNum += (int) json.get("authSuccNum");
            dhcpFailNum += (int) json.get("dhcpFailNum");
            dhcpSuccNum += (int) json.get("dhcpSuccNum");
            accessRatio += (int) json.get("accessRatio");
            accessTotalNum += (int) json.get("accessTotalNum");
            accessSuccNum += (int) json.get("accessSuccNum");
        }

        map.put("associateFailNum", associateFailNum);
//        map.put("associateSuccNum", associateSuccNum);
        map.put("authFailNum", authFailNum);
//        map.put("authSuccNum", authSuccNum);
        map.put("dhcpFailNum", dhcpFailNum);
//        map.put("dhcpSuccNum", dhcpSuccNum);
//        map.put("accessRatio", accessRatio);
        map.put("accessSuccRatio", 100 - accessRatio);
//        map.put("authSuccNum", authSuccNum);
        map.put("accessTotalNum", accessTotalNum);
        retList.add(map);
        return resJson.getBody();
    }
}
