package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataconfig.DbConfig;

public class AdminUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminUpdateController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = response.getWriter();
		
	

	String mid = request.getParameter("mid");	
	String mpass = request.getParameter("mpass");
	String mname = request.getParameter("mname");
	String memail = request.getParameter("memail");
	String mtel1 = request.getParameter("mtel1");
	String mtel2 = request.getParameter("mtel2");
	String mtel3 = request.getParameter("mtel3");
	String mdepart = request.getParameter("mdepart");
	String mpo = request.getParameter("mpo");
	
	PreparedStatement ps = null;
	String sql = null;
	Connection ct= null;
	
	
	try {
		
		DbConfig db = new DbConfig();
		ct = db.cafe24();
		
		
	ArrayList<String> aa = new ArrayList<String>();
	
	aa.add(mpass);
	aa.add(mname);
	aa.add(memail);
	aa.add(mtel1);
	aa.add(mtel2);
	aa.add(mtel3);
	aa.add(mdepart);
	aa.add(mpo);
	
	aa.add(mid);

	
	int n =0;
	if(mpass=="") {
		sql = "update login set mname=?, memail=?, mtel1=?, mtel2=?, mtel3=?, mdepart=?, mpo=? where mid=? ";
		ps = ct.prepareStatement(sql);
		int w= 1;
		while(w<=aa.size()) {
			ps.setString(w,aa.get(1));
			ps.setString(w,aa.get(2));
			ps.setString(w,aa.get(3));
			ps.setString(w,aa.get(4));
			ps.setString(w,aa.get(5));
			ps.setString(w,aa.get(6));
			ps.setString(w,aa.get(7));
			ps.setString(w,aa.get(8));
		w++;
			}
		n = ps.executeUpdate();

	}else {
		
		 sql = "update login set mpass=?, mname=?, memail =? ,mtel1=?, mtel2=?, mtel3=?, mdepart=?, mpo=? where mid =? " ;
		 ps = ct.prepareStatement(sql);
		
		int w = 1;
		while(w<=aa.size()) {
			ps.setString(w, aa.get(w-1));
			w++;
		}
		n = ps.executeUpdate();
		
	}
	
	if(n == 1) {
		pw.print("<script>alert('등록이 되었습니다. '); location.href='./main'; </script>");
	}else {
		pw.print("<script>" + "alert('db저장에 문제가 생겼습니다.');" + "history.go(-1);" + "</script>");
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
					if (ps!= null)
					ps.close();
					ct.close();
					} catch (Exception z) {
				z.getMessage();
				}
		 	}
		 
	
	
	
	}

}
