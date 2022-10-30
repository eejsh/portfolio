package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryViewModel;

public class CategoryViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CategoryViewController() {
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CategoryViewModel llc = new CategoryViewModel();
		String pgno = request.getParameter("pgno");
		
		String coption = request.getParameter("coption");
		String csearch = request.getParameter("csearch");	
		
		
		try {
			llc.aa(pgno);
			llc.coption(coption);
			llc.csearch(csearch);
			
			
			ArrayList<CategoryViewModel> clist = llc.catelistt();
			request.setAttribute("clist", clist);
			
			request.setAttribute("pageview", llc.getPageview());
		    request.setAttribute("startpage", llc.getStartpage());
		    request.setAttribute("pagenumber", llc.getPagenumber());
		    request.setAttribute("total", llc.getTotal());
		    request.setAttribute("csearch", csearch);
		    request.setAttribute("coption", coption);
		    
			RequestDispatcher rd3 = request.getRequestDispatcher("/admin/admin_category.jsp");
			rd3.forward(request, response);

		} catch (Exception e) {
			e.getMessage();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}