package com.xdarker.service.impl;

import com.xdarker.dto.OrderDTO;
import com.xdarker.service.IPayService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by XDarker
 * 2018/8/8 19:14
 */
public class PayServiceImplTest {

    @Autowired
    private IPayService iPayService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        iPayService.create(orderDTO);
    }
}