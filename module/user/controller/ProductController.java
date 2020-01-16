package com.zknu.shop.module.user.controller;

import com.zknu.shop.common.pojo.Product;
import com.zknu.shop.common.pojo.Productimg;
import com.zknu.shop.module.user.service.GoodsService;
import com.zknu.shop.module.user.util.PortalResult;
import com.zknu.shop.module.user.vo.CarImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by abeg on 2017/8/21.
 * 商品详情页的展示
 */
@Controller
public class ProductController {

    @Autowired
    GoodsService goodsService;


    /**
     * 根据传入的商品id  查询信息 绑定到页面上
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "goods")
    public String seleteGoodsId(Model model,Long id)
    {
        Product product = goodsService.seleteGoodsByOne(id);
        model.addAttribute("goods",product);
        List<Productimg> list = goodsService.seleteGoodsImg(id);
        if(list.size()==0)
        {
            Productimg productimg = new Productimg();
            productimg.setImgurl("static/img/defult.jpg");
            list.add(productimg);
            model.addAttribute("goodsImgList",list);
        }else {
            model.addAttribute("goodsImgList",list);
        }

        return "goods";
    }
}
