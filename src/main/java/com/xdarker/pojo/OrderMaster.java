package com.xdarker.pojo;

import com.xdarker.common.OrderStatusEnum;
import com.xdarker.common.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by XDarker
 * 2018/8/6 16:09
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

   /** 订单ID */
   @Id  //主键
    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 买家手机号 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信Openid */
    private String buyerOpenid;

    /** 订单金额 */
    private BigDecimal orderAmount;

    /** 订单状态 默认新下单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态  默认0未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 创建时间 */
    @CreationTimestamp
    private Date createTime;
    /** 更新时间 */
    @UpdateTimestamp
    private Date updateTime;

//    @Transient
//    private List<OrderDetail> orderDetailList;
}
