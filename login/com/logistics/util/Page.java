package  com.logistics.util;

import java.util.List;

public class Page<T> {
   private int pageNo;
   private int pageSize = 5;
   private int rows;
   private int pageTotal;
   private List<T> dataList;
   private Object params;



   
   
   public Page(int pageNo,int pageSize){
	   this.pageSize = pageSize;
	   this.pageNo = pageNo;
   }
   public Page(){
	  
   }
   
   public Page(int pageNo,int pageSize, int rows){
	
	   this.pageSize = pageSize;
	   this.rows = rows;
	   this.pageTotal = this.rows / this.pageSize + (this.rows%this.pageSize==0?0:1);
	   if(pageNo<=0){
		   this.pageNo = 1;
	   }else{
		   this.pageNo = pageNo;
	   }
	   
	   
   }
   
   public static void main(String args[]){
	     int m = 10 /3;
	     System.out.println( m );
   }

public int getPageNo() {
	return pageNo;
}

public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
}

public int getPageSize() {
	return pageSize;
}

public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}

public int getRows() {
	return rows;
}

public void setRows(int rows) {
	this.rows = rows;
    this.pageTotal = this.rows / this.pageSize + (this.rows%this.pageSize==0?0:1);
	if(this.pageNo<=0){
		 this.pageNo = 1;
	}else if(pageNo>=this.pageTotal){
		 this.pageNo = this.pageTotal;
	 }else{
		 this.pageNo = pageNo;
	  }
}

public int getPageTotal() {
	return pageTotal;
}

public void setPageTotal(int pageTotal) {
	this.pageTotal = pageTotal;
}

public List<T> getDataList() {
	return dataList;
}

public void setDataList(List<T> dataList) {
	this.dataList = dataList;
}

public Object getParams() {
	return params;
}

public void setParams(Object params) {
	this.params = params;
}
   


   
}
