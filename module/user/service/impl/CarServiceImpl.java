package com.zknu.shop.module.user.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.zknu.shop.common.mapper.*;
import com.zknu.shop.common.pojo.*;
import com.zknu.shop.common.util.IDUtils;
import com.zknu.shop.module.user.service.CarService;
import com.zknu.shop.module.user.util.PortalResult;
import com.zknu.shop.module.user.vo.CarImg;
import com.zknu.shop.module.user.vo.UpdateCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by abeg on 2017/8/22.
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductimgMapper productimgMapper;
    @Autowired
    CarMapper carMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    ItemMapper itemMapper;
    @Override
    public PortalResult addcart(HttpServletRequest request, Long id, double price, int number, int status) {
        //把价格也传过来
        User user=(User) request.getSession().getAttribute("user");
        //首先判断购物车中是否有该物品
        CarExample example = new CarExample();
        CarExample.Criteria criteria= example.createCriteria();
        criteria.andCarBuyerIdEqualTo(user.getUserId());
        criteria.andCarProductIdEqualTo(id);
        List<Car> list = carMapper.selectByExample(example);
        if(list.size()==0)
        {
            Car car = new Car();
            car.setCarBuyerId(user.getUserId());
            car.setCarProductId(id);
            car.setCarProductNum(number);
            car.setCarProductPrice(number*price);
            carMapper.insert(car);
        }else {
            list.get(0).setCarProductNum(list.get(0).getCarProductNum()+number);
            list.get(0).setCarProductPrice(price*list.get(0).getCarProductNum());
            carMapper.updateByPrimaryKey(list.get(0));
        }

        if(status==0)
        {
            return PortalResult.build(205,"加入成功");
        }else {
            return PortalResult.build(200,"加入成功",request.getContextPath()+"/addorder");
        }
    }

    @Override
    public void addindexCart(HttpServletRequest request, Long id) {
        //取出user
        User user=(User) request.getSession().getAttribute("user");
        //首先判断购物车中是否有该物品
        CarExample example = new CarExample();
        CarExample.Criteria criteria= example.createCriteria();
        criteria.andCarBuyerIdEqualTo(user.getUserId());
        criteria.andCarProductIdEqualTo(id);
        List<Car> list = carMapper.selectByExample(example);
        Product product = productMapper.selectByPrimaryKey(id);
        if(list.size()==0)
        {
            Car car = new Car();
            car.setCarBuyerId(user.getUserId());
            car.setCarProductId(id);
            car.setCarProductNum(1);
            car.setCarProductPrice(product.getProductPrice());
            carMapper.insert(car);
        }else {
            list.get(0).setCarProductNum(list.get(0).getCarProductNum()+1);
            list.get(0).setCarProductPrice(list.get(0).getCarProductPrice()*list.get(0).getCarProductNum());
            carMapper.updateByPrimaryKey(list.get(0));
        }
    }

    @Override
    public List<CarImg> seleteCatByUserAll(HttpServletRequest request) {
        //取出user
        User user=(User) request.getSession().getAttribute("user");
        CarExample example = new CarExample();
        CarExample.Criteria criteria= example.createCriteria();
        criteria.andCarBuyerIdEqualTo(user.getUserId());
        Long[] cartId= (Long[]) request.getSession().getAttribute("sel_goods");
        if(cartId!=null&&cartId.length!=0) {
            List<Long> longs = new ArrayList<>();
            for (int i = 0; i < cartId.length; i++) {
                longs.add(cartId[i]);
            }
            criteria.andCarProductIdIn(longs);
        }
        List<Car> list = carMapper.selectByExample(example);
        List<CarImg> list_return = new ArrayList<>();
        for (Car car :list){
            CarImg carImg = new CarImg();
            carImg.setCarProductNum(car.getCarProductNum());
            carImg.setCarProductPrice(car.getCarProductPrice());
            carImg.setCarProductId(car.getCarProductId());
            carImg.setCarBuyerId(car.getCarBuyerId());
            carImg.setCarId(car.getCarId());
            ProductimgExample productimgExample = new ProductimgExample();
            ProductimgExample.Criteria criteria1=productimgExample.createCriteria();
            criteria1.andProductIdEqualTo(car.getCarProductId());
            carImg.setImg(productimgMapper.selectByExample(productimgExample).get(0).getImgurl());
            //在product中获取数据
            Product product = productMapper.selectByPrimaryKey(car.getCarProductId());
            carImg.setOneprice(product.getProductPrice());
            carImg.setGoodsName(product.getProductName());
            list_return.add(carImg);
        }
        return list_return;
    }

    @Override
    public UpdateCar updatecarprice(HttpServletRequest request,Long[] cartId) {
        UpdateCar updateCar = new UpdateCar();
        request.getSession().setAttribute("sel_goods",cartId);
        if(cartId!=null&&cartId.length!=0)
        {
            User user=(User) request.getSession().getAttribute("user");
            CarExample example = new CarExample();
            CarExample.Criteria criteria= example.createCriteria();
            criteria.andCarBuyerIdEqualTo(user.getUserId());
            List<Long> longs = new ArrayList<>();
            for (int i=0;i<cartId.length;i++)
            {
                longs.add(cartId[i]);
            }
            criteria.andCarProductIdIn(longs);
            List<Car> list = carMapper.selectByExample(example);
            List<CarImg> list_return = new ArrayList<>();
            updateCar.setNumber(0);
            updateCar.setPrice(0.00);
            for (Car car :list){
                updateCar.setNumber(updateCar.getNumber()+car.getCarProductNum());
                updateCar.setPrice(updateCar.getPrice()+car.getCarProductPrice());
            }
        }else {

            updateCar.setNumber(0);
            updateCar.setPrice(0.00);
        }

        return updateCar;
    }

    @Override
    public void deletecarById(Long id) {
        carMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PortalResult addAddress(HttpServletRequest request, String address) {
        User user=(User) request.getSession().getAttribute("user");
        Address a = new Address();
        a.setAddressIsDefault(0+"");
        a.setAddressBuyerId(user.getUserId());
        a.setAddressAddress(address);
        addressMapper.insert(a);
        return PortalResult.build(200,"添加成功");
    }

    @Override
    public void updateAddress(HttpServletRequest request, int number) {
        //首先获取user
        User user=(User) request.getSession().getAttribute("user");
        //用user去address中取数据
        AddressExample example = new AddressExample();
        AddressExample.Criteria criteria = example.createCriteria();
        criteria.andAddressBuyerIdEqualTo(user.getUserId());
        List<Address> list = addressMapper.selectByExample(example);
        for (int i=0;i<list.size();i++)
        {
            if(i==number)
            {
                if(!list.get(i).getAddressIsDefault().equals("1"))
                {
                    list.get(i).setAddressIsDefault(1+"");
                    addressMapper.updateByPrimaryKey(list.get(i));
                }

            }else{
                if(!list.get(i).getAddressIsDefault().equals("0"))
                {
                    list.get(i).setAddressIsDefault(0+"");
                    addressMapper.updateByPrimaryKey(list.get(i));
                }
            }

        }
    }

    @Override
    public List<Orders> insertconfirmOrder(HttpServletRequest request, String sc_date, int paytype, String message,double sumprice) {
        //首先获取user
        User user=(User) request.getSession().getAttribute("user");
        //构建一个返回的list
        List<Orders> list_return = new ArrayList<>();
        Date cr_date = new Date();
        //其次根据session获取 要结算的物品
        CarExample example = new CarExample();
        CarExample.Criteria criteria= example.createCriteria();
        criteria.andCarBuyerIdEqualTo(user.getUserId());
        Long[] carId= (Long[]) request.getSession().getAttribute("sel_goods");
        if(carId!=null)
        {
            List<Long> longs = new ArrayList<>();
            for (int i=0;i<carId.length;i++)
            {
                longs.add(carId[i]);
            }
            criteria.andCarProductIdIn(longs);
        }
        List<Car> list = carMapper.selectByExample(example);
        HashMap<Long,List<Car>> map = new HashMap();
        for(Car car :list)
        {
            Product product = productMapper.selectByPrimaryKey(car.getCarProductId());
            if(map.containsKey(product.getProductSellerId()))
            {
                List<Car> list1=map.get(product.getProductSellerId());
                list1.add(car);
                map.put(product.getProductSellerId(),list1);
            }else {
                List<Car> list1 = new ArrayList<>();
                list1.add(car);
                map.put(product.getProductSellerId(),list1);
            }
        }
        //拿出选中的地址
        AddressExample example1 = new AddressExample();
        AddressExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andAddressBuyerIdEqualTo(user.getUserId());
        criteria1.andAddressIsDefaultEqualTo(1+"");
        List<Address> addressList = addressMapper.selectByExample(example1);
        for (HashMap.Entry<Long,List<Car>> entry : map.entrySet()) {
            //一个key就是一个订单号  value就是item
            Orders o = new Orders();

            o.setOrderAddress(addressList.get(0).getAddressAddress());
            o.setOrderBuyerId(user.getUserId());
            o.setOrderSellerId(entry.getKey());
            o.setOrderNo(IDUtils.genItemId()+"");
            o.setOrderTime(cr_date);
            //时间处理一下
            Calendar now = Calendar.getInstance();
            String[] sj=sc_date.split("-");
            String newdate=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1)+"-"+now.get(Calendar.DAY_OF_MONTH)+" "+sj[0].substring(0,2)+":00:00";
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            Date date = null;
            try {
                date = sdf.parse(newdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            o.setOrderDelieveTime(date);
            o.setOrderFinishTime(new Date());
            o.setOrderTotalPrice(sumprice);
            if(paytype==0)
            {
                o.setOrderPayment(paytype+"");
                o.setOrderStatus(0+"");
            }else {
                o.setOrderPayment(paytype+"");
                o.setOrderStatus(4+"");
            }

            o.setOrderComments("");
            //插入订单表
            ordersMapper.insert(o);
            //插入后立马查询一下 找到刚插入的id
            OrdersExample example2 = new OrdersExample();
            OrdersExample.Criteria criteria2 = example2.createCriteria();
            criteria2.andOrderBuyerIdEqualTo(user.getUserId());
            criteria2.andOrderTimeEqualTo(cr_date);
            List<Orders> ordersList=ordersMapper.selectByExample(example2);
            list_return.add(ordersList.get(0));
            //下面插入item
            List<Car> carList=entry.getValue();
            for (Car car :carList)
            {
                Item item = new Item();
                item.setItemOrderId(ordersList.get(0).getOrderId());
                item.setItemProductId(car.getCarProductId());
                item.setItemProductNum(car.getCarProductNum());
                item.setItemProductPrice(car.getCarProductPrice());
                //插入
                itemMapper.insert(item);
                //删除购物车中东西
                carMapper.deleteByPrimaryKey(car.getCarId());
                //商品库存减少 商品 卖出数量增加
                Product product = productMapper.selectByPrimaryKey(car.getCarProductId());
                product.setProductHassSelled(product.getProductHassSelled()+car.getCarProductNum());
                product.setProductStock(product.getProductStock()-car.getCarProductNum());
                productMapper.updateByPrimaryKey(product);
            }
            //业务逻辑完成
        }
        return list_return;
    }

    @Override
    public double selectOrderByuser(Long order) {
        Orders orders=ordersMapper.selectByPrimaryKey(order);
        OrdersExample example=new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andOrderTimeEqualTo(orders.getOrderTime());
        List<Orders> list = ordersMapper.selectByExample(example);
        double sumprice = 0.00;
        for(Orders orders1 : list)
        {
            sumprice+=orders1.getOrderTotalPrice();
        }
        return sumprice;
    }

    @Override
    public void updateOrderStatus(int all, HttpServletRequest request, Long[] orderSn) {
        if(all==1)
        {
           for(Long l:orderSn)
           {
               if(l!=null)
               {
                   Orders orders=ordersMapper.selectByPrimaryKey(l);
                   //说明只结账一个商家的
                   orders.setOrderStatus(1+"");
                   ordersMapper.updateByPrimaryKey(orders);
               }else {
                   break;
               }
           }

        }else{
            Orders orders=ordersMapper.selectByPrimaryKey(orderSn[0]);
            OrdersExample example=new OrdersExample();
            OrdersExample.Criteria criteria = example.createCriteria();
            criteria.andOrderTimeEqualTo(orders.getOrderTime());
            List<Orders> list = ordersMapper.selectByExample(example);
            for(Orders orders1 : list)
            {
                orders1.setOrderStatus(1+"");
                ordersMapper.updateByPrimaryKey(orders1);
            }
        }

    }
}
