package com.xdarker.form;

import lombok.Data;

/**
 * Created by XDarker
 * 2018/8/9 16:35
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
