package com.xdarker.service;

import com.xdarker.dto.OrderDTO;

/**
 * 买家
 * Created by XDarker
 * 2018/8/7 15:54
 */
public interface IBuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
