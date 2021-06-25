package com.c4demo.service.session;

import com.alibaba.fastjson.JSONObject;
import com.c4demo.configure.RestTemplateConfig;
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
import java.util.Map;

/**
 * @author 曾家华
 */
@Component
@CrossOrigin
public class SessionService {
    public final HttpMethod POST = HttpMethod.POST;
    public final HttpMethod GET = HttpMethod.GET;
    public final HttpMethod PUT = HttpMethod.PUT;

    private String token;
    private final RestTemplate restTemplate;
    private final String tokenUrl;

    public SessionService() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.restTemplate = new RestTemplate(RestTemplateConfig.generateHttpRequestFactory());
        this.tokenUrl = "https://117.78.31.209:26335/rest/plat/smapp/v1/oauth/token";
        this.updateToken();

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
        body.put("userName", "13264789860");
        body.put("value", "123456@lqz");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));

        ResponseEntity<String> resJson = this.getJsonData(tokenUrl, headers, body, this.PUT);

        JSONObject json = JSONObject.parseObject(resJson.getBody());
        this.token = json.getString("accessSession");
    }

    public ResponseEntity<String> getJsonData(String url, HttpHeaders headers, Map<String, String> body, HttpMethod reqMethod) {
        HttpEntity entity = new HttpEntity(body, headers);
        ResponseEntity<String> resJson = this.restTemplate.exchange(url, reqMethod, entity, String.class);
        System.out.println(resJson);
        if (JSONObject.parseObject(resJson.getBody()).getIntValue("resultCode") != 0) {
            this.updateToken();
            headers.set("X-Auth-Token", this.token);
            resJson = this.restTemplate.exchange(url, reqMethod, entity, String.class);
        }
        return resJson;
    }
}