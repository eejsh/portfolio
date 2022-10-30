package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataconfig.Timer;
import model.CategoryModel;

public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryController() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 Timer tr = new Timer();
		 String cindate = tr.now_time();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
	
		String ccode = request.getParameter("ccode");
		String c1code = request.getParameter("c1code");
		String c1codenm = request.getParameter("c1codenm").trim();
		String c2code = request.getParameter("c2code");
		String c2codenm = request.getParameter("c2codenm").trim();
	
		String cateuse = request.getParameter("cateuse");
	
		String c1codeonly = request.getParameter("c1codeonly");
		if(c1codeonly==null){
			c1codeonly="N";
		}

		if(c2code == null || c2code ==" ") {
			c2code = " ";
			c2codenm = " ";
		}
		
		
		ArrayList<String>cateinfo = new ArrayList<>();
		cateinfo.add(ccode);
		cateinfo.add(c1code);
		cateinfo.add(c1codenm);
		cateinfo.add(c1codeonly);
		cateinfo.add(c2code);
		cateinfo.add(c2codenm);
		cateinfo.add(cateuse);
		cateinfo.add(cindate);
		
		CategoryModel lc = new CategoryModel();
		
		
		try {

			lc.list_cateinsert(cateinfo);
			
			String sign = lc.getRe();
			
			if(sign=="ok") {
				pw.print("<script>alert('저장되었습니다.'); location.href='./category?pgno=1'; </script>");
			}else {
				throw new Exception("error");				
			}
			
		} catch (Exception ee) {
			ee.getMessage();
			 pw.print("<script>"
			+ "alert('db저장에 문제가 생겼습니다.');"
	 		+ "history.go(-1);"
	 		+ "</script>"); 
		
		}
		
		
	}

}