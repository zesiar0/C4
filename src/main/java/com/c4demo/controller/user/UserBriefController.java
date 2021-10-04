package com.c4demo.controller.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api")
@Api(tags = "UserBriefController")
public class UserBriefController {

    UserListController userListControllerIns;

    @Autowired
    public UserBriefController(UserListController userListControllerIns) {
        this.userListControllerIns = userListControllerIns;
    }

    public UserBriefController() { }

    public UserListController getUserListControllerIns() {
        return userListControllerIns;
    }

    public void setUserListControllerIns(UserListController userListControllerIns) {
        this.userListControllerIns = userListControllerIns;
    }

    @RequestMapping(value = "/user/userbriefinfo", method = RequestMethod.POST)
    @ApiOperation(value = "getUserBriefInfo")
    public String getUserBriefInfo() {
        JSONObject userInfo = JSONObject.parseObject(userListControllerIns.getUserList()), ans = new JSONObject();

        ans.put("ok", userInfo.getInteger("ok"));

        if (userInfo.getInteger("ok") == 1) {
            JSONArray userArray = userInfo.getJSONArray("userList"), resArray = new JSONArray();
            int length = userInfo.getInteger("userCount");

            for (int i = 0; i < length; i++) {
                JSONObject currentUser = userArray.getJSONObject(i);
                JSONObject resUserInfo = new JSONObject();

                resUserInfo.put("userName", currentUser.getString("userName"));
                resUserInfo.put("userMac", currentUser.getString("userMac"));
                resUserInfo.put("userExprTime", currentUser.getLong("totalTimes"));

                resArray.add(resUserInfo);
            }
            ans.put("userCount", length);
            ans.put("userBriefInfo", resArray);
        } else {
            ans.put("reason", userInfo.getString("reason"));
        }

        return ans.toJSONString();
    }
}
