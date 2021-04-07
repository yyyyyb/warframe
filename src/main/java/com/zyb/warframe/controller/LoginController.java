package com.zyb.warframe.controller;

import com.zyb.warframe.Result;
import com.zyb.warframe.pojo.User;
import com.zyb.warframe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;


@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUserName();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassWord());
        if (null == user) {
            return new Result(400, "失败");
        } else {
            return new Result(200, "成功");
        }

    }
}
