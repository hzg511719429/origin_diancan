package com.zknu.shop.module.sell.controller;

import com.zknu.shop.common.pojo.Orders;
import com.zknu.shop.common.pojo.User;
import com.zknu.shop.common.util.PageResult;
import com.zknu.shop.module.sell.service.SellOrderService;
import com.zknu.shop.module.user.service.UserService;
import com.zknu.shop.module.user.vo.OrderAndUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by abeg on 2017/8/23.
 * 商家订单列表 列表详情
 */
@Controller
public class SellOrderController {
   @Autowired
    SellOrderService sellOrderService;
   @Autowired
    UserService userService;
    /**
     *
     *  商家订单列表
     */
   @RequestMapping(value = "/sellOrderList",method = RequestMethod.GET)
   public String sellOrderListView(@RequestParam(defaultValue = "1")int page,
                                   @RequestParam(defaultValue = "10")int row, HttpServletRequest request, Model model){

           User user= (User)request.getSession().getAttribute("user");
           if (null==user){
               return "login";
           }
           PageResult pageResult = sellOrderService.selectOrderListByUserId(page,row,user.getUserId());


           model.addAttribute("pageResult",pageResult);

       return "sell/sellorderlist";
   }
    /**
     *
     *  条件商家订单列表
     */
    @RequestMapping(value = "/sellOrderListAction",method = RequestMethod.GET)
    public String sellOrderListViewAction(@RequestParam(defaultValue = "1")int page,
                                    @RequestParam(defaultValue = "10")int row,String action, HttpServletRequest request, Model model){

        User user= (User)request.getSession().getAttribute("user");
        if (null==user){
            return "login";
        }
        PageResult pageResult = sellOrderService.selectOrderListAction(page,row,user.getUserId(),action);


        model.addAttribute("pageResult",pageResult);
        model.addAttribute("raction",action);

        return "sell/sellorderlist";
    }
    /**
     *
     *  商家订单详细
     */
    @RequestMapping(value = "/sellOrderDetails",method = RequestMethod.GET)
    public String sellOrderDetailstView(long id,HttpServletRequest request,Model model){
        User user= (User)request.getSession().getAttribute("user");
        if (null==user){
            return "login";
        }

        OrderAndUser orderAndUser = userService.selectitemByOrderId(id);
        model.addAttribute("orderAndUser",orderAndUser);
        return "sell/sellorderDetails";
    }

    /**
     *
     *  商家退单订单backOrder?orderId=${order.orderId}
     */
    @RequestMapping(value = "/backOrder",method = RequestMethod.GET)
    public String sellOrderDetailstView(long orderId){

        Orders orders = new Orders();
        orders.setOrderId(orderId);
        orders.setOrderStatus("8");

        userService.updateOrder(orders);


        return "redirect:sellOrderList";
    }
    /**
     *
     *  商家发货
     */
    @RequestMapping(value = "/sendOrder",method = RequestMethod.GET)
    public String sendOrderw(long orderId){

        Orders orders = new Orders();
        orders.setOrderId(orderId);
        orders.setOrderStatus("2");

        userService.updateOrder(orders);

        return "redirect:sellOrderList";
    }
    /**
     *
     *  m买家收货
     */
    @RequestMapping(value = "/sreceiveOrder",method = RequestMethod.GET)
    public String sreceiveOrder(long orderId){

        Orders orders = new Orders();
        orders.setOrderId(orderId);
        orders.setOrderStatus("3");

        userService.updateOrder(orders);

        return "redirect:sellOrderList";
    }
    /**
     *
     *  m买家未收货
     */
    @RequestMapping(value = "/sbreceiveOrder",method = RequestMethod.GET)
    public String sbreceiveOrder(long orderId){

        Orders orders = new Orders();
        orders.setOrderId(orderId);
        orders.setOrderStatus("11");

        userService.updateOrder(orders);

        return "redirect:sellOrderList";
    }
    /**
     *
     *  商家发货    货到付款
     */
    @RequestMapping(value = "/goodssendOrder",method = RequestMethod.GET)
    public String goodssendOrderw(long orderId){

        Orders orders = new Orders();
        orders.setOrderId(orderId);
        orders.setOrderStatus("5");

        userService.updateOrder(orders);

        return "redirect:sellOrderList";
    }
    /**
     *
     *  m买家收货  货到付款
     */
    @RequestMapping(value = "/goodssreceiveOrder",method = RequestMethod.GET)
    public String goodssreceiveOrder(long orderId){

        Orders orders = new Orders();
        orders.setOrderId(orderId);
        orders.setOrderStatus("6");

        userService.updateOrder(orders);

        return "redirect:sellOrderList";
    }

    /**
     *
     *  查看评价订单
     */
    @RequestMapping(value = "sellselectmessageOrder",method = RequestMethod.GET)
    public  String selectmessageOrderp(long orderId,Model model,HttpServletRequest request){
        User user= (User)request.getSession().getAttribute("user");
        if (null==user){
            return "login";
        }

        Orders orders =  userService.selectOrderByOrderId(orderId);
        model.addAttribute("orders",orders);
        model.addAttribute("action",2);
        return  "sell/sellmessage";

    }
    /**
     *
     *  退款
     */
    @RequestMapping(value = "backmoney",method = RequestMethod.GET)
    public  String backmoney(long orderId,Model model,HttpServletRequest request){
        User user= (User)request.getSession().getAttribute("user");
        if (null==user){
            return "login";
        }

        Orders orders = new Orders();
        orders.setOrderId(orderId);
        orders.setOrderStatus("10");

        userService.updateOrder(orders);

        return "redirect:sellOrderList";

    }
}
