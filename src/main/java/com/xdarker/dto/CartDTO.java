package com.xdarker.dto;

import lombok.Data;

/**
 * Created by XDarker
 * 2018/8/6 18:14
 */
@Data
public class CartDTO {

    /** 商品Id */
    private String productId;

    /** 数量 */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
