package com.zknu.shop.module.user.service;

import com.zknu.shop.common.pojo.User;
import com.zknu.shop.common.util.ShopResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by abeg on 2017/8/17.
 */
public interface LoginService {

    /**
     * 根据传入的用户名和密码进行登陆验证

     * @return
     */
    List<User> selectUser(User user);
}
