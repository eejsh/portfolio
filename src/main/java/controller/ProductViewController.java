package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductViewModel;

public class ProductViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductViewController() {
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색
		
		ProductViewModel prs= new ProductViewModel();
		
		String pgno = request.getParameter("pgno");
		
		String poption = request.getParameter("poption");
		String psearch = request.getParameter("psearch");	

	try {
		
		prs.aa(pgno);
		prs.poption(poption);
		prs.psearch(psearch);
		

		ArrayList<ProductViewModel> pselect = prs.pvlist();
		request.setAttribute("pselect", pselect);
		
		request.setAttribute("pageview", prs.getPageview());
		request.setAttribute("startpage", prs.getStartpage());
		request.setAttribute("pagenumber", prs.getPagenumber());
		request.setAttribute("total", prs.getTotal());
		request.setAttribute("psearch", psearch);
		request.setAttribute("poption", poption);
		
		RequestDispatcher rd3 = request.getRequestDispatcher("/admin/admin_product.jsp");
		rd3.forward(request, response);
	
	}catch (Exception e) {
			e.getMessage();
	}
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
