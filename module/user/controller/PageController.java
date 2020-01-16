package com.zknu.shop.module.user.controller;

import com.zknu.shop.common.pojo.Product;
import com.zknu.shop.common.util.PageResult;
import com.zknu.shop.module.user.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by abeg on 2017/8/17.
 */
@Controller
public class PageController {

    @Autowired
    GoodsService goodsService;
    /**
     * 跳转主页
     * @return
     */
    @RequestMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1")int page,
                        @RequestParam(defaultValue = "10")int row,
                        @RequestParam(defaultValue = "0")int status,
                        @RequestParam(defaultValue = "100000.00")double max,
                        @RequestParam(defaultValue = "0.00")double min,
                        @RequestParam(defaultValue = "") String word){
        //跳转到主页 获取商品信息
        //拿到商品信息  拿出来前10个吧
        PageResult pageResult =goodsService.selectGoodsByTj(page,row,status,max,min,word);
        model.addAttribute("pageResult",pageResult);
        model.addAttribute("status",status);
        model.addAttribute("max",max);
        model.addAttribute("min",min);
        return "/index";
    }
    /**
     * 跳转主页
     * @return
     */
    @RequestMapping("/index")
    public String goindex(Model model, @RequestParam(defaultValue = "1")int page,
                        @RequestParam(defaultValue = "10")int row,
                        @RequestParam(defaultValue = "0")int status,
                        @RequestParam(defaultValue = "100000.00")double max,
                        @RequestParam(defaultValue = "0.00")double min,
                          @RequestParam(defaultValue = "") String word){
        //跳转到主页 获取商品信息
        //拿到商品信息  拿出来前10个吧
        PageResult pageResult =goodsService.selectGoodsByTj(page,row,status,max,min,word);
        model.addAttribute("pageResult",pageResult);
        model.addAttribute("status",status);
        model.addAttribute("max",max);
        model.addAttribute("min",min);
        model.addAttribute("word",word);
        return "/index";
    }
}
