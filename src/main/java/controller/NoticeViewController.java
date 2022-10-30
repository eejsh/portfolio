package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NoticeViewModel;


public class NoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NoticeViewController() {
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		
	try {
		String pgno = request.getParameter("pgno");
		NoticeViewModel ns = new NoticeViewModel();
		ns.aa(pgno);
	   
		ArrayList<NoticeViewModel>notice = ns.notilist();
		request.setAttribute("notice", notice);
	    
	    request.setAttribute("pageview", ns.getPageview());
	    request.setAttribute("pagenumber", ns.getPagenumber());
	    request.setAttribute("startpage", ns.getStartpage());
	 
	    RequestDispatcher rd2 = request.getRequestDispatcher("/admin/admin_notice.jsp");
	  rd2.forward(request, response);
	  
		
	}catch(Exception e) {
		e.getMessage();
	}
	
	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
