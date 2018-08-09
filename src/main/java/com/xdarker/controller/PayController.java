package com.xdarker.controller;

import com.xdarker.common.ResultEnum;
import com.xdarker.dto.OrderDTO;
import com.xdarker.expection.SellException;
import com.xdarker.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by XDarker
 * 2018/8/8 17:03
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    private final IOrderService iOrderService;

    @Autowired
    public PayController(IOrderService iOrderService) {
        this.iOrderService = iOrderService;
    }

    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId, @RequestParam("returnUrl") String retuenUrl){

        //查询订单
        OrderDTO orderDTO = iOrderService.findOne(orderId);
        if(orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }



    }
}
