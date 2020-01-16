package com.zknu.shop.module.sell.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zknu.shop.common.mapper.ProductMapper;
import com.zknu.shop.common.mapper.ProductimgMapper;
import com.zknu.shop.common.pojo.*;
import com.zknu.shop.common.util.IDUtils;
import com.zknu.shop.common.util.PageResult;
import com.zknu.shop.common.util.ShopResult;
import com.zknu.shop.module.sell.service.SellGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by abeg on 2017/8/18.
 */
@Service
public class SellGoodsServiceImpl implements SellGoodsService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductimgMapper productimgMapper;


    @Override
    public ShopResult insertGoods(HttpServletRequest request, Product product) {
        //直接插入 不用空值判断 空值判断在前台判断
        //商品货号 添加人 售出数量都要填入值
        User user = (User)request.getSession().getAttribute("user");
        product.setProductSellerId(user.getUserId());
        product.setProductNo(IDUtils.getOrdersn());
        product.setProductHassSelled(0);
        if(product.getProductDescription()==null)
        {
            product.setProductDescription("商家未进行详细介绍");
        }

        productMapper.insert(product);
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductNoEqualTo(product.getProductNo());
        List<Product> productList = productMapper.selectByExample(example);
        //商品图片另外添加  首先获取图片路径
        Map<Integer,String> list=(Map<Integer,String>) request.getSession().getAttribute("uploadimgcount");
        for(Map.Entry<Integer,String> entry:list.entrySet()){
            Productimg productimg = new Productimg();
            productimg.setImgurl(entry.getValue());
            productimg.setProductId(productList.get(0).getProductId());
            productimgMapper.insert(productimg);
        }
        //添加成功跳到主页
        return ShopResult.build(200,"增加成功",request.getContextPath()+"/adminimenu");
    }

    @Override
    public ShopResult updateGoods(HttpServletRequest request, Product product) {
        product.setProductPicture("不使用该字段");
        productMapper.updateByPrimaryKey(product);
        return ShopResult.build(200,"修改成功",request.getContextPath()+"/adminimenu");
    }

    @Override
    public PageResult selectUserAllProduct(HttpServletRequest request, int page, int row) {
        //首先获取到用户登陆信息
        User user = (User)request.getSession().getAttribute("user");
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductSellerIdEqualTo(user.getUserId());
        PageHelper.startPage(page,row);
        List<Product> list = productMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return PageResult.buid(page,list,pageInfo.getPages());
    }

    @Override
    public void updateGoodsXj(HttpServletRequest request, Long id, String status) {
        //首先根据商品id 拿出商品 然后再更新
        Product product = productMapper.selectByPrimaryKey(id);
        product.setProductIsSell(status);
        productMapper.updateByPrimaryKey(product);
    }

    @Override
    public ShopResult updatelittleGoods(HttpServletRequest request, int[] id, int status) {
        for (int i=0;i<id.length;i++)
        {
            if(status==2)
            {
                //状态为2是删除
                productMapper.deleteByPrimaryKey((long)id[i]);
            }else {
                Product product=productMapper.selectByPrimaryKey((long)id[i]);
                product.setProductIsSell(status+"");
                productMapper.updateByPrimaryKey(product);
            }

        }
        return ShopResult.build(200,"修改成功",request.getContextPath()+"/adminimenu");
    }
}
