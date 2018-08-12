package com.xdarker.pojo.mapper;

import com.xdarker.pojo.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by XDarker
 * 2018/8/9 20:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap();
        map.put("category_name","杭州特色");
        map.put("category_type",10);

        int result = mapper.insertByMap(map);
        log.info("result={}",result);
    }

    @Test
    public void insertByObject() {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("最便宜");
        productCategory.setCategoryType(8);


        int result = mapper.insertByObject(productCategory);

    }

    @Test
    public void selectByCategoryType(){
        ProductCategory result = mapper.selectByCategoryType(10);
        log.info("result={}",result);
    }
}