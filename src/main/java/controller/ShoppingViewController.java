package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CouponViewModel;


public class ShoppingViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShoppingViewController() {
    }

		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		try {
			CouponViewModel cs = new CouponViewModel();
			ArrayList<CouponViewModel> coupon = cs.coulist();
			request.setAttribute("coupon", coupon);
			RequestDispatcher rd4 = request.getRequestDispatcher("/admin/admin_shopping.jsp");
			rd4.forward(request, response);
			
		}catch (Exception e) {
			e.getMessage();	
		
		}
			
	
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
