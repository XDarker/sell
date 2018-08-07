package com.xdarker.service;

import com.xdarker.pojo.ProductCategory;

import java.util.List;

/**
 * Created by XDarker
 * 2018/8/4 14:35
 */
public interface ICategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory>  findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}