package com.c4demo.controller.api.Qs;

import com.alibaba.fastjson.JSONObject;
import com.c4demo.configure.RestTemplateConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin

public class QsHealthTrendController {

    @RequestMapping(value = "QS/htrend", method = RequestMethod.GET)

    public String get_data() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException{

        RestTemplate restTemplate = new RestTemplate(RestTemplateConfig.generateHttpRequestFactory());

        String url = "https://117.78.31.209:26335/rest/campuswlanqualityservice/v1/expmonitor/rate/basictable";

        String token_url = "http://localhost:8080/api/get_token";

        String token = restTemplate.getForObject(token_url, String.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.add("X-Auth-Token", token);


        JSONObject param = new JSONObject();
        param.put("regionType","site");
        param.put("level","1");
        param.put("tenantId","default-organization-id");
        param.put("startTime","1624549463000");
        param.put("endTime","1624635863000");
        param.put("id","857b706e-67d9-49c0-b3cd-4bd1e6963c07");

        UriComponentsBuilder bulider = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("param",param.toJSONString());

        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> resJson = restTemplate.exchange(bulider.toUriString(),HttpMethod.GET,httpEntity,String.class);

        return resJson.getBody();
    }

}
