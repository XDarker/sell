package com.xdarker.service.impl;

import com.xdarker.common.OrderStatusEnum;
import com.xdarker.common.PayStatusEnum;
import com.xdarker.common.ResultEnum;
import com.xdarker.converter.OrderMaster2OrderDTOConverter;
import com.xdarker.dto.CartDTO;
import com.xdarker.dto.OrderDTO;
import com.xdarker.expection.SellException;
import com.xdarker.pojo.OrderDetail;
import com.xdarker.pojo.OrderMaster;
import com.xdarker.pojo.ProductInfo;
import com.xdarker.repository.OrderDetailRepository;
import com.xdarker.repository.OrderMasterRepository;
import com.xdarker.service.IOrderService;
import com.xdarker.service.IProductService;
import com.xdarker.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by XDarker
 * 2018/8/6 17:27
 */
@Service("iOrderService")
@Slf4j
public class OrderServiceImpl implements IOrderService {

    private IProductService iProductService;
    private OrderDetailRepository orderDetailRepository;
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    public OrderServiceImpl(IProductService iProductService,
                            OrderDetailRepository orderDetailRepository,
                            OrderMasterRepository orderMasterRepository) {
        this.iProductService = iProductService;
        this.orderDetailRepository = orderDetailRepository;
        this.orderMasterRepository = orderMasterRepository;
    }

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

//        List<CartDTO> cartDTOList = new ArrayList<CartDTO>();


        //1.查询商品(数量 价格)
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
                ProductInfo productInfo = iProductService.findOne(orderDetail.getProductId());
                //检查数据库中商品信息是否存在
                if(productInfo == null){
                    throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
                }
                //计算订单总价
               orderAmount =  productInfo.getProductPrice()
                       .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                       .add(orderAmount);

            //属性拷贝  productInfo -> orderDetail
            BeanUtils.copyProperties(productInfo,orderDetail);

            //订单详情入库(orderDetail表)
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);

            orderDetailRepository.save(orderDetail);

//                CartDTO cartDTO  = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//                cartDTOList.add(cartDTO);


        }


        //写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);

        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);

        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);

        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());

        iProductService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERTDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);

        //判断订单状态
        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw  new SellException(ResultEnum.ORDERSTATUS_ERROR);
        }
        //修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【取消订单】订单状态更新失败, orderMaster={}", orderMaster);
            throw  new SellException(ResultEnum.ORDERSTATUS_UPDATE_ERROR);
        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情, orderMaster={}", orderMaster);
            throw  new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());

        iProductService.increaseStock(cartDTOList);

        //如果已支付，需要退款
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {


               return null;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}
