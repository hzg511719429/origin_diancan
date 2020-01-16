package com.zknu.shop.module.user.service.impl;

import com.zknu.shop.common.mapper.UserMapper;
import com.zknu.shop.common.pojo.User;
import com.zknu.shop.common.pojo.UserExample;
import com.zknu.shop.common.util.ShopResult;
import com.zknu.shop.module.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by abeg on 2017/8/17.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> selectUser(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserEmailEqualTo(user.getUserEmail());
        criteria.andUserPasswordEqualTo(user.getUserPassword());

        List<User> userList = userMapper.selectByExample(userExample);

        return userList;
    }
}
