package com.kfhstu.furn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author KFH
 * @version 1.0
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
