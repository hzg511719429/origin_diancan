package com.zknu.shop.module.sell.service;

import com.zknu.shop.common.util.PageResult;

/**
 * Created by Administrator on 2017/8/23.
 */
public interface SellOrderService {
    /**
     *
     *  商家订单列表
     */
    PageResult selectOrderListByUserId(int page, int row, Long userId);
    /**
     *
     *  条件商家订单列表
     */
    PageResult selectOrderListAction(int page, int row, Long userId, String action);
}
