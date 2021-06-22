package com.c4demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 曾家华
 */
@RestController
public class IndexController {
    @RequestMapping({"/", "/index"})
    public String index() {
        return "主页面";
    }
}
