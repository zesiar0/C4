package com.c4demo.controller.api.user;

import com.c4demo.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin
public class userListController {

    @Autowired
    private SessionService sessionService;

    /**
     * @author 曾家华
     * @return resJson.getBody() 返回查询的所有用户信息
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     */
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
    ) {
        String url = "https://117.78.31.209:26335/rest/campusclientservice/v1/event/userlist";
        String token = sessionService.getToken();

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

        ResponseEntity<String> resJson = sessionService.getJsonData(url, headers, body, sessionService.POST);
        return resJson.getBody();
    }
}
