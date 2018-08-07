package com.xdarker.utils;

import java.util.Random;

/**
 * Created by XDarker
 * 2018/8/6 17:51
 */
public class KeyUtil {

    /**
     * 生产唯一主键
     * 格式：时间 + 随机数
     * @return
     */
    public static synchronized String  getUniqueKey(){

        Random random = new Random();
        /** 生成六位随机数 */
        Integer number = random.nextInt(900000) + 100000;

        return  System.currentTimeMillis() + String.valueOf(number);


    }
}
