/**
* 模仿天猫整站ssm 教程 为how2j.cn 版权所有
* 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关
* 供购买者学习，请勿私自传播，否则自行承担相关法律责任
*/	

package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;

public interface OrderItemService {


    void add(OrderItem c);

    void delete(int id);
    void update(OrderItem c);
    OrderItem get(int id);
    List list();

    void fill(List<Order> os);

    void fill(Order o);

    int getSaleCount(int  pid);

    List<OrderItem> listByUser(int uid);
}
/**
* 模仿天猫整站ssm 教程 为how2j.cn 版权所有
* 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关
* 供购买者学习，请勿私自传播，否则自行承担相关法律责任
*/	
