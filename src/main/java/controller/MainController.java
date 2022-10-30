package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginModel;


public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainController() {
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		
		 LoginModel dbse = new LoginModel();
		 ArrayList<LoginModel> datas = dbse.viewdb();

		 
		request.setAttribute("list", datas);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/admin_main.jsp");
		rd.forward(request, response);
		
		
	}catch(Exception e) {
		
	}
	
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
