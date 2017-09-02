package com.logistics.serve;



import com.logistics.pojo.Goods;
import com.logistics.pojo.User;
import com.logistics.util.Page;

public interface GoodsServe {
	public Page selectList(User user,int page,int rows);
	public int saveGoods(Goods  goods);
	public Goods selectById(int goodsid);
	public int updateGoods(Goods goods);
	public int deleteGoods(String str);
}
