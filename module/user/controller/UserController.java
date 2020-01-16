package com.zknu.shop.module.user.controller;

import com.zknu.shop.common.pojo.*;
import com.zknu.shop.common.util.PageResult;
import com.zknu.shop.module.user.vo.ItemAndProduct;
import com.zknu.shop.module.user.vo.OrderAndUser;
import org.springframework.ui.Model;
import com.zknu.shop.common.pojo.Orders;
import com.zknu.shop.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */
@Controller

public class UserController {
    @Autowired
    UserService userService;
    /**
     *
     * 跳转我的订单view
     */
    @RequestMapping(value = "menu",method = RequestMethod.GET)
    public  String orderList(@RequestParam(defaultValue = "1")int page,
                             @RequestParam(defaultValue = "10")int row,HttpServletRequest request,Model model){

         User user= (User)request.getSession().getAttribute("user");
         if (null==user){
             return "login";
         }
        PageResult pageResult = userService.selectOrderListByUserId(page,row,user.getUserId());


         model.addAttribute("pageResult",pageResult);

        return "orderList";
    }
    /**
     *
     * 跳转我的订单详情view
     */
    @RequestMapping(value = "menudetails",method = RequestMethod.GET)
    public  String orderListdetails(long id,HttpServletRequest request,Model model){
        User user= (User)request.getSession().getAttribute("user");
        if (null==user){
            return "login";
        }

         OrderAndUser orderAndUser = userService.selectitemByOrderId(id);
         model.addAttribute("orderAndUser",orderAndUser);
        return "porderDetils";
    }
    /**
     *
     * 订单收货
     */
    @RequestMapping(value = "receiveOrder",method = RequestMethod.GET)
    public  String receive(long id,int action,HttpServletRequest request,Model model){
        User user= (User)request.getSession().getAttribute("user");
        if (null==user){
            return "login";
        }
        Orders orders = new Orders();
        orders.setOrderId(id);
        if (action==2){
            orders.setOrderStatus("3");
        }

        userService.updateOrder(orders);

        return "redirect:menu";
    }
    /**
     *
     * 删除订单
     */
    @RequestMapping(value = "deleteOrder",method = RequestMethod.GET)
    public  String deleteOrder(long id,HttpServletRequest request){
        User user= (User)request.getSession().getAttribute("user");
        if (null==user){
            return "login";
        }
        Orders orders = new Orders();
        orders.setOrderId(id);
        orders.setOrderStatus("8");
        userService.updateOrder(orders);
        return "redirect:menu";
    }

    /**
     *
     *  申请退款
     */
    @RequestMapping(value = "backmoneyOrder",method = RequestMethod.GET)
    public  String backmoneyOrder(long id,Model model,HttpServletRequest request){
        User user= (User)request.getSession().getAttribute("user");
        if (null==user){
            return "login";
        }
        Orders orders = new Orders();
        orders.setOrderId(id);
        orders.setOrderStatus("9");

       userService.updateOrder(orders);
        return "redirect:menu";
    }
    /**
     *
     *  评价订单view
     */
    @RequestMapping(value = "messageOrder",method = RequestMethod.GET)
    public  String messageOrder(long id,Model model,HttpServletRequest request){
        User user= (User)request.getSession().getAttribute("user");
        if (null==user){
            return "login";
        }

        model.addAttribute("id",id);
        return "message";
    }
    /**
     *
     *  评价订单
     */
    @RequestMapping(value = "messageOrder",method = RequestMethod.POST)
    public  String messageOrderp(Orders orders,Model model,HttpServletRequest request){
        User user= (User)request.getSession().getAttribute("user");
        if (null==user){
            return "login";
        }
        userService.updateOrder(orders);

        model.addAttribute("view","menu");
        model.addAttribute("message","评价成功！");
        return  "success";

    }
    /**
     *
     *  查看评价订单
     */
    @RequestMapping(value = "selectmessageOrder",method = RequestMethod.GET)
    public  String selectmessageOrderp(long id,Model model,HttpServletRequest request){
        User user= (User)request.getSession().getAttribute("user");
        if (null==user){
            return "login";
        }

      Orders orders =  userService.selectOrderByOrderId(id);
      model.addAttribute("orders",orders);
      model.addAttribute("action",2);
        return  "message";

    }

}
