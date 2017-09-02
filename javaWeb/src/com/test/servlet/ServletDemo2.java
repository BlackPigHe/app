package com.test.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo2
 */
@WebServlet("/ServletDemo2")
public class ServletDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
       String user_Name="17835395677";
       String user_Pwd="hezijiang";
       String res=user_Name+","+user_Pwd;
       byte[] b=res.getBytes();
       OutputStream outputStream = response.getOutputStream();
       outputStream.write(b);
       /*int len=0;
       byte[] b=new byte[1024];
       while((len=b)!=0){
    	   
       }*/
       //PrintWriter pw=new PrintWriter(response.getOutputStream(),true);
       //pw.write("user_Name"+user_Name+"user_Pwd"+user_Pwd);
       //pw.write("<font>hehe</font>");
       //ServletOutputStream outputStream = response.getOutputStream();
       //outputStream.write(b, 0, len);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
