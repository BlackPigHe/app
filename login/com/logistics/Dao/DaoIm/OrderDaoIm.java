package com.logistics.Dao.DaoIm;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.logistics.Dao.OrderDao;
import com.logistics.pojo.Order;
import com.logistics.util.Page;

@Repository(value="orderDao")
public class OrderDaoIm extends OrderDao {

	@Override
	public int getMaxOrderNo(int companyno) {
		Integer integer= getSqlSession().selectOne("Order.getMaxOrderNo",companyno);
		int n=integer==null?10000000:integer;
		return n;
	}

	@Override
	public int insertOrder(Order order) {
		return getSqlSession().insert("Order.insertOrder", order);
	}

	@Override
	public List selectOrder(Page<Order> page) {
		return getSqlSession().selectList("Order.selectOrder-page", page);
		
	}

	@Override
	public int updateOrder(Order order) {
		return getSqlSession().update("Order.updateOrder",order);
	}

	@Override
	public int deleteOrder(Order order) {
		 
			getSqlSession().delete("orderDao.deleteOrder",order );
			return 1;
	}
	
}
