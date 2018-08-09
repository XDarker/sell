package com.xdarker.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by XDarker
 * 2018/8/8 12:46
 */
@Configuration
public class WechatMpConfig {


    private final WechatAccountConfig wechatAccountConfig;

    @Autowired
    public WechatMpConfig(WechatAccountConfig wechatAccountConfig) {
        this.wechatAccountConfig = wechatAccountConfig;
    }


    @Bean
    public WxMpService wxMpService(){

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(wechatAccountConfig.getMpAppId());
        wxMpInMemoryConfigStorage.setSecret(wechatAccountConfig.getMpAppSecret());
        return wxMpInMemoryConfigStorage;
    }
}
