package com.c4demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 曾家华
 */
@RestController // ? 这里不是 REST api 入口， 为什么要 @RestController
// @ResponseBody
public class IndexController {
    @RequestMapping({"/", "/index"})
    public String index() {
        return "主页面";
    }
}
