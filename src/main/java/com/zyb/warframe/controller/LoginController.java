package com.zyb.warframe.controller;

import com.zyb.warframe.Result;
import com.zyb.warframe.pojo.User;
import com.zyb.warframe.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.logging.Logger;


@Controller
public class LoginController {
    @Autowired
    UserService userService;

    protected final Log logger = LogFactory.getLog(this.getClass());
    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUserName();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassWord());
        logger.info("登录的用户" + username);
        if (null == user) {
            logger.info("登录失败");
            return new Result(400, "失败");
        } else {
            logger.info("登录成功");
            return new Result(200, "成功");
        }
    }
}
