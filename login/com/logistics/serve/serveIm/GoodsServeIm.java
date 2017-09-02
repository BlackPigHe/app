package com.logistics.serve.serveIm;

import java.util.List;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.logistics.Dao.GoodsDao;
import com.logistics.pojo.Goods;
import com.logistics.pojo.User;
import com.logistics.serve.GoodsServe;
import com.logistics.util.Page;

@Service(value="GoodsServeIm")
public class GoodsServeIm implements GoodsServe{

	@Resource(name="goodsImpl")
	public GoodsDao GoodsDaoImpl;
	

	
	
	@Override
	public Page selectList(User user, int page, int rows) {
		int count = GoodsDaoImpl.count(user);
		Page pag = new Page(page,rows,count);
		
		pag.setParams(user);
		List<Goods> list = GoodsDaoImpl.selectGoodsList(pag);
		pag.setDataList(list);
		return pag;
	}


	@Override
	public int saveGoods(Goods goods) {
		try{
			GoodsDaoImpl.saveGoods(goods);
			return 1;
		}catch(Exception e){
			return 0;
		}
		
	}


	@Override
	public Goods selectById(int goodsid) {
		Goods goods = GoodsDaoImpl.findById(goodsid);
		return goods;
	}


	@Override
	public int updateGoods(Goods goods) {
		try{
		this.GoodsDaoImpl.updategoods(goods);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}


	@Override
	public int deleteGoods(String str) {
		String[] strA = str.split(",");
		try{
			this.GoodsDaoImpl.deletegoods(strA);
				return 1;
			}catch(Exception e){
				return 0;
			}
	}




}
