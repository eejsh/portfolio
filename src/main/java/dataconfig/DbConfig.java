package dataconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig { 
	
	
	public static Connection cafe24() throws ClassNotFoundException, SQLException{
		
		String dbDriver = "com.mysql.jdbc.Driver";
		//String dburl="jdbc:mysql://localhost:3306/eejsh08";
		String dburl ="jdbc:mysql://umj7-009.cafe24.com/eejsh08";
	    String dbuser = "eejsh08";
	    String dbpw ="hoho12!@";
		
		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(dburl, dbuser, dbpw);
		
		return con;
		
	}
	
	
	
}
