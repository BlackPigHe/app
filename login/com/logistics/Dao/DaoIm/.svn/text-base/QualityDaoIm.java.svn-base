package com.logistics.Dao.DaoIm;

import com.logistics.Dao.QualityDao;
import com.logistics.pojo.Order;

public class QualityDaoIm extends QualityDao{

	@Override
	public Order findOrderno(Order order) {
		Order ord = this.getSqlSession().selectOne("Order.selectOrder", order);
		return ord;
	}

	@Override
	public void saveQuality() {
		
	}

}
