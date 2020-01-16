package com.zknu.shop.module.sell.service;

import com.zknu.shop.common.pojo.Product;
import com.zknu.shop.common.util.PageResult;
import com.zknu.shop.common.util.ShopResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by abeg on 2017/8/18.
 * 进行商家商品的增删改查
 */
public interface SellGoodsService {
    /**
     * 增加商品
     * @param request
     * @param product
     */
    ShopResult insertGoods(HttpServletRequest request, Product product);

    /**
     * 修改商品
     * @param request
     * @param product
     */
    ShopResult updateGoods(HttpServletRequest request, Product product);

    /**
     * 根据用户登陆信息 查询该用户下有多少商品 全部展示
     * @param request
     * @return
     */
    PageResult selectUserAllProduct(HttpServletRequest request, int page, int row);

    /**
     * 根据商品 id 进行上下架
     * @param request
     * @param id
     */
    void updateGoodsXj(HttpServletRequest request,Long id,String status);

    /**
     * 根据传入的数组进行状态改变
     * @param request
     * @param id
     * @param status
     * @return
     */
    ShopResult updatelittleGoods(HttpServletRequest request,int[] id,int status);


}
