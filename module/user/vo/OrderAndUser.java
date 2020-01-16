package com.zknu.shop.module.user.vo;

import com.zknu.shop.common.pojo.Orders;
import com.zknu.shop.common.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */
public class OrderAndUser extends Orders {
    private String name;
    private  String address;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private List<ItemAndProduct> itemAndProductList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ItemAndProduct> getItemAndProductList() {
        return itemAndProductList;
    }

    public void setItemAndProductList(List<ItemAndProduct> itemAndProductList) {
        this.itemAndProductList = itemAndProductList;
    }
}
