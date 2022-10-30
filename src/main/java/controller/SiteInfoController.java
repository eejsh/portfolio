package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataconfig.Timer;
import model.SiteInfoModel;

public class SiteInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SiteInfoController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	
	//Controller
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();

		Timer tr = new Timer();
		String indate = tr.now_time();

		String hhomename = request.getParameter("hhomename");
		String hsiteemail = request.getParameter("hsiteemail");
		String hcpnm = request.getParameter("hcpnm");
		String hcpreginum = request.getParameter("hcpreginum");
		String hcpceo = request.getParameter("hcpceo");
		String hcptel = request.getParameter("hcptel");
		String hcpereginum = request.getParameter("hcpereginum");
		String hcpesubreginum = request.getParameter("hcpesubreginum");
		String hcpzcord = request.getParameter("hcpzcord");
		String hcpaddr = request.getParameter("hcpaddr");
		String hcpcpo = request.getParameter("hcpcpo");
		String hcpcpomail = request.getParameter("hcpcpomail");

		String abanknm = request.getParameter("abanknm");
		String abanknum = request.getParameter("abanknum");
		String acard = request.getParameter("acard");
		String aphone = request.getParameter("aphone");
		String agiftmoney = request.getParameter("agiftmoney");
		String aminpo = request.getParameter("aminpo");
		String amaxpo = request.getParameter("amaxpo");
		String acashrece = request.getParameter("acashrece");
		String ashipcom = request.getParameter("ashipcom");
		String ashipcost = request.getParameter("ashipcost");
		String ashipday = request.getParameter("ashipday");

		String pointuse = request.getParameter("pointuse");
		String point = request.getParameter("point");
		String basiclevel = request.getParameter("basiclevel");

		ArrayList<String> homeprefer = new ArrayList<>();
		homeprefer.add(hhomename);
		homeprefer.add(hsiteemail);
		homeprefer.add(hcpnm);
		homeprefer.add(hcpreginum);
		homeprefer.add(hcpceo);
		homeprefer.add(hcptel);
		homeprefer.add(hcpereginum);
		homeprefer.add(hcpesubreginum);
		homeprefer.add(hcpzcord);
		homeprefer.add(hcpaddr);
		homeprefer.add(hcpcpo);
		homeprefer.add(hcpcpomail);
		homeprefer.add(indate);

		ArrayList<String> payment = new ArrayList<>();
		payment.add(abanknm);
		payment.add(abanknum);
		payment.add(acard);
		payment.add(aphone);
		payment.add(agiftmoney);
		payment.add(aminpo);
		payment.add(amaxpo);
		payment.add(acashrece);
		payment.add(ashipcom);
		payment.add(ashipcost);
		payment.add(ashipday);
		payment.add(indate);

		ArrayList<String> preferpoints = new ArrayList<>();
		preferpoints.add(pointuse);
		preferpoints.add(point);
		preferpoints.add(basiclevel);
		preferpoints.add(indate);

		SiteInfoModel ai = new SiteInfoModel();
		
		try {
			
			String[] sign = ai.call();
			ai.a(homeprefer);
			ai.b(payment);
			ai.c(preferpoints);

			if (sign[0] == "ok" && sign[1] == "ok" && sign[2] == "ok") {

				pw.print("<script>alert('등록이 되었습니다. '); location.href='./siteinfo'; </script>");

			} else {
				throw new Exception("error");
			}

		} catch (Exception e) {

			pw.print("<script>" + "alert('db저장에 문제가 생겼습니다.');" + "history.go(-1);" + "</script>");

		}

	}

}