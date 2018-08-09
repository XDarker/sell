package com.xdarker.controller;

import com.xdarker.common.ResultEnum;
import com.xdarker.expection.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * Created by XDarker
 * 2018/8/8 12:29
 */
@Controller
@Slf4j
@RequestMapping("/wechat")
public class WechatController {

    private final WxMpService wxMpService;
    @Autowired
    public WechatController(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //配置
        //调研方法
        String url = "http://xdarker.mynatapp.cc/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code，result={}",redirectUrl);

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl){

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
             wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        //获取openid
        String oprnid = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + oprnid;


    }

}
