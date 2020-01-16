package com.zknu.shop.module.user.service.impl;

import com.zknu.shop.common.mapper.AddressMapper;
import com.zknu.shop.common.pojo.Address;
import com.zknu.shop.common.pojo.AddressExample;
import com.zknu.shop.common.pojo.UserExample;
import com.zknu.shop.module.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;
    @Override
    public List<Address> selectAdressById(long userId) {
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        criteria.andAddressBuyerIdEqualTo(userId);

        return addressMapper.selectByExample(addressExample);
    }

    @Override
    public void insertAdress(Address address) {
        addressMapper.insert(address);
    }

    @Override
    public void updateAdress(Address addressAddress) {

        addressMapper.updateByPrimaryKeySelective(addressAddress);

    }

    @Override
    public void insertSomAdress(String[] hpmename, Long userId) {

        for(String as : hpmename){
            Address address = new Address();
            address.setAddressAddress(as);
            address.setAddressBuyerId(userId);
            address.setAddressIsDefault("0");
            addressMapper.insert(address);
        }

    }

    @Override
    public void updateAdressAll(String[] addressId, String[] addressAddress) {
        int num = addressId.length;
        for (int i =0; i<num;i++){
            Address address = new Address();
            address.setAddressId( Long.parseLong(addressId[i]));
            address.setAddressAddress(addressAddress[i]);
            addressMapper.updateByPrimaryKeySelective(address);
        }
    }

    @Override
    public void deleteAddressById(long id) {

        addressMapper.deleteByPrimaryKey(id);


    }

    @Override
    public void updateAdressdefault(long id) {
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        criteria.andAddressIsDefaultEqualTo("1");
        List<Address> addresslist =addressMapper.selectByExample(addressExample);
        if (addresslist.size()>0){
            long oldId= addresslist.get(0).getAddressId();

            Address address = new Address();
            address.setAddressId(oldId);
            address.setAddressIsDefault("0");
            addressMapper.updateByPrimaryKeySelective(address);

        }
        Address address2 = new Address();
        address2.setAddressId(id);
        address2.setAddressIsDefault("1");
        addressMapper.updateByPrimaryKeySelective(address2);
    }
}
