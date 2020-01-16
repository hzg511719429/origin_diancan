package com.zknu.shop.module.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zknu.shop.common.mapper.CarMapper;
import com.zknu.shop.common.mapper.ProductMapper;
import com.zknu.shop.common.mapper.ProductimgMapper;
import com.zknu.shop.common.pojo.*;
import com.zknu.shop.common.util.PageResult;
import com.zknu.shop.module.user.service.GoodsService;
import com.zknu.shop.module.user.util.PortalResult;
import com.zknu.shop.module.user.vo.CarImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abeg on 2017/8/21.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductimgMapper productimgMapper;
    @Autowired
    CarMapper carMapper;
    @Override
    public PageResult selectGoodsByTj(int page, int rows, int status, double max, double min,String word) {
        //根据输入条件 进行组合查询
        ProductExample e=new ProductExample();
        ProductExample.Criteria c = e.createCriteria();

        //只有最高价格和最低价格都确定的时候 才加入查询
        c.andProductPriceBetween(min,max);
        //上架的才显示 不上架的不显示
        c.andProductIsSellEqualTo("1");
        if(word!=null)
        {
            c.andProductNameLike("%"+word+"%");
        }
        //如果为0 说明是默认  如果为1或2 就是按照最新或最热
        if(status!=0)
        {
            if(status==1)
            {
                //按照最新查询
                e.setOrderByClause("product_id DESC");
            }else {
                //按照卖出最多查询
                e.setOrderByClause("product_hass_selled DESC");
            }
        }
        PageHelper.startPage(page,rows);
        List<Product> list = productMapper.selectByExample(e);
        PageInfo pageInfo = new PageInfo(list);
        for(Product p :list)
        {
            ProductimgExample example = new ProductimgExample();
            ProductimgExample.Criteria criteria = example.createCriteria();
            criteria.andProductIdEqualTo(p.getProductId());
            List<Productimg> productimgList=productimgMapper.selectByExample(example);
            if(productimgList.size()==0)
            {
                p.setProductPicture("static/img/defult.jpg");
            }else {
                p.setProductPicture(productimgList.get(0).getImgurl());
            }

        }
        return PageResult.buid(page,list,pageInfo.getPages());
    }

    @Override
    public Product seleteGoodsByOne(long id) {
        //根据插入的商品id 查找
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }

    @Override
    public List<Productimg> seleteGoodsImg(long id) {
        ProductimgExample example = new ProductimgExample();
        ProductimgExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(id);

        return  productimgMapper.selectByExample(example);
    }

    @Override
    public Productimg insertProductImg(HttpServletRequest request, Long id, String imgurl) {
        Productimg productimg = new Productimg();
        productimg.setImgurl(imgurl);
        productimg.setProductId(id);
        productimgMapper.insert(productimg);
        ProductimgExample example = new ProductimgExample();
        ProductimgExample.Criteria criteria = example.createCriteria();
        criteria.andImgurlEqualTo(imgurl);
        criteria.andProductIdEqualTo(id);
        return  productimgMapper.selectByExample(example).get(0);
    }

    @Override
    public void deleteProductImg(HttpServletRequest request, Long id) {
        productimgMapper.deleteByPrimaryKey(id);
    }

}
