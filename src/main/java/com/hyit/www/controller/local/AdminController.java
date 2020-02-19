package com.hyit.www.controller.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 管理账号
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * 用户注册
     */
    @RequestMapping(value = "/register")
    public String register() {
        return "admin/register";
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "admin/login";
    }

    /**
     * 用户修改密码
     */
    @RequestMapping(value = "/changepwd")
    public String changePwd() {
        return "admin/changepwd";
    }
}
