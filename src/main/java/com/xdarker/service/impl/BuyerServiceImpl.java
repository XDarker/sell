package com.xdarker.service.impl;

import com.xdarker.common.ResultEnum;
import com.xdarker.dto.OrderDTO;
import com.xdarker.expection.SellException;
import com.xdarker.service.IBuyerService;
import com.xdarker.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XDarker
 * 2018/8/7 15:56
 */
@Service("iBuyerService")
@Slf4j
public class BuyerServiceImpl implements IBuyerService {

    private final IOrderService iOrderService;
    @Autowired
    public BuyerServiceImpl(IOrderService iOrderService) {
        this.iOrderService = iOrderService;
    }


    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = iOrderService.findOne(orderId);

        if (orderDTO == null){
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的opernid不一致. openid={}, orderDTO",openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到改订单, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return iOrderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = iOrderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
