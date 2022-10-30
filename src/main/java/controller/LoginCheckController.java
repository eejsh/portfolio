package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginModel;

public class LoginCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginCheckController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String ckmid = request.getParameter("ckmid").replaceAll(" ", "").intern();
		LoginModel ds = new LoginModel();

		try {
			ds.Results(ckmid);
			String dbok = ds.aa;

			if (dbok == "no") {
				response.getWriter().print("no");

			} else if (dbok == "ok") {
				response.getWriter().print("ok");

			} else {
				throw new Exception("error");
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

}
