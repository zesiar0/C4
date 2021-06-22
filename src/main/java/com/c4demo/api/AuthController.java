package com.c4demo.api;

import com.alibaba.fastjson.JSONObject;
import com.c4demo.configure.RestTemplateConfig;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin
public class AuthController {

    @RequestMapping(value = "get_token", method = RequestMethod.GET)
    public String getToken() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        RestTemplate restTemplate =new RestTemplate(RestTemplateConfig.generateHttpRequestFactory());
        String url = "https://117.78.31.209:26335/rest/plat/smapp/v1/oauth/token";

        Map<String, String> map = new HashMap<>();
        map.put("grantType", "password");
        map.put("userName", "13264789860");
        map.put("value", "123456@lqz");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));

        HttpEntity entity = new HttpEntity(map, headers);

        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

        String body = exchange.getBody();
        JSONObject json = JSONObject.parseObject(body);
        String token = json.getString("accessSession");

        return token;
    }
}
