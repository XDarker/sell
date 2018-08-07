package com.xdarker.service.impl;

import com.xdarker.common.ProductStatusEnum;
import com.xdarker.pojo.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by XDarker
 * 2018/8/4 16:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {

        ProductInfo productInfo = productService.findOne("123");

    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        System.out.println(productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() {

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("120")
                .setProductName("皮皮虾")
                .setProductPrice(new BigDecimal(5.00))
                .setProductStock(100)
                .setProductDescription("味道还不错")
                .setProductIcon("http://xxxxx.jpg")
                .setProductStatus(ProductStatusEnum.DOWN.getCode())
                .setCategoryType(2);

        productService.save(productInfo);
    }
}