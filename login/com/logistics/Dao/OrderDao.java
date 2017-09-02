package com.logistics.Dao;

import java.util.List;

import com.logistics.pojo.Order;
import com.logistics.util.Page;

public abstract class OrderDao extends BaseDao{
	public abstract int getMaxOrderNo(int companyno);
	public abstract int insertOrder(Order order);
	public abstract List<Order> selectOrder(Page<Order> page);
	public abstract int updateOrder(Order order);
	public abstract int deleteOrder(Order order);
}
