package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataconfig.DbConfig;

public class AdminOKController extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    public AdminOKController() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//회원 승인파트
		
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String dbno = request.getParameter("midx");
		
		String[] aa = dbno.split(",");
		String inx = aa[0];
		String ck = aa[1];
		
		try {
			DbConfig db = new DbConfig();
			Connection ct = db.cafe24();
			
			String sql = "update login set mapp=? where midx =? ";
			PreparedStatement ps = ct.prepareStatement(sql);

			ps.setString(1, ck);
			ps.setString(2, inx);
			
		    int n=0;
		    n = ps.executeUpdate();
		    
		    if(n>0) {
		    	if(ck.equals("Y")) {
		    		response.getWriter().print("Y");
		    	}else if(ck.equals("N")) {
		    		response.getWriter().print("N");
		    	}
		    	
		    }else {
		    	throw new Exception ("error");
		    	
		    }
		    ct.close();
		}catch(Exception e) {
				e.getMessage();			
		
		}
		
	}

}
