package com.xdarker.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/** 商品（包含类目）
 * Created by XDarker
 * 2018/8/4 17:22
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 3226073569081191364L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
