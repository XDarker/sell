package com.xdarker.vo;

import lombok.Data;

import java.io.Serializable;

/** HTTP 请求返回的最外层对象
 * Created by XDarker
 * 2018/8/4 17:17
 */

@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 3236329195874147801L;

    /** 错误码 */
    private Integer code;
    /** 提示信息 */
    private String msg;
    /** 返回的具体内容 */
    private T data;

}
