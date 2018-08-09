package com.xdarker.service.impl;

import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.service.BestPayService;
import com.xdarker.dto.OrderDTO;
import com.xdarker.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XDarker
 * 2018/8/8 17:10
 */
@Service("iPayService")
public class PayServiceImpl implements IPayService {

    private final BestPayService bestPayService;
    @Autowired
    public PayServiceImpl(BestPayService bestPayService) {
        this.bestPayService = bestPayService;
    }

    @Override
    public void create(OrderDTO orderDTO) {

        PayRequest payRequest = new PayRequest();
        bestPayService.pay(payRequest);
    }
}
