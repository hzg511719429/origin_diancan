package com.zknu.shop.module.user.service;

import com.zknu.shop.common.pojo.Orders;
import com.zknu.shop.common.util.PageResult;
import com.zknu.shop.module.user.vo.OrderAndUser;

/**
 * Created by Administrator on 2017/8/22.
 */
public interface UserService {
    /**
     *
     * 查找订单列表
     */
    PageResult selectOrderListByUserId(int page, int row, Long userId);
    /**
     *订单
     * 更新
     */

    void updateOrder(Orders orders);
    /**
     *
     * 删除订单
     */
    void deleteOrderByOrderId(long id);
    /**
     *
     * 订单 详情
     */
    OrderAndUser selectitemByOrderId(long id);
    /**
     *
     * 通过订单id查找订单
     */
    Orders selectOrderByOrderId(long id);
}
