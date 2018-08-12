package com.xdarker.handler;

import com.xdarker.exception.SellException;
import com.xdarker.utils.ResultVOUtil;
import com.xdarker.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by XDarker
 * 2018/8/9 20:05
 */
@ControllerAdvice
public class SellExceptionHandler {


    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){

        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
