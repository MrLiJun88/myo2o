package com.hyit.www.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author LiJun
 * @Date 2020/2/12 22:09
 */

@Controller
@RequestMapping(value = "/front")
public class FrontendController {
    /**
     * 前端首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "front/index";
    }
}
