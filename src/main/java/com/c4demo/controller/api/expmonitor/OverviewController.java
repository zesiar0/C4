package com.c4demo.controller.api.expmonitor;

//api 3.1

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.UriEncoder;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("api")
@CrossOrigin

public class OverviewController {

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "exp/overview", method = RequestMethod.GET)

    public List<Map<String,Object>> get_qsdata() {

        String url = "/rest/campuswlanqualityservice/v1/expmonitor/overview/rate?param=";

        String token = sessionService.getToken();

        String[] all_id = {"c94e9196-3686-4d4c-a7de-8117f581f63e","9fc9253f-33a4-40db-8afc-825f383e54e8",
                "0504b1cb-e368-452c-8b43-2c8be81b3d14","857b706e-67d9-49c0-b3cd-4bd1e6963c07"};

        int counter = 0;

        ResponseEntity<String> tmp = null;

        List<Map<String,Object>> listmap = new ArrayList<Map<String,Object>>();

        Map<String,Object> map_tmp1 = new HashMap<String,Object>();
        Map<String,Object> map_tmp2 = new HashMap<String,Object>();
        Map<String,Object> map_tmp3 = new HashMap<String,Object>();
        Map<String,Object> map_tmp4 = new HashMap<String,Object>();

        while(counter != 4)
        {
            JSONObject param = new JSONObject();
            param.put("regionType","site");
            param.put("level","1");
            param.put("tenantId","default-organization-id");
            param.put("startTime","1624549463000");
            param.put("endTime","1624635863000");
            param.put("id",all_id[counter]);

            String path = UriEncoder.encode(param.toJSONString());

            tmp = sessionService.getJsonData(url + path, null, SessionService.GET);

            JSONObject jsonObject = JSONObject.parseObject(tmp.getBody());

            switch(counter)
            {
                case 0:
                    map_tmp1.put("city","Nanjing");
                    map_tmp1.put("successCon",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("successCon"));
                    map_tmp1.put("roaming",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("roaming"));
                    map_tmp1.put("capacity",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("capacity"));
                    map_tmp1.put("throughput",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("throughput"));
                    listmap.add(map_tmp1);
                    break;
                case 1:
                    map_tmp2.put("city","Shanghai");
                    map_tmp2.put("successCon",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("successCon"));
                    map_tmp2.put("roaming",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("roaming"));
                    map_tmp2.put("capacity",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("capacity"));
                    map_tmp2.put("throughput",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("throughput"));
                    listmap.add(map_tmp2);
                    break;

                case 2:
                    map_tmp3.put("city","Suzhou");
                    map_tmp3.put("successCon",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("successCon"));
                    map_tmp3.put("roaming",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("roaming"));
                    map_tmp3.put("capacity",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("capacity"));
                    map_tmp3.put("throughput",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("throughput"));
                    listmap.add(map_tmp3);
                    break;
                case 3:
                    map_tmp4.put("city","Shenzhen");
                    map_tmp4.put("successCon",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("successCon"));
                    map_tmp4.put("roaming",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("roaming"));
                    map_tmp4.put("capacity",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("capacity"));
                    map_tmp4.put("throughput",jsonObject.getJSONObject("data").getJSONObject("values").getFloatValue("throughput"));
                    listmap.add(map_tmp4);
                    break;
            }

            counter++;
       }
        return listmap;
    }


}
