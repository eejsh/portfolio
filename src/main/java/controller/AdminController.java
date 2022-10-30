package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminModel;

public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public AdminController() {
    }

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 response.setContentType("text/html; charset=utf-8"); 
		
	

		 String mid = request.getParameter("mid");
		 String mpass = request.getParameter("mpass");
		 String mname = request.getParameter("mname");
		 String memail = request.getParameter("memail");
		 String mtel1 = request.getParameter("mtel1");
		 String mtel2 = request.getParameter("mtel2");
		 String mtel3 = request.getParameter("mtel3");
		 String mdepart = request.getParameter("mdepart");
		 String mpo = request.getParameter("mpo");

		 PrintWriter pw = response.getWriter();
		 AdminModel di = new AdminModel(mid, mpass, mname, memail, mtel1, mtel2, mtel3, mdepart, mpo);
		  
		 try {		 
			 String sign= di.insql().intern();
			 if(sign=="ok") {
		
			 pw.print("<script>alert('회원등록이 되었습니다. '); location.href='./index.html'; </script>");
			 
			 
			 }else {
				 throw new Exception("error");
			 }
		 }catch(Exception e) {
			 pw.print("<script>"
					+ "alert('db저장에 문제가 생겼습니다.');"
			 		+ "history.go(-1);"
			 		+ "</script>");
		 }
		 
		 
	}

}
