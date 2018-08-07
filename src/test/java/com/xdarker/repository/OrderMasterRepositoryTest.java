package com.xdarker.repository;

import com.xdarker.pojo.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by XDarker
 * 2018/8/6 16:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;


    @Test
    public void saveTest(){

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1235");
        orderMaster.setBuyerName("XDarker");
        orderMaster.setBuyerPhone("18888888888");
        orderMaster.setBuyerAddress("杭州");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {

        PageRequest pageRequest = new PageRequest(0,1);

        Page<OrderMaster> result = repository.findByBuyerOpenid("110110",pageRequest);

        System.out.println(result.getTotalElements());


    }
}