package com.logistics.serve.serveIm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.logistics.Dao.OrderDao;
import com.logistics.pojo.Order;
import com.logistics.pojo.User;
import com.logistics.serve.OrderServe;
import com.logistics.serve.UtilServer;
import com.logistics.util.DateUtil;
import com.logistics.util.Page;
@Service(value="orderServe")
public class OrderServeIm implements OrderServe {
	@Resource(name="orderDao")
	public OrderDao orderDao;
	@Resource(name="UtilService")
	public UtilServer util;
	@Override
	public int insertOrder(Order order, User user){
		// 添加运单公司
		order.setCompanyno(user.getCompanyno());
		// 添加录入人
		order.setUseridfk(user.getUserid());
		//添加录入部门
		order.setStartdept(user.getDeptno());
		// 查找公司最大的运单自增一,来得出当前运单号
		int orderNo=orderDao.getMaxOrderNo(order.getCompanyno())+1;
		order.setOrderno(orderNo);
		//添加操作时间
		order.setOperdate(new DateUtil().getNowDate());
		//添加运单状态——录入在库1
		order.setOrderstate(1);
		if(order.getStartdept()==order.getGoaldept())
			return 4;
		
		int n=orderDao.insertOrder(order);
		
		return n!=0?1:0;
		
	}
	@Override
	public Map<String, Integer> updateOrder(Order order,User user) {
		Map<String, Integer> map=new HashMap<String, Integer>();
		order.setCompanyno(user.getCompanyno());
		order.setStartdept(user.getDeptno());
		int n=orderDao.updateOrder(order);
		if(n!=0){
			map.put("status",1);
		}else map.put("status",3);
		return map;
	}
	@Override
	public Map<String, Object> selectOrder(Order order,User user,int page,int rows) {
		Map<String, Object> map=new HashMap<String, Object>();
		Page<Order> pageMas=new Page<Order>(0, 0);
		order.setCompanyno(user.getCompanyno());
		order.setStartdept(user.getDeptno());
		
		pageMas.setParams(order);
		pageMas.setPageNo(page);
		pageMas.setPageSize(rows);
		
		List<Order> orderList=orderDao.selectOrder(pageMas);
		map.put("rows", orderList);
		map.put("total",pageMas.getPageTotal());
		return map;
	}
	@Override
	public int deleteOrder(Order order) {
		int n=orderDao.deleteOrder(order);
		return n;
	}
}
