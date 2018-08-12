package com.xdarker.service;

import com.xdarker.pojo.SellerInfo;
import com.xdarker.repository.SellerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XDarker
 * 2018/8/9 19:10
 */
@Service("iSellerService")
public class SellerServiceImpl implements ISellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}

