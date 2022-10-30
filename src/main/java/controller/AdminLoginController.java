package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.mail.Session;
import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminModel;
import model.LoginModel;


public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminLoginController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		String mid = request.getParameter("adid").intern();
		String mpass = request.getParameter("adpw").intern();
		
		try {
		
			LoginModel ds = new LoginModel();
			ds.results_ok(mid);
			
		String dbid = ds.loginok[0].intern();
		String dbpw = ds.loginok[1].intern();
		String dbnm = ds.loginok[2].intern();
		String dbapp = ds.loginok[3].intern();
		
		
		if(dbid==mid && dbpw == mpass) {
		   if(dbapp=="N") {
				pw.print("<script>alert('승인 대기 중입니다.. '); history.go(-1);</script>");
			}else if(dbapp=="Y"){

				AdminModel dbin = new AdminModel(mid); //log용
				
				HttpSession se = request.getSession();
				se.setAttribute("dbnm", dbnm);
				se.setAttribute("mid", mid);
				
				pw.print("<script>"
			       		+ "alert('로그인 성공');"
			       		+ "location.href='./main';"
			       		+ "</script>");
			}
		   
		}
		else if(dbid==mid && dbpw != mpass) {
			
			pw.print("<script>"
					+ "alert('비밀번호가 틀립니다. '); location.href='./index.html'; "
					+ "</script>");
			
		}else {
		    throw new Exception("error");
		}
		} catch (Exception e) {
		pw.print("<script>alert('해당 아이디는 등록되지 않았습니다.');location.href='./index.html'; </script>");
			
		}
				
	}

}
