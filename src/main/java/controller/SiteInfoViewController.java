package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SiteInfoDBModel;

public class SiteInfoViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SiteInfoViewController() {
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			SiteInfoDBModel as = new SiteInfoDBModel();
			ArrayList<SiteInfoDBModel> siteinfo = as.sitedb();
			request.setAttribute("siteinfo", siteinfo);
		RequestDispatcher rd1 = request.getRequestDispatcher("/admin/admin_config.jsp");
			rd1.forward(request, response);
			
		} catch (Exception e) {
		}
		
	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	
	}

}
