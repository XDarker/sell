package com.xdarker.common;

import lombok.Getter;

/**
 * Created by XDarker
 * 2018/8/6 17:38
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存错诶"),

    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERTDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDERSTATUS_ERROR(14,"订单状态不正确"),
    ORDERSTATUS_UPDATE_ERROR(15,"订单状态更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单中无商品详情"),


    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
