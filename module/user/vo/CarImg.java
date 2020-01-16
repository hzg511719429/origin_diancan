package com.zknu.shop.module.user.vo;

import com.zknu.shop.common.pojo.Car;

/**
 * Created by abeg on 2017/8/22.
 */
public class CarImg extends Car {
    private String img;
    private double oneprice;
    private String goodsName;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getOneprice() {
        return oneprice;
    }

    public void setOneprice(double oneprice) {
        this.oneprice = oneprice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
