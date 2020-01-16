package com.zknu.shop.module.sell.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zknu.shop.common.mapper.OrdersMapper;
import com.zknu.shop.common.pojo.Orders;
import com.zknu.shop.common.pojo.OrdersExample;
import com.zknu.shop.common.util.PageResult;
import com.zknu.shop.module.sell.service.SellOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */
@Service
public class SellOrderServiceImpl implements SellOrderService {
    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public PageResult selectOrderListByUserId(int page, int row, Long userId) {
        OrdersExample orderExample = new OrdersExample();
        OrdersExample.Criteria criteria = orderExample.createCriteria();
        criteria.andOrderSellerIdEqualTo(userId);
        PageHelper.startPage(page,row);
        List<Orders> ordersList = ordersMapper.selectByExample(orderExample);
        PageInfo pageInfo = new PageInfo(ordersList);
        return  PageResult.buid(page,ordersList,pageInfo.getPages());
    }

    @Override
    public PageResult selectOrderListAction(int page, int row, Long userId, String action) {
        OrdersExample orderExample = new OrdersExample();
        OrdersExample.Criteria criteria = orderExample.createCriteria();
        criteria.andOrderSellerIdEqualTo(userId);

      if (!action.equals("00")){
            criteria.andOrderStatusEqualTo(action);}
        PageHelper.startPage(page,row);
        List<Orders> ordersList = ordersMapper.selectByExample(orderExample);
        PageInfo pageInfo = new PageInfo(ordersList);
        return  PageResult.buid(page,ordersList,pageInfo.getPages());
    }
}
