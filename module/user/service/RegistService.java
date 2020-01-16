package com.zknu.shop.module.user.service;

import com.zknu.shop.common.pojo.Address;
import com.zknu.shop.common.pojo.User;
import com.zknu.shop.common.util.ShopResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by abeg on 2017/8/17.
 */
public interface RegistService {
    /**
     * 是否已有用户
     * @return
     */
   User findUserByEmail(String userEmail);
    /**
     * 插入用户
     * @return
     */
    void insertUser(User user);
    /**
     * 更新用户信息
     * @return
     */
    void updateUser(User user1);
}
