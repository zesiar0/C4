package com.c4demo.controller.api.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.c4demo.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin
public class UserListController {
    private SessionService sessionService;

    @Autowired
    public UserListController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public UserListController() { }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * @author 曾家华
     * @return resJson.getBody() 返回查询的所有用户信息
     */
    @RequestMapping(value = "/user/userlist", method = RequestMethod.GET)
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
        String path = "/rest/campusclientservice/v1/event/userlist";
        JSONObject ret = new JSONObject();
        int currentPage = 1;
        Map<String, String> body = new HashMap<>();

        body.put("regionType", "site");
        body.put("level", "0");
        body.put("tenantId", "default-organization-id");
        body.put("startTime", "1597766400000");
        body.put("id", "/");
        body.put("endTime", "1598374800000");
        // body.put("endTime", String.valueOf(new Date().getTime()));
        body.put("sortColumn", null);
        body.put("currPage", String.valueOf(currentPage++));
        body.put("pageSize", "200");
        body.put("sortType", "asc");

        ResponseEntity<String> resJson = sessionService.getJsonData(path, body, SessionService.POST);

        JSONObject jsonObject = JSONObject.parseObject(resJson.getBody()), data;

        if (jsonObject == null) {
            ret.put("ok", 0);
            ret.put("reason", "Empty response from upstream server");
            return ret.toJSONString();
        }

        int resultCode = jsonObject.getInteger("resultCode");
        if (resultCode != 0) {
            ret.put("ok", 0);
            ret.put("reason", "Error from upstream server: " + jsonObject.getString("errorReson"));
            return ret.toJSONString();
        }

        data = jsonObject.getJSONObject("data");
        if (data == null) {
            ret.put("ok", 0);
            ret.put("reason", "Empty 'data' object");
            return ret.toJSONString();
        }

        int pageSize = data.getInteger("pageSize"), totalSize = data.getInteger("totalSize");
        int remainingSize = pageSize - totalSize;
        JSONArray resArray = data.getJSONArray("tableData");

        while (remainingSize > 0) {
            body.remove("currPage");
            body.put("currPage", String.valueOf(currentPage++));
            resJson = sessionService.getJsonData(path, body, SessionService.POST);
            jsonObject = JSONObject.parseObject(resJson.getBody());
            if (jsonObject == null || jsonObject.getInteger("resultCode") != 0)
                break;
            data = jsonObject.getJSONObject("data");
            resArray.addAll(data.getJSONArray("tableData"));
            pageSize = data.getInteger("pageSize");
            remainingSize -= pageSize;
        }

        ret.put("userList", resArray);
        ret.put("userCount", resArray.size());
        ret.put("ok", 1);

        return ret.toJSONString();
    }
}
