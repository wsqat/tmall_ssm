/**
* 模仿天猫整站ssm 教程 为how2j.cn 版权所有
* 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关
* 供购买者学习，请勿私自传播，否则自行承担相关法律责任
*/	

package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.OrderMapper;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderExample;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.OrderItemService;
import com.how2java.tmall.service.OrderService;
import com.how2java.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public void add(Order c) {
        orderMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order c) {
        orderMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public float add(Order o, List<OrderItem> ois) {
        float total = 0;
        add(o);

        if(false)
            throw new RuntimeException();

        for (OrderItem oi: ois) {
            oi.setOid(o.getId());
            orderItemService.update(oi);
            total+=oi.getProduct().getPromotePrice()*oi.getNumber();
        }
        return total;
    }

    public List<Order> list(){
        OrderExample example =new OrderExample();
        example.setOrderByClause("id desc");
        return orderMapper.selectByExample(example);

    }

    @Override
    public List list(int uid, String excludedStatus) {
        OrderExample example =new OrderExample();
        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
        example.setOrderByClause("id desc");
        return orderMapper.selectByExample(example);
    }

    ;

    public void setUser(List<Order> os){
        for (Order o : os)
            setUser(o);
    }
    public void setUser(Order o){
        int uid = o.getUid();
        User u = userService.get(uid);
        o.setUser(u);
    }

}
/**
* 模仿天猫整站ssm 教程 为how2j.cn 版权所有
* 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关
* 供购买者学习，请勿私自传播，否则自行承担相关法律责任
*/	
