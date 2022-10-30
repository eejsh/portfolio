package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;

import dataconfig.DbConfig;

public class NoticeDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDelController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html; charset=utf-8");
	String idx[] = request.getParameterValues("ckbox");
	
	int ea = idx.length;
	
	PrintWriter pw = response.getWriter();
	
	Connection ct = null;
	PreparedStatement ps = null;
	
	try {
		DbConfig db = new DbConfig();
		ct = db.cafe24();

		int w = 0;
		int n = 0;

		String sql = null;
		while(w<=ea) {
			sql = "delete from notice where idx ='" + idx[w] + "'";
			ps = ct.prepareStatement(sql);
			n = ps.executeUpdate();
			
			if (n > 0) {
				pw.print("<script>alert('성공적으로 삭제되었습니다.');"
						+ "location.href='./notice?pgno=1';"
						+ "</script>");
				
			} else {
				throw new Exception("error");
			}
			w++;
		} //반복문 끝.. 
		
	} catch (Exception e) {
		pw.print("<script>" + "alert('db저장에 문제가 생겼습니다.');" + "history.go(-1);" + "</script>");
		e.getMessage();

	}finally {
  		 	try {
  				if (ps != null)
  					ps.close();
  				ct.close();
  			} catch (Exception zz) {
  				zz.printStackTrace();
  			}
  	 	}

}
	
	
}
