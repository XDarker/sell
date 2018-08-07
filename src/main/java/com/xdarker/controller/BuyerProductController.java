package com.xdarker.controller;

import com.xdarker.pojo.ProductCategory;
import com.xdarker.pojo.ProductInfo;
import com.xdarker.service.ICategoryService;
import com.xdarker.service.IProductService;
import com.xdarker.utils.ResultVOUtil;
import com.xdarker.vo.ProductInfoVO;
import com.xdarker.vo.ProductVO;
import com.xdarker.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * Created by XDarker
 * 2018/8/4 17:12
 */

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    private final IProductService iProductService ;
    private final ICategoryService iCategoryService;

    @Autowired
    public BuyerProductController(IProductService iProductService, ICategoryService iCategoryService) {
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
    }


    @GetMapping("/list")
    public ResultVO list(){

        //1.查询所有上架商品
        List<ProductInfo> productInfoList = iProductService.findUpAll();

        //2.查询类目(一次性查询)
//        List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
//        for (ProductInfo productInfo : productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //精简方法(java8 ,lambda)
        List<Integer> categoryTypeList = productInfoList.stream().
                map(e -> e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productCategoryList = iCategoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据封装
        List<ProductVO> productVOList = new ArrayList<ProductVO>();

        for (ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>();
            for (ProductInfo productInfo : productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);

    }


}
