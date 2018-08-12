package com.xdarker.service;

import com.xdarker.pojo.SellerInfo;

/**
 * 卖家端
 * Created by XDarker
 * 2018/8/9 19:08
 */
public interface ISellerService {
    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
