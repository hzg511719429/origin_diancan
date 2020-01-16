package com.zknu.shop.module.sell.controller;

import com.zknu.shop.common.pojo.Product;
import com.zknu.shop.common.pojo.Productimg;
import com.zknu.shop.common.util.DateUtils;
import com.zknu.shop.common.util.PageResult;
import com.zknu.shop.common.util.ShopResult;
import com.zknu.shop.module.sell.service.SellGoodsService;
import com.zknu.shop.module.user.service.CarService;
import com.zknu.shop.module.user.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * Created by abeg on 2017/8/18.
 */
@Controller
public class SellGoodsController {
    @Autowired
    SellGoodsService sellGoodsService;
    @Autowired
    GoodsService goodsService;

    /**
     * 添加商品
     * @param request
     * @param product
     * @return
     */
    @RequestMapping("/insertGoods")
    @ResponseBody
    public ShopResult insertGoods(HttpServletRequest request, Product product){
        return sellGoodsService.insertGoods(request,product);
    }

    /**
     * 修改商品
     * @param request
     * @param product
     * @return
     */
    @RequestMapping("/updateGoods")
    public String updateGoods(HttpServletRequest request, Product product){
         sellGoodsService.updateGoods(request,product);
        return"redirect:adminimenu";
    }

    /**
     * 查询当前登陆用户的下属商品
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/adminimenu")
    public  String sellgoods(HttpServletRequest request, Model model,
                             @RequestParam(defaultValue = "1")int page,
                             @RequestParam(defaultValue = "10")int row){
        //判断是否登陆  没有就跳转登陆页面
        if(request.getSession().getAttribute("user")!=null){

            PageResult pageResult=sellGoodsService.selectUserAllProduct(request,page,row);
            model.addAttribute("pageResult",pageResult);
            return "sell/sellgoods";
        }else{
            return "login";
        }

    }
    @RequestMapping("/updateSellStatus")
    public  String updateSellStatus(HttpServletRequest request,Long id,String status, Model model){
        //判断是否登陆  没有就跳转登陆页面
        sellGoodsService.updateGoodsXj(request,id,status);
        PageResult pageResult=sellGoodsService.selectUserAllProduct(request,1,10);
        model.addAttribute("pageResult",pageResult);
        return "sell/sellgoods";
    }
    /**
     * 表单上传方式
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/uploadMulit")
    @ResponseBody
    public ShopResult upload(MultipartFile file,int count, HttpServletRequest request) {
        //保存
        if(file==null)
        {
            return ShopResult.build(500, "请选择上传的图片");
        }
        try {
            Map<Integer,String> session_list=(Map<Integer,String>) request.getSession().getAttribute("uploadimgcount");

            if(count==0)
            {
                request.getSession().setAttribute("uploadimgcount",null);
            }else {
                if(session_list.size()>5)
                {
                    return ShopResult.build(500, "不能超过六张");
                }
            }
            String basepath = request.getSession().getServletContext().getRealPath("/");
            String contentType = file.getContentType();
            //如果是文件保存在/data/files 图片保存在/data/images
            String path = contentType.contains("image") ? "data/images/" + DateUtils.format(new Date(), "YYYYMMdd") : "data/files/" + DateUtils.format(new Date(), "YYYYMMdd");
            //String fileName = file.getOriginalFilename();容易重名覆盖
            String fileName = new Date().getTime()+".jpg";
            File targetFile = new File(basepath + path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            Map<Integer,String> list=(Map<Integer,String>) request.getSession().getAttribute("uploadimgcount");
            if(list==null)
            {
                list=new HashMap<>();
            }
            list.put(count,path + "/" + fileName);
            request.getSession().setAttribute("uploadimgcount",list);
            return ShopResult.build(200, count+"", path + "/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ShopResult.build(500, "上传失败");
        }

    }
    @RequestMapping("/delimgurl")
    @ResponseBody
    public ShopResult upload(int index_imgurl, HttpServletRequest request) {
        Map<Integer,String> session_list=(Map<Integer,String>) request.getSession().getAttribute("uploadimgcount");
        session_list.remove(index_imgurl);
        return ShopResult.build(200,"删除成功");
    }
    /**
     * 根据传入的数组进行状态改变
     * @param request
     * @param ids
     * @param status
     * @return
     */
    @RequestMapping("/updatelittleGoods")
    @ResponseBody
    public ShopResult updatelittleGoods(HttpServletRequest request,int ids[],int status){
        return sellGoodsService.updatelittleGoods(request,ids,status);
    }

    /**
     * 跳转商品修改页面
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/updategoods",method = RequestMethod.GET)
    public String goupdategoodsView(HttpServletRequest request,Model model,long id){
        Product product = goodsService.seleteGoodsByOne(id);
        model.addAttribute("product",product);
        List<Productimg> list = goodsService.seleteGoodsImg(id);
        model.addAttribute("productImg",list);
        //把图片加入到session中
        Map<Integer,String> map = new HashMap<>();
        for (Productimg productimg : list)
        {
            long count=productimg.getId();
            map.put((int) count,productimg.getImgurl());
        }
        return "sell/updategoods";
    }

    /**
     * 表单上传方式
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/UpdateuploadMulit")
    @ResponseBody
    public ShopResult updateuploadMulit(MultipartFile file,Long goodid, HttpServletRequest request) {
        //保存
        if(file==null)
        {
            return ShopResult.build(500, "请选择上传的图片");
        }
        try {
            List<Productimg> session_list=goodsService.seleteGoodsImg(goodid);
                if(session_list.size()>6)
                {
                    return ShopResult.build(500, "不能超过六张");
                }
            String basepath = request.getSession().getServletContext().getRealPath("/");
            String contentType = file.getContentType();
            //如果是文件保存在/data/files 图片保存在/data/images
            String path = contentType.contains("image") ? "data/images/" + DateUtils.format(new Date(), "YYYYMMdd") : "data/files/" + DateUtils.format(new Date(), "YYYYMMdd");
            //String fileName = file.getOriginalFilename();容易重名覆盖
            String fileName = new Date().getTime()+".jpg";
            File targetFile = new File(basepath + path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            Productimg productimg=goodsService.insertProductImg(request,goodid,path + "/" + fileName);
            return ShopResult.build(200, "上传成功", productimg);
        } catch (Exception e) {
            e.printStackTrace();
            return ShopResult.build(500, "上传失败");
        }

    }
    @RequestMapping("/updatedelimgurl")
    @ResponseBody
    public ShopResult updatedelimgurl(Long goodsId, HttpServletRequest request) {
        goodsService.deleteProductImg(request,goodsId);
        return ShopResult.build(200,"删除成功");
    }

}

