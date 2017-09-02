package com.logistics.Dao.DaoIm;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.logistics.Dao.GoodsDao;
import com.logistics.pojo.Goods;
import com.logistics.pojo.User;
import com.logistics.util.Page;

@Repository(value="goodsImpl")
public  class GoodsImpl extends GoodsDao{
 
	
	@Override
	public int count(User user) {
		int count = this.getSqlSession().selectOne("Goods.select_count", user);
		System.out.println("goods的count===="+count);
		return count;
	}

	@Override
	public List<Goods> selectGoodsList(Page page) {
		List<Goods> list = this.getSqlSession().selectList("Goods.select_page", page);
		System.out.println("goods的list===="+list.size());
		
		return list;
	}

	@Override
	public void saveGoods(Goods goods) {
		this.getSqlSession().insert("Goods.insert_goods", goods);
	}

	@Override
	public Goods findById(int goodsid) {
		Goods goods = this.getSqlSession().selectOne("Goods.selectById", goodsid);
		return goods;
	}

	@Override
	public void updategoods(Goods goods) {
		this.getSqlSession().update("Goods.update_goods",goods);
		
	}

	@Override
	public void deletegoods(String[] str) {
		this.getSqlSession().delete("Goods.deleteById", str);
	}

	
	
	
}
