package com.zknu.shop.module.user.vo;

import com.zknu.shop.common.pojo.Item;

/**
 * Created by Administrator on 2017/8/22.
 */
public class ItemAndProduct extends Item {
    private String productName;
    private Double productPrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
