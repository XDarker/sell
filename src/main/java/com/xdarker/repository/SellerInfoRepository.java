package com.xdarker.repository;

import com.xdarker.pojo.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XDarker
 * 2018/8/9 18:58
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}
