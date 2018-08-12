package com.xdarker.service.impl;

import com.xdarker.pojo.ProductCategory;
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
 * 2018/8/4 14:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private  CategoryServiceImpl categoryService;


    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        System.out.println(productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list  = Arrays.asList(2,3);

        List<ProductCategory> result = categoryService.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生专享");
        productCategory.setCategoryType(1);
        ProductCategory result = categoryService.save(productCategory);

    }
}