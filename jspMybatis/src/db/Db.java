package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public interface Db {

	public Connection dbConn();
	public void dbConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn);
	
	
	
}
