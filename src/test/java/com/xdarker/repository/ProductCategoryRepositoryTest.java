package com.xdarker.repository;

import com.xdarker.pojo.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by XDarker
 * 2018/8/4 13:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;


    @Test
    public void findOneTest(){
        // SpringBoot版本问题 将2.0版本 换成 1.5.4即可
        //repository.findOne(1);

        //不换版本, 改成这种写法
       ProductCategory productCategory = repository.findById(1).orElse(null);

        System.out.println(productCategory.toString());
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(3);

        repository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest(){

        List<Integer> list  = Arrays.asList(2,3);

        List<ProductCategory> result = repository.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0, result.size());

    }
}