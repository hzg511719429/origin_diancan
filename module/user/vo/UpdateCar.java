package com.zknu.shop.module.user.vo;

/**
 * Created by abeg on 2017/8/22.
 * 选择购物车中要结算的商品 所用到的
 */
public class UpdateCar {
    private int number;
    private double price;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
