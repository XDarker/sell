package com.xdarker.service.impl;

import com.xdarker.pojo.ProductCategory;
import com.xdarker.repository.ProductCategoryRepository;
import com.xdarker.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XDarker
 * 2018/8/4 14:39
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    private final ProductCategoryRepository repository;
    @Autowired
    public CategoryServiceImpl(ProductCategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}

