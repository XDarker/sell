package com.xdarker.expection;

import com.xdarker.common.ResultEnum;

/**
 * Created by XDarker
 * 2018/8/6 17:37
 */
public class SellException  extends  RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
