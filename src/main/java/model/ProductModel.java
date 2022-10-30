package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dataconfig.DbConfig;

public class ProductModel {
	
	
	private String sign = null;

	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
	public void pro_insert(ArrayList<String> prolist) throws ClassNotFoundException, SQLException {
		
		DbConfig db = new DbConfig();
		Connection con = db.cafe24();
		
		String sql = "insert into productcode values('0',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
				
		int w = 1;
		while(w <= prolist.size()) {
			ps.setString(w, prolist.get(w-1));
			w++;
		}
		
		int n = ps.executeUpdate();
		
		if(n>0) {
			this.setSign("ok");
			
			con.close();
		}else {
			this.setSign("no");
		}
		
		ps.close();
		con.close();
			
	}


}
