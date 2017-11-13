package db;

import org.postgresql.*;  
import java.sql.*; 

public class DBAccess {
	
	private String url = "testdb.cur5zf1lggee.ap-northeast-1.rds.amazonaws.com";
	private String dbName = "mydbtest01";
	private Connection conn = null;
	
	public boolean Connect() {
		 try{  
	         Class.forName("org.postgresql.Driver").newInstance();  
	         String connectUrl ="jdbc:postgresql://" + url + ":5432/" + dbName;  
	         conn = DriverManager.getConnection(connectUrl, "pfs", "zaq1xsw2");
	         
//	         Statement st = conn.createStatement();  
//	         String sql = " SELECT 1;";  
//	         ResultSet rs = st.executeQuery(sql);  
//	         while(rs.next()){  
//	             System.out.println(rs.getInt(1));  
//	         }  
//	         rs.close();  
//	         st.close();  
//	         conn.close();  
	         
	         return true;
	     }catch(Exception e){
	    	 	System.out.println(e.toString());
	    	 	return false;
	     }
	}
	
	public void DisConnect() {
		if(conn != null) {
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int executeSql(String sql) {
		if(conn != null) {
			try {
				Statement st = conn.createStatement(); 
		        int rs = st.executeUpdate(sql);  
		        st.close();
		        return rs;
			}catch(Exception e) {
				return -1;
			}
		}else{
			return -1;
		}
	}
	
	
}
