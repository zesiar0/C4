package com.c4demo.api.user;

import com.c4demo.configure.RestTemplateConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin
public class userListController {

    @RequestMapping(value = "user/userlist", method = RequestMethod.GET)
    public String getUserList(
//            @RequestParam(value = "filter", required = false) Object filter,
//            @RequestParam(value = "level") String level,
//            @RequestParam(value = "tenantId") String tenantId,
//            @RequestParam(value = "startTime") String startTime,
//            @RequestParam(value = "endTime") String endTime,
//            @RequestParam(value = "id") String id,
//            @RequestParam(value = "sortColumn") String sortColumn,
//            @RequestParam(value = "currPage") String currPage,
//            @RequestParam(value = "pageSize") String pageSize,
//            @RequestParam(value = "sortType") String sortType
    ) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        RestTemplate restTemplate = new RestTemplate(RestTemplateConfig.generateHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });

        String url = "https://117.78.31.209:26335/rest/campusclientservice/v1/event/userlist";
        String tokenUrl = "http://localhost:8080/api/get_token";

        String token = restTemplate.getForObject(tokenUrl, String.class);
        if (token == null) {
            return "token failed";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.add("X-Auth-Token", token);

        Map<String, String> body = new HashMap<>();
        body.put("regionType", "site");
        body.put("level", "0");
        body.put("tenantId", "default-organization-id");
        body.put("startTime" ,"1592846642");
        body.put("id", "/");
        body.put("endTime" , "1624382642");
        body.put("sortColumn", null);
        body.put("currPage", "1");
        body.put("pageSize", "5");
        body.put("sortType", "asc");

        HttpEntity httpEntity = new HttpEntity(body, headers);
        ResponseEntity<String> resJson = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

        return  resJson.getBody();
    }
}
