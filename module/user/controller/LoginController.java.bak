package com.zknu.shop.module.user.controller;

import com.zknu.shop.common.pojo.Address;
import com.zknu.shop.common.pojo.User;
import com.zknu.shop.common.util.ShopResult;
import com.zknu.shop.module.user.service.AddressService;
import com.zknu.shop.module.user.service.LoginService;
import com.zknu.shop.module.user.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by abeg on 2017/8/17.
 */
@Controller()
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    RegistService registService;
    @Autowired
    AddressService addressService;
    /**
     * 跳转登录view
     * @return
     */
    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public String loginView(){
        return "login";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public String  login(User user, Model model, HttpServletRequest request){

        String username= user.getUserEmail();
        String password = user.getUserPassword();
        if (username==null|| username=="" || password==null|| password==""){
            model.addAttribute("message","账号或密码不能为空!");
        } else{
            List<User> user1 = loginService.selectUser(user);
            if (user1.size()<=0){
                model.addAttribute("message","对不起，账号或密码不正确!");
            } else {
                request.getSession().setAttribute("user",user1.get(0));

                return  "redirect:index";
            }
        }
        return "login";
    }
    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/loginOut" )
    public String  login0ut(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            request.getSession().removeAttribute("user");
        }
        model.addAttribute("view","login");
        model.addAttribute("message","退出成功！");
        return  "success";
    }

    /**
     * 注册view
     * @return
     */

    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    public String registView(HttpServletRequest request, User user){
        return "regist";
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String  regist(HttpServletRequest request,Model model, User user,String user_passWordAgain,String addressAddress){
        String format = "\\w{2,15}[@][a-z0-9]{2,}[.]\\p{Lower}{2,}";
        //w{2,15}: 2~15个[a-zA-Z_0-9]字符；w{}内容是必选的。 如：dyh@152.com是合法的。
        //[a-z0-9]{3,}：至少三个[a-z0-9]字符,[]内的是必选的；如：dyh200896@16.com是不合法的。
        //[.]:'.'号时必选的； 如：dyh200896@163com是不合法的。
        //p{Lower}{2,}小写字母，两个以上。如：dyh200896@163.c是不合法的。
        //为空
        if (user.getUserEmail()==null || user.getUserEmail()==""){
            model.addAttribute("semail","邮箱不能为空 ");
            model.addAttribute("userr",user);
            model.addAttribute("addressr",addressAddress);
            return "regist";
        }else  if(!user.getUserEmail().matches(format)){
            model.addAttribute("semail","邮箱格式不正确");
            model.addAttribute("userr",user);
            model.addAttribute("addressr",addressAddress);
            return "regist";
        } else if(user.getUserPassword()==null || user.getUserPassword()==""){
            model.addAttribute("spassword","密码不能为空");
            model.addAttribute("userr",user);
            model.addAttribute("addressr",addressAddress);
            return "regist";
        } else if (user.getUserPassword().length()<6){
            model.addAttribute("spassword","密码长度不能小于6");
            model.addAttribute("userr",user);
            model.addAttribute("addressr",addressAddress);
            return "regist";
        }else if(user_passWordAgain==null || user_passWordAgain==""){
            model.addAttribute("spassword2","2次密码不能为空");
            model.addAttribute("userr",user);
            model.addAttribute("addressr",addressAddress);
            return "regist";
        } else if (user_passWordAgain.length()<6){
            model.addAttribute("spassword2","密码长度不能小于6");
            model.addAttribute("userr",user);
            model.addAttribute("addressr",addressAddress);
            return "regist";
        }//密码不一致
        else if (!user.getUserPassword().equals(user_passWordAgain)){
            model.addAttribute("spassword","两次密码不一致");
            model.addAttribute("userr",user);
            model.addAttribute("addressr",addressAddress);
            return "regist";
        } else if(user.getUserNickname()==null || user.getUserNickname()==""){
            model.addAttribute("snickname","昵称不能为空");
            model.addAttribute("userr",user);
            model.addAttribute("addressr",addressAddress);
            return "regist";
        } else if(user.getUserRealname()==null || user.getUserRealname()==""){
            model.addAttribute("srealname","真实姓名不能为空");
            model.addAttribute("userr",user);
            model.addAttribute("addressr",addressAddress);
            return "regist";
        } else if(user.getUserPhone()==null || user.getUserPhone()==""){
            model.addAttribute("sphone","电话不能为空");
            model.addAttribute("userr",user);
            model.addAttribute("addressr",addressAddress);
            return "regist";
        }

        if (!"1".equals(user.getUserRol())){

            if(addressAddress==null || addressAddress==""){
                model.addAttribute("saddress","地址不能为空");
                model.addAttribute("userr",user);
                model.addAttribute("addressr",addressAddress);
                return "regist";
            }

        }


        //是否已有用户
        User user1 = registService.findUserByEmail(user.getUserEmail());
        if (user1!=null){
            model.addAttribute("semail","用户已经存在");
            model.addAttribute("userr",user);
            model.addAttribute("addressr",addressAddress);
            return "regist";
        }else{
            //卖家买家
            if("1".equals(user.getUserRol())){//卖家
                registService.insertUser(user);
            }else{
                user.setUserRol("0");
                registService.insertUser(user);
                List<User> userlist = loginService.selectUser(user);
                User users = userlist.get(0);

                Address address = new Address();
                address.setAddressAddress(addressAddress);
                address.setAddressBuyerId(users.getUserId());
                address.setAddressIsDefault("1");
                addressService.insertAdress(address);
            }
            model.addAttribute("view","login");
            model.addAttribute("message","注册成功！");
            return  "success";
        }

    }
    /**
     *
     * 跳修用重置密码d登录邮箱view
     */
    @RequestMapping(value = "mailEditPassword",method =RequestMethod.GET)
    public  String mailEditPassword(){

        return "mailEditPassword";
    }
    /**
     *
     * 跳修用重置密码d登录邮箱
     */
    @RequestMapping(value = "mailEditPassword",method =RequestMethod.POST)
    public  String mailEditPasswordP(String email,Model model){
        String format = "\\w{2,15}[@][a-z0-9]{2,}[.]\\p{Lower}{2,}";
        if (email==null || email ==""){
            model.addAttribute("semail","邮箱不能为空");
            return "mailEditPassword";
        }else if (!email.matches(format)){
            model.addAttribute("semail","邮箱格式不正确");
            return "mailEditPassword";
        }

        User user = registService.findUserByEmail(email);
        if (user==null){
            model.addAttribute("semail","不存在此用户");
            return "mailEditPassword";
        }
         model.addAttribute("userId",user.getUserId());
        return "editPassword";
    }

    /**
     *
     * 跳转新密码
     */
    @RequestMapping(value = "editPassword",method = RequestMethod.GET)
    public  String editPassword(){
        return "editPassword";
    }
    @RequestMapping(value = "editPassword",method = RequestMethod.POST)
    public  String editPasswordp(String passWord ,String passWordAgain,long userId,HttpServletRequest request,Model model){
       if(passWord==null || passWord ==""){
            model.addAttribute("spassword","密码不能为空");
           return "editPassword";

       } else  if (passWordAgain==null || passWordAgain==""){
           model.addAttribute("spassword2","二次密码不能为空");
           return "editPassword";

       } else if (!passWord.equals(passWordAgain)){
           model.addAttribute("spassword","二次密码不一致");
           return "editPassword";
       }
        else {
           //更新
           User user1 = new User();
           user1.setUserPassword(passWord);
           user1.setUserId(userId);

           registService.updateUser(user1);
       }


        model.addAttribute("view","login");
        model.addAttribute("message","修改密码成功！");
        return  "success";

    }
    /**
     *
     * 跳修改账户view
     */
    @RequestMapping(value = "updateUser",method = RequestMethod.GET)
    public  String editProfile(HttpServletRequest request,Model model){
        User user =(User) request.getSession().getAttribute("user");
        if (!"1".equals(user.getUserRol())){
            List<Address> addressList = addressService.selectAdressById(user.getUserId());
           model.addAttribute("addressList",addressList);
        }
        return "editProfile";
    }
    @RequestMapping(value = "updateUser",method = RequestMethod.POST)
    public  String editProfilep(HttpServletRequest request,Model model, User user,String[] addressId,String[] addressAddress ,String[] hpmename){

        if(user.getUserPassword()==null || user.getUserPassword()==""){
            model.addAttribute("errss1","密码不能为空");

            if (!"1".equals(user.getUserRol())){
                List<Address> addressList = addressService.selectAdressById(user.getUserId());
                model.addAttribute("addressList",addressList);
            }
            return "editProfile";
        } else if (user.getUserPassword().length()<6){
            model.addAttribute("errss1","密码长度不能小于6");

            if (!"1".equals(user.getUserRol())){
                List<Address> addressList = addressService.selectAdressById(user.getUserId());
                model.addAttribute("addressList",addressList);
            }
            return "editProfile";
        }else if(user.getUserNickname()==null || user.getUserNickname()==""){
            model.addAttribute("errss3","昵称不能为空");

            if (!"1".equals(user.getUserRol())){
                List<Address> addressList = addressService.selectAdressById(user.getUserId());
                model.addAttribute("addressList",addressList);
            }
            return "editProfile";
        } else if(user.getUserRealname()==null || user.getUserRealname()==""){
            model.addAttribute("errss4","真实姓名不能为空");

            if (!"1".equals(user.getUserRol())){
                List<Address> addressList = addressService.selectAdressById(user.getUserId());
                model.addAttribute("addressList",addressList);
            }
            return "editProfile";
        } else if(user.getUserPhone()==null || user.getUserPhone()==""){
            model.addAttribute("errss5","电话不能为空");

            if (!"1".equals(user.getUserRol())){
                List<Address> addressList = addressService.selectAdressById(user.getUserId());
                model.addAttribute("addressList",addressList);
            }
            return "editProfile";
        }



           registService.updateUser(user);

              if (user.getUserRol().equals("0")){
                  for(int i =0;i<addressId.length;i++){
                      if (addressAddress[i]==null ||addressAddress[i]==""){
                          model.addAttribute("errss6","地址不能为空");
                          return "editProfile";
                      }
                  }
                  addressService.updateAdressAll(addressId,addressAddress);

                  if (hpmename!=null){
                      addressService.insertSomAdress(hpmename,user.getUserId());
                  }
              }

            User users = (User) request.getSession().getAttribute("user");
            if (users!=null){
                request.getSession().removeAttribute("user");
            }

        model.addAttribute("view","login");
        model.addAttribute("message","修改个人信息成功！");
        return  "success";
    }

    /**
     *
     * 删除地址
     */
    @RequestMapping(value = "deleteAdress",method = RequestMethod.POST)
    @ResponseBody
    public  String deleteAdress(long id,HttpServletRequest request,Model model){

       addressService.deleteAddressById(id);

       return  "success";
    }
    /**
     *
     * 设为默认地址
     */
    @RequestMapping(value = "updatedefaultAddresss",method = RequestMethod.GET)
    public  String updatedefaultAddresss(long id){

        addressService.updateAdressdefault(id);

        return  "redirect:updateUser";
    }
}
