package com.xdarker.utils;

import com.xdarker.common.CodeEnum;

/**
 * Created by XDarker
 * 2018/8/8 21:02
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
