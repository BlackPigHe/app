package com.logistics.mybatis.plugin;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DialectSql {
	   public String toCountSql(String primeSql){
		    int orderIndex = getLastOrderInsertPoint(primeSql);  
	        int formIndex = getAfterFormInsertPoint(primeSql);  
	        
	        String select = primeSql.substring(0, formIndex);  
	  
	        if (select.toLowerCase().indexOf("select distinct") != -1 || primeSql.toLowerCase().indexOf("group by") != -1) {  
	            return new StringBuffer(primeSql.length()).append("select count(1)  from (").append(primeSql.substring(0, orderIndex)).append(" ) t").toString();  
	        } else {  
	            return new StringBuffer(primeSql.length()).append("select count(1)  ").append(primeSql.substring(formIndex, orderIndex)).toString();  
	        }  
	   }
	   public String pageSql(String primeSql,int offset, int limit){
			primeSql = getLineSql(primeSql);  
//		    String sql = primeSql.replaceAll("[^\\s,]+\\.", "") + " limit " + offset + " ," + limit;  
		    String sql = primeSql + " limit " + offset + " ," + limit;  
		    System.out.println("linpage sql=="+sql);
		    return sql;  
	   }
	   
	   private  String getLineSql(String sql) {  
	        return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");  
	   }  
	   
	   
	   private  int getAfterFormInsertPoint(String querySelect) {  
	        String regex = "\\s+FROM\\s+";  
	        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);  
	        Matcher matcher = pattern.matcher(querySelect);  
	        while (matcher.find()) {  
	            int fromStartIndex = matcher.start(0);  
	            String text = querySelect.substring(0, fromStartIndex);  
	            if (isBracketCanPartnership(text)) {  
	                return fromStartIndex;  
	            }  
	        }  
	        return 0;  
	    } 

	   
	   private  boolean isBracketCanPartnership(String text) {  
	        if (text == null || (getIndexOfCount(text, '(') != getIndexOfCount(text, ')'))) {  
	            return false;  
	        }  
	        return true;  
	    }  
	   
	    private  int getIndexOfCount(String text, char ch) {  
	        int count = 0;  
	        for (int i = 0; i < text.length(); i++) {  
	            count = (text.charAt(i) == ch) ? count + 1 : count;  
	        }  
	        return count;  
	    }  
	    
	    private  int getLastOrderInsertPoint(String querySelect) {  
	        int orderIndex = -1 ;
	        orderIndex = querySelect.toLowerCase().lastIndexOf("order by"); 
	        System.out.println("lastIndexOf orderIndex=="+orderIndex);
	        if (orderIndex == -1 || !isBracketCanPartnership(querySelect.substring(orderIndex, querySelect.length()))) {  
	            throw new RuntimeException("My SQL Order by");  
	        }  
	        return orderIndex;  
	    }  
	    
	    
	    public static  void setFieldValue(Object target, String filedName,Object val){
	    	Class classObj = target.getClass();
	    	try {
				Field field = classObj.getDeclaredField(filedName);
				if(!field.isAccessible()){
					field.setAccessible(true);
				}
				field.set(target, val);
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
}
