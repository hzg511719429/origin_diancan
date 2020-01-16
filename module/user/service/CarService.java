package com.zknu.shop.module.user.service;

import com.zknu.shop.common.pojo.Orders;
import com.zknu.shop.module.user.util.PortalResult;
import com.zknu.shop.module.user.vo.CarImg;
import com.zknu.shop.module.user.vo.UpdateCar;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by abeg on 2017/8/22.
 * 购物车 订单生成
 */
public interface CarService {
    /**
     * 根据传入的信息 把商品加入购物车
     * @param request
     * @param number
     * @return
     */
    PortalResult addcart(HttpServletRequest request, Long id, double price, int number, int status);

    /**
     * 从主页点击加入购物车
     * @param request
     * @param id
     */
    void addindexCart(HttpServletRequest request,Long id);

    /**
     * 根据session中的user 查询出 他购物车中所有的东西
     * @param request
     * @return
     */
    List<CarImg> seleteCatByUserAll(HttpServletRequest request);

    /**
     * 根据用户选择的商品 进行数量和价格的更新
     * @param request
     * @return
     */
    UpdateCar updatecarprice(HttpServletRequest request,Long[] cartId);

    /**
     * 根据传入的id删除一条记录
     * @param id
     */
    void deletecarById(Long id);

    /**
     * 增加新地址 不设为默认
     * @param request
     * @param address
     * @return
     */
    PortalResult addAddress(HttpServletRequest request,String address);

    /**
     * 根据传入的number 设置默认收货地址
     * @param request
     * @param number
     */
    void updateAddress(HttpServletRequest request,int number);

    /**
     * 确认订单
     * @param request
     * @param sc_date
     * @param paytype
     * @param message
     */
    List<Orders> insertconfirmOrder(HttpServletRequest request, String sc_date, int paytype, String message, double sumprice);

    /**
     * 根据传入的sn 获取到这一批的总金额
     *
     * @param order
     * @return
     */
    double selectOrderByuser(Long order);

    /**
     * 支付成功后毁掉逻辑
     * @param all
     * @param request
     * @param orderSn
     */
    void  updateOrderStatus(int all,HttpServletRequest request,Long[] orderSn);
}
