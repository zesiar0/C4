package com.c4demo.controller.api.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api")
public class UserBriefController {

    UserListController userListControllerIns;

    @Autowired
    public UserBriefController(com.c4demo.controller.api.user.UserListController userListControllerIns) {
        this.userListControllerIns = userListControllerIns;
    }

    public UserBriefController() { }

    public com.c4demo.controller.api.user.UserListController getUserListControllerIns() {
        return userListControllerIns;
    }

    public void setUserListControllerIns(com.c4demo.controller.api.user.UserListController userListControllerIns) {
        this.userListControllerIns = userListControllerIns;
    }

    @RequestMapping(value = "/user/userbriefinfo", method = RequestMethod.POST)
    public String getUserBriefInfo() {
        JSONObject userInfo = JSONObject.parseObject(userListControllerIns.getUserList()), ans = new JSONObject();

        ans.put("ok", userInfo.getInteger("ok"));

        if (userInfo.getInteger("ok") == 1) {
            JSONArray userArray = userInfo.getJSONArray("userList"), resArray = new JSONArray();
            int length = userInfo.getInteger("userCount");

            for (int i = 0; i < length; i++) {
                JSONObject currentUser = userArray.getJSONObject(i), resUserInfo = new JSONObject();

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
