package com.xdarker.common;

import lombok.Getter;

/**
 * Created by XDarker
 * 2018/8/6 17:38
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存错误"),

    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERTDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDERSTATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单状态更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单中无商品详情"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),
    CART_EMPTY(18, "购物车为空"),
    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),


    PARAM_ERROR(1, "参数不正确"),


    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
