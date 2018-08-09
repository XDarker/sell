package com.xdarker.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by XDarker
 * 2018/8/8 19:02
 */
@Configuration
public class WechatPayConfig {

    private final WechatAccountConfig wechatAccountConfig;
    @Autowired
    public WechatPayConfig(WechatAccountConfig wechatAccountConfig) {
        this.wechatAccountConfig = wechatAccountConfig;
    }


    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatAccountConfig.getMpAppId());
        wxPayH5Config.setAppSecret(wechatAccountConfig.getMpAppSecret());
        wxPayH5Config.setMchId(wechatAccountConfig.getMchId());
        wxPayH5Config.setMchKey(wechatAccountConfig.getMchKey());
//        wxPayH5Config.setKeyPath(wechatAccountConfig.getKeyPath());
//        wxPayH5Config.setNotifyUrl(wechatAccountConfig.getNotifyUrl());
        return wxPayH5Config;
    }


}
