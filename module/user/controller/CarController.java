package com.zknu.shop.module.user.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zknu.shop.common.pojo.Address;
import com.zknu.shop.common.pojo.Orders;
import com.zknu.shop.common.pojo.User;
import com.zknu.shop.module.user.service.AddressService;
import com.zknu.shop.module.user.service.CarService;
import com.zknu.shop.module.user.util.PortalResult;
import com.zknu.shop.module.user.vo.CarImg;
import com.zknu.shop.module.user.vo.UpdateCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.zknu.shop.module.user.constants.AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY;
import static com.zknu.shop.module.user.constants.AlipayServiceEnvConstants.APP_ID;
import static com.zknu.shop.module.user.constants.AlipayServiceEnvConstants.PRIVATE_KEY;

/**
 * Created by abeg on 2017/8/22.
 * 购物车 以及订单的生成 支付宝支付
 */
@Controller
public class CarController {
    @Autowired
    CarService carService;
    @Autowired
    AddressService addressService;
    /**
     * 加入购物车
     * @param request
     * @param id
     * @param price
     * @param number
     * @return
     */
    @RequestMapping("/addcart")
    @ResponseBody
    public PortalResult addCart(HttpServletRequest request, Long id, double price, int number, int status){
        //首先判断是否登陆 未登陆 返回未登录信息
        if(request.getSession().getAttribute("user")==null)
        {
            return PortalResult.build(232,"未登录，请您登陆");
        }else {
            PortalResult portalResult = carService.addcart(request,id,price,number,status);
            return portalResult;
        }
    }
    /**
     * 加入购物车
     * @param request
     * @return
     */
    @RequestMapping("/indexaddCart")
    public String addCart(HttpServletRequest request, Long goodid){
        //首先判断是否登陆 未登陆 返回未登录信息
        if(request.getSession().getAttribute("user")==null)
        {
            return "login";
        }else {
            carService.addindexCart(request, goodid);
            return "redirect:car";
        }


    }

    /**
     * 跳转结算页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/addorder")
    public String addorder(HttpServletRequest request,Model model){
        //首先判断是否登陆 未登陆 返回未登录信息
        if(request.getSession().getAttribute("user")==null)
        {
            return "login";
        }else {
            int goodsNumber =0;
            double goodsPriceCount =0.00;
            List<CarImg> list = carService.seleteCatByUserAll(request);
            for (CarImg carImg : list)
            {
                goodsNumber+=carImg.getCarProductNum();
                goodsPriceCount+=carImg.getCarProductPrice();
            }
            //这里要调用 user接口 查询用户所有的收货地址
            User user=(User)request.getSession().getAttribute("user");
            List<Address> addressList=addressService.selectAdressById(user.getUserId());
            model.addAttribute("car_list",list);
            model.addAttribute("user_s",user);
            model.addAttribute("userAddress",addressList);
            model.addAttribute("goodsNumber",goodsNumber);
            model.addAttribute("priceCount",goodsPriceCount);
            return "addorder";
        }
    }

    /**
     * 跳转购物车页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/car")
    public String gocar(HttpServletRequest request,Model model){
        //首先判断是否登陆 未登陆 返回未登录信息
        if(request.getSession().getAttribute("user")==null)
        {
            return "login";
        }else {
            //定义两个变量 分别保存总价格和总数量
            int goodsNumber =0;
            double goodsPriceCount =0.00;
            List<CarImg> list = carService.seleteCatByUserAll(request);
            for (CarImg carImg : list)
            {
                goodsNumber+=carImg.getCarProductNum();
                goodsPriceCount+=carImg.getCarProductPrice();
            }
            model.addAttribute("car_list",list);
            model.addAttribute("goodsNumber",goodsNumber);
            model.addAttribute("priceCount",goodsPriceCount);
            return "car";
        }
    }

    /**
     * 更新购物车页面
     * @param request
     * @param carId
     * @return
     */
    @RequestMapping("/updatecarprice")
    @ResponseBody
    public UpdateCar updatecarprice(HttpServletRequest request,Long[] carId){
        //根据传入的用户选择的商品id 返回 钱和数量 局部更新页面
        UpdateCar car = carService.updatecarprice(request,carId);
        return car;
    }
    /**
     * 删除购物车中一条记录
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/delcarone")
    public String delcarone(HttpServletRequest request,Long id){
        //根据传入的id删除一条记录
        carService.deletecarById(id);
        return "redirect:car";
    }

    /**
     * 添加地址
     * @param request
     * @return
     */
    @RequestMapping("/user/addaddress")
    @ResponseBody
    public PortalResult addaddress(HttpServletRequest request,String address){

        return carService.addAddress(request,address);
    }

    /**
     * 选择地址
     * @param request
     * @param number
     */
    @RequestMapping("/user/selAddress")
    @ResponseBody
    public void selAddress(HttpServletRequest request,int number){
        carService.updateAddress(request,number);
    }

    /**
     * 确认订单
     * @param request
     * @param sc_date
     * @param paytype
     * @param message
     */
    @RequestMapping("/confirmOrder")
    public String confirmOrder(HttpServletRequest request, Model model, String sc_date, int paytype,
                               @RequestParam(defaultValue = " ") String message, double sumprice){
        List<CarImg> list_all=carService.seleteCatByUserAll(request);
        if(list_all.size()==0)
        {
            model.addAttribute("error_message","没有可结算的商品");
            return "addorder";
        }
        List<Orders> list=carService.insertconfirmOrder(request,sc_date,paytype,message,sumprice);
        model.addAttribute("order_list",list);
        model.addAttribute("order_list_one",list.get(0));
        model.addAttribute("paytype",paytype);
        return "gopay";
    }

    /**
     * 去支付
     * @param request
     * @return
     */
    @RequestMapping("/gopay")
    public String confirmOrder(HttpServletRequest request, HttpServletResponse response,Long[] ordersn,int all) throws IOException {
        //传入一个订单  然后用这个订单的 生成时间去 查找所有的  查出来的list集合 当做一次付款
        String ww = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        double money=0.00;
        String ordersn_string ="";
        for(Long l:ordersn)
        {
             money += carService.selectOrderByuser(l);
             ordersn_string+=l+",";
        }
        ordersn_string=ordersn_string.substring(0,ordersn_string.length()-1);
        //判断有没有登录
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", APP_ID, PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA"); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        if (all == 1) {
            alipayRequest.setReturnUrl(ww + "/payok?orderSn=" + ordersn_string + "&all=1");//成功后回调
            alipayRequest.setNotifyUrl(ww + "/payok?orderSn=" + ordersn_string + "&all=1");//在公共参数中设置回跳和通知地址
        } else if (all == 2) {
            alipayRequest.setReturnUrl(ww + "/payok?orderSn=" + ordersn_string + "&all=2");//成功后回调
            alipayRequest.setNotifyUrl(ww + "/payok?orderSn=" + ordersn_string + "&all=2");//在公共参数中设置回跳和通知地址
        }
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":" + ordersn[0] + "," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + money + "," +
                "    \"subject\":\"全部商品\"," +
                "    \"body\":\"全部商品\"," +
                "    \"seller_id\":\"2088102170132700\"" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + "utf-8");
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
        return "gopay";
    }

    @RequestMapping(value = "/payok")
    public String payok(String  orderSn, int all, HttpServletRequest request) {
        //支付成功之后 增加支付记录 订单表状态改变
        String[] ordersn_string = orderSn.split(",");
        Long[]  longs =new Long[100];
        for (int i=0;i<ordersn_string.length;i++)
        {
            longs[i]=Long.parseLong(ordersn_string[i]);
        }
        carService.updateOrderStatus(all,request,longs);
        //首先先获取到相关数据
        return "payok";
    }


}
