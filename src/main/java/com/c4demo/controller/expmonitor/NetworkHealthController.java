package com.c4demo.controller.expmonitor;

import com.alibaba.fastjson.JSONArray;
import com.c4demo.service.SessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("api")
@CrossOrigin
public class NetworkHealthController {
    @Resource
    private SessionService sessionService;
    @Value("${expmonitor.NetworkHealthController.path}")
    private String url;
    @Value("${expmonitor.NetworkHealthController.data}")
    private String resp;

    @RequestMapping(value = "/exp/netHealth")
    public String searchNetworkHealth(
            @RequestParam(value = "statDimens", required = false) String statDimens,
            @RequestParam(value = "beginTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "siteInfo", required = false) String siteInfo
    ) {
        HashMap<String, String> body = new HashMap<>();
        body.put("statDimens", statDimens);
        body.put("beginTime", startTime);
        body.put("endTime", endTime);
        body.put("siteInfo", siteInfo);

        String data = UriEncoder.encode("{\"endTime\":1628496796000,\"beginTime\":1628438400000,\"statDimens\":[\"ALL\",\"deviceEnv\",\"deviceCapacity\",\"networkPerformance\",\"networkStatus\"],\"siteInfo\":{\"level\":1,\"subnetIds\":[\"857b706e-67d9-49c0-b3cd-4bd1e6963c07\"]}}");
        String path = "?condition=" + data;

        System.out.println(url + path);
//        ResponseEntity<String> resJson = sessionService.getJsonData(url+path, null, SessionService.GET);
        return resp;
    }
}
