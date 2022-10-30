package model;

import java.sql.*;

import dataconfig.DbConfig;
import dataconfig.Timer;

public class AdminModel {

	private String mid = null;
	private String mpass = null;
	private String mname = null;
	private String memail = null;
	private String mtel1 = null;
	private String mtel2 = null;
	private String mtel3 = null;
	private String mdepart = null;
	private String mpo = null;
	private String mapp = null;
	
	 Timer tr = new Timer();
	 String mindate = tr.now_time();
	 
	 
	public AdminModel(String mid, String mpass, String mname, String memil, String mtel1, String mtel2, String mtel3,
			String mdepart, String mpo){
	
		this.mid = mid;
		this.mpass = mpass;
		this.mname = mname;
		this.memail = memil;
		this.mtel1 = mtel1;
		this.mtel2 = mtel2;
		this.mtel3 = mtel3;
		this.mdepart = mdepart;
		this.mpo = mpo;
		this.mapp = "N";
		
	}
	
	public AdminModel(String mid) {
	 this.mid = mid;
	 Connection con = null;
	 PreparedStatement ps = null;
	 
	 
	 try {
		 DbConfig db = new DbConfig();	
		 con = DbConfig.cafe24();
		 String sql = "insert into loginlog values('0','"+this.mid+"','"+this.mindate+"')";
		 ps = con.prepareStatement(sql);
		 
		 String msg1 = null;
		 int n = 0;
		 n = ps.executeUpdate();
		 if(n>0) {
			 msg1 = "ok";
		 }else {
			 msg1="no";
		 }

	 }catch(Exception c) {
		c.getMessage();
		
	 }finally {
		 
			try {
				if (ps != null)
					ps.close();
			} catch (Exception zz) {
				zz.printStackTrace();
			}
			try {
				if (con != null)
					
					con.close();
					ps.close();
			} catch (Exception z) {
			z.getMessage();
			}
	 	}
	 
	 
	}

	public String insql() throws ClassNotFoundException, SQLException{

		DbConfig db = new DbConfig();
		Connection ct = db.cafe24();
		
		String sql ="insert into login values('0',?,?,?,?,?,?,?,?,?,?,?,'0001-01-01 01:00:00')" ;
		PreparedStatement ps = ct.prepareStatement(sql);
	
		ps.setString(1, this.mid);
		ps.setString(2, this.mpass);
		ps.setString(3, this.mname);
		ps.setString(4, this.memail);
		ps.setString(5, this.mtel1);
		ps.setString(6, this.mtel2);
		ps.setString(7, this.mtel3);
		ps.setString(8, this.mdepart);
		ps.setString(9, this.mpo);
		ps.setString(10, this.mapp);
		ps.setString(11, this.mindate);
		
		int n = 0;
		n = ps.executeUpdate();
		String msg = null;
		if(n>0) {
			msg ="ok";
		}else {
			msg ="no";
		}
		
		ct.close();
		ps.close();
		
		return msg;
	}
	

}