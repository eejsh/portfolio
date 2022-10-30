package model;

import java.sql.*;
import java.util.ArrayList;

import dataconfig.DbConfig;


public class CouponModel {
	

	String result= null;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public void cou_insert(ArrayList<String> cp) throws ClassNotFoundException, SQLException {
	
		
		DbConfig db = new DbConfig();
		Connection con = db.cafe24();
		
		String sql = "insert into coupon value('0',?,?,?,?,?,?,?,?,?) ";
		
		PreparedStatement ps = con.prepareStatement(sql);
			
		int w = 1;
		while(w <= cp.size()) {
			ps.setString(w, cp.get(w-1));
			w++;
		}
		
		int n = ps.executeUpdate();
		if(n >0 ) {
			this.result = "ok";
			con.close();
		}
		else {
			this.result = "no";
		}
		
		ps.close();
		con.close();
		
		
	}	
}
