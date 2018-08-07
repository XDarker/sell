package com.xdarker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by XDarker
 * 2018/8/7 21:42
 */
@RestController
@RequestMapping("/weixin")
public class WeixinController {

    @GetMapping("")
    public void auth(){

    }
}
