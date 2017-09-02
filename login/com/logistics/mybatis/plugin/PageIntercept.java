package com.logistics.mybatis.plugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import com.logistics.util.Page;


@Intercepts({@Signature(
		  type= StatementHandler.class,
		  method = "prepare",
		  args = {Connection.class})})
public class PageIntercept implements Interceptor{
	private String pageSign;
	protected ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
	protected ObjectFactory objectFactory = new DefaultObjectFactory();
	protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("111111111111111111");
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		BoundSql boundSql = statementHandler.getBoundSql();
		System.out.println("bsql==" +boundSql.getSql() );
		String srcSql = boundSql.getSql();
		MetaObject metaStatementHandler =  MetaObject.forObject(statementHandler, objectFactory, objectWrapperFactory, reflectorFactory);	
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		String sqlId = mappedStatement.getId();
		System.out.println("sqlId==" +mappedStatement.getId());
		
		if(sqlId.endsWith(this.pageSign)){
	      Connection connection = (Connection) invocation.getArgs()[0];  
     
		  Object parameterObject = boundSql.getParameterObject();
		  System.out.println(parameterObject);
		  if(parameterObject instanceof Page<?>){
			  Page<?> page = (Page<?>)parameterObject;
			  setTotalRecordForPage(page,mappedStatement,connection,statementHandler);
			  DialectSql dialectSql = new DialectSql();
			  String pageSql = dialectSql.pageSql(srcSql, (page.getPageNo() -1 ) * page.getPageSize() , page.getPageSize() );
			  DialectSql.setFieldValue(boundSql, "sql", pageSql);
		  }
		}
		return invocation.proceed();
	}
	
	
   private void setTotalRecordForPage(Page<?>page,MappedStatement mappstatement,
		      Connection conn, StatementHandler statementHandler){ 
		BoundSql newSql = statementHandler.getBoundSql();   
		DialectSql dialectSql = new DialectSql();
		String countSql =  dialectSql.toCountSql(newSql.getSql()); 
		System.out.println("countSql=="+countSql);
		List<ParameterMapping> parameterMapping = newSql.getParameterMappings();
		try {
			ResultSet rs = null ;
			int rows = 0;
			PreparedStatement  pstat =  conn.prepareStatement(countSql);
			statementHandler.getParameterHandler().setParameters(pstat);		
			rs = pstat.executeQuery();
			if(rs.next()){
				rows = rs.getInt(1);
			}
			System.out.println("rows=="+rows);
			page.setRows(rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }   	
	
	
	
	

	@Override
	public Object plugin(Object target) {
		 return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		  this.pageSign = properties.getProperty("pageSign");
	}

	public String getPageSign() {
		return pageSign;
	}

	public void setPageSign(String pageSign) {
		this.pageSign = pageSign;
	}

   
	
	
}
