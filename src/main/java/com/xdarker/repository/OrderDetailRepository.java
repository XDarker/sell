package com.xdarker.repository;

import com.xdarker.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by XDarker
 * 2018/8/6 16:35
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {


    List<OrderDetail> findByOrderId(String orderId);
}
