package com.zknu.shop.module.user.service;

import com.zknu.shop.common.pojo.Address;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */
public interface AddressService {
    /**
     * 根据传入的id 查找地址
     * @return
     */
    List<Address> selectAdressById(long userId);

    /**
     * 插入地址
     * @return
     */
    void insertAdress(Address address);
    /**
     * 更新地址
     * @return
     * @param addressAddress
     */
    void updateAdress(Address addressAddress);
    /**
     * 批量添加地址
     * @return

     */
    void insertSomAdress(String[] hpmename, Long userId);
    /**
     * 批量x修改地址
     * @return

     */
    void updateAdressAll(String[] addressId, String[] addressAddress);
    /**
     * 删除地址
     * @return

     */
    void deleteAddressById(long id);
    /**
     *
     * 设为默认地址
     */
    void updateAdressdefault(long id);
}
