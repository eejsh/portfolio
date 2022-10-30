package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataconfig.DbConfig;

public class ProductCkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductCkController() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("html/text; charset=utf-8");
    	PrintWriter pw = response.getWriter();
	
    	String ck = request.getParameter("ckpro");
    		
    	DbConfig db = null;
    	Connection con = null;
    	String ckok = null;
    	PreparedStatement ps =null;
    	
    	try {
    		db = new DbConfig();
    		con = DbConfig.cafe24();
    		
    		String sql = "select * from productcode where pcode = '"+ ck +"'";
    		
    		ps = con.prepareStatement(sql);
    		int n=0;
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()==true) { 
    			//중복시 
    			pw.print("no");
    		}else {
    			pw.print("ok");
    			
    		}
    	}catch (Exception e) {
    	e.getMessage();
    	}finally {
   		 
   			try {
   				if (ps != null)
   					ps.close();
   			} catch (Exception zz) {
   				zz.printStackTrace();
   			}
   			try {
   				if (con != null)
   					
   					con.close();
   					ps.close();
   			} catch (Exception z) {
   			z.getMessage();
   			}
   	 	}
   	 	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}
