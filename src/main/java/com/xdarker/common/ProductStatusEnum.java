package com.xdarker.common;

import lombok.Getter;

/**
 * 商品状态
 * Created by XDarker
 * 2018/8/4 16:44
 */
@Getter
public enum ProductStatusEnum {

    UP(0,"在架"),    //表示上架
    DOWN(1,"下架");  //表示下架
    private  final Integer code;

    private final String message;

    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
