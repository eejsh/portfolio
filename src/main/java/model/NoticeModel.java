package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import dataconfig.DbConfig;

public class NoticeModel {

	String result = null;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void notice_insert(ArrayList<String> noins) {

		try {

			DbConfig db = new DbConfig();
			Connection ct = db.cafe24();

			String sql = "insert notice values('0',?,?,?,?,?,0,?,'0001-00-00 01:00:00')";

			PreparedStatement ps = ct.prepareStatement(sql);

			int w = 1;
			while (w <= noins.size()) {
				ps.setString(w, noins.get(w - 1));
				w++;
			}
	
			
			int n = 0;
			n = ps.executeUpdate();
			
			
			if (n > 0) {
				this.result = "ok";
				
			} else {
				this.result = "no";

			}
			ps.close();
			ct.close();

		} catch (Exception e) {

		}

	}
}