package com.logistics.serve;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.logistics.pojo.Order;
import com.logistics.pojo.User;

public interface OrderServe {
	public int insertOrder(Order order, User user) throws Exception;
	public Map updateOrder(Order order,User user);
	public Map selectOrder(Order order, User user, int page, int rows);
	public int deleteOrder(Order order);
}
