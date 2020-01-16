package com.zknu.shop.module.user.service;

import com.zknu.shop.common.pojo.Car;
import com.zknu.shop.common.pojo.Product;
import com.zknu.shop.common.pojo.Productimg;
import com.zknu.shop.common.util.PageResult;
import com.zknu.shop.module.user.util.PortalResult;
import com.zknu.shop.module.user.vo.CarImg;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by abeg on 2017/8/21.
 * 首页的商品展示 以及详情页的展示
 */
public interface GoodsService {

    /**
     * 根据用户选择条件 进行组合查询
     * @param page
     * @param rows
     * @param status
     * @param max
     * @param min
     * @return
     */
    PageResult selectGoodsByTj(int page,int rows,int status,double max,double min,String word);


    /**
     * 根据goodsID查询单个商品
     * @param id
     * @return
     */
    Product seleteGoodsByOne(long id);

    /**
     * 根据传入的商品id 查询出商品图片
     * @param id
     * @return
     */
    List<Productimg> seleteGoodsImg(long id);

    /**
     * 传入图片地址和id 插入图片
     * @param request
     * @param id
     */
    Productimg insertProductImg(HttpServletRequest request,Long id,String imgurl);

    /**
     * 传入图片地址和id  删除图片
     * @param request
     * @param id
     */
    void deleteProductImg(HttpServletRequest request,Long id);

}
