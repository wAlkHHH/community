package com.walkhhh.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-07 8:52
 */
@Controller
public class indexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
