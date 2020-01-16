package com.zknu.shop.module.user.service.impl;

import com.zknu.shop.common.mapper.AddressMapper;
import com.zknu.shop.common.mapper.UserMapper;
import com.zknu.shop.common.pojo.Address;
import com.zknu.shop.common.pojo.User;
import com.zknu.shop.common.pojo.UserExample;
import com.zknu.shop.common.util.ShopResult;
import com.zknu.shop.module.user.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by abeg on 2017/8/17.
 */
@Service
public class RegistServiceImpl implements RegistService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    AddressMapper addressMapper;


    @Override
    public User findUserByEmail(String userEmail) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserEmailEqualTo(userEmail);
        List<User> userlist = userMapper.selectByExample(userExample);
        if (userlist.size()>0){

            return userlist.get(0);
        }
        return null;
    }

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user1) {


        userMapper.updateByPrimaryKeySelective(user1);
    }

}
