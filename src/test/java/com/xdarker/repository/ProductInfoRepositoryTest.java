package com.xdarker.repository;

import com.xdarker.pojo.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by XDarker
 * 2018/8/4 16:05
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;


    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123")
        .setProductName("皮蛋粥")
        .setProductPrice(new BigDecimal(5.00))
        .setProductStock(100)
        .setProductDescription("味道还不错")
        .setProductIcon("http://xxxxx.jpg")
        .setProductStatus(0)
        .setCategoryType(2);

        ProductInfo result  = repository.save(productInfo);
    }


    @Test
    public void findByProductStatus() {

        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        System.out.println(productInfoList.size());
    }
}