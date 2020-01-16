package com.zknu.shop.module.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zknu.shop.common.mapper.*;
import com.zknu.shop.common.pojo.*;
import com.zknu.shop.common.util.PageResult;
import com.zknu.shop.module.user.service.UserService;
import com.zknu.shop.module.user.vo.ItemAndProduct;
import com.zknu.shop.module.user.vo.OrderAndUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */
@Controller
public class UserServiceImpl implements UserService {
    @Autowired
    OrdersMapper orderMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    ProductMapper productMapper;
    @Override
    public PageResult selectOrderListByUserId(int page, int row, Long userId) {
        OrdersExample orderExample = new OrdersExample();
        OrdersExample.Criteria criteria = orderExample.createCriteria();
        criteria.andOrderBuyerIdEqualTo(userId);

        PageHelper.startPage(page,row);
        List<Orders> ordersList = orderMapper.selectByExample(orderExample);
        PageInfo pageInfo = new PageInfo(ordersList);
        return  PageResult.buid(page,ordersList,pageInfo.getPages());
    }



    @Override
    public void updateOrder(Orders orders) {

        orderMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public void deleteOrderByOrderId(long id) {
        ItemExample itemExample = new ItemExample();
        ItemExample.Criteria criteria = itemExample.createCriteria();
        criteria.andItemOrderIdEqualTo(id);
        itemMapper.deleteByExample(itemExample);


        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OrderAndUser selectitemByOrderId(long id) {

          OrderAndUser orderAndUser = new OrderAndUser();
           Orders orders = orderMapper.selectByPrimaryKey(id);

        orderAndUser.setOrderId(orders.getOrderId());
        orderAndUser.setOrderBuyerId(orders.getOrderBuyerId());
        orderAndUser.setOrderSellerId(orders.getOrderSellerId());
        orderAndUser.setOrderNo(orders.getOrderNo());
        orderAndUser.setOrderAddress(orders.getOrderAddress());
        orderAndUser.setOrderTime(orders.getOrderTime());
        orderAndUser.setOrderDelieveTime(orders.getOrderDelieveTime());
        orderAndUser.setOrderFinishTime(orders.getOrderFinishTime());
        orderAndUser.setOrderTotalPrice(orders.getOrderTotalPrice());
        orderAndUser.setOrderPayment(orders.getOrderPayment());
        orderAndUser.setOrderStatus(orders.getOrderStatus());
        orderAndUser.setOrderComments(orders.getOrderComments());
        //
        User user = userMapper.selectByPrimaryKey(orders.getOrderBuyerId());
        orderAndUser.setName(user.getUserNickname());
        orderAndUser.setPhone(user.getUserPhone());

        List<ItemAndProduct> itemAndProductList = new ArrayList<>();

        ItemExample itemExample = new ItemExample();
        ItemExample.Criteria criteria = itemExample.createCriteria();
        criteria.andItemOrderIdEqualTo(orders.getOrderId());
        List<Item> items = itemMapper.selectByExample(itemExample);
        for (Item i : items){
            ItemAndProduct itemAndProduct = new ItemAndProduct();
            itemAndProduct.setItemProductNum(i.getItemProductNum());
            itemAndProduct.setItemId(i.getItemId());
            itemAndProduct.setItemOrderId(i.getItemOrderId());
            itemAndProduct.setItemProductId(i.getItemProductId());
            itemAndProduct.setItemProductPrice(i.getItemProductPrice());

            Product product = productMapper.selectByPrimaryKey(i.getItemProductId());
            itemAndProduct.setProductName(product.getProductName());
            itemAndProduct.setProductPrice(product.getProductPrice());


            itemAndProductList.add(itemAndProduct);
        }

        orderAndUser.setItemAndProductList(itemAndProductList);

        return orderAndUser;
    }

    @Override
    public Orders selectOrderByOrderId(long id) {


        return orderMapper.selectByPrimaryKey(id);
    }
}
