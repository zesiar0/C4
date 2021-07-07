package com.c4demo.service.session;

import com.alibaba.fastjson.JSONObject;
import com.c4demo.configure.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 曾家华
 */
@Component
@CrossOrigin
@PropertySource("classpath:config.properties")
public class SessionService {
    static public final HttpMethod POST = HttpMethod.POST;
    static public final HttpMethod GET = HttpMethod.GET;
    static public final HttpMethod PUT = HttpMethod.PUT;

    private String token;
    private final RestTemplate restTemplate;
    @Value("${session.platformURL}")
    private String platformURL;
    @Value("${session.username}")
    private String userName;
    @Value("${session.password}")
    private String password;

    public SessionService() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.restTemplate = new RestTemplate(RestTemplateConfig.generateHttpRequestFactory());

        this.restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });
    }

    public String getToken() {
        return this.token;
    }

    public void updateToken() {
        Map<String, String> body = new HashMap<>(3);
        body.put("grantType", "password");
        body.put("userName", userName);
        body.put("value", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));

        ResponseEntity<String> resJson = getJsonData(false, "/rest/plat/smapp/v1/oauth/token", headers, body, PUT);

        this.token = JSONObject.parseObject(resJson.getBody()).getString("accessSession");
    }

    ResponseEntity<String> getJsonData(boolean needRenewToken, String path, HttpHeaders headers, Map<String, String> body, HttpMethod reqMethod) {
        HttpEntity entity = new HttpEntity(body, headers);
        ResponseEntity<String> resJson = this.restTemplate.exchange(platformURL + path, reqMethod, entity, String.class);
        System.out.println(resJson);
        if (needRenewToken && JSONObject.parseObject(resJson.getBody()).getIntValue("resultCode") != 0) {
            updateToken();
            headers.set("X-Auth-Token", getToken());
            // update header
            entity = new HttpEntity(body, headers);
            resJson = this.restTemplate.exchange(platformURL + path, reqMethod, entity, String.class);
        }
        return resJson;
    }

    public ResponseEntity<String> getJsonData(String path, Map<String, String> body, HttpMethod reqMethod) {
        if (getToken() == null) {
            updateToken();
        }
        // Header 不需要下层参与
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypeList = new LinkedList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypeList);
        headers.add("X-Auth-Token", getToken());
        return getJsonData(true, path, headers, body, reqMethod);
    }

}