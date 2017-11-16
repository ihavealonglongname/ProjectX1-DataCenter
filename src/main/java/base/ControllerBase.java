package base;

import java.util.ArrayList;
import java.lang.reflect.*;
import java.sql.*;
import base_db.DBAccess;

public class ControllerBase<ctrl> {
	
	private DBAccess dbAccess = new DBAccess();
	
	protected ArrayList<ctrl> queryAll(){
		ArrayList<ctrl> targetDataList = new ArrayList<ctrl>();
		
		dbAccess.Connect();
		
        Statement st;
		try {
			Type t = getClass().getGenericSuperclass();
        	Class<ctrl> entityClass = (Class<ctrl>)((ParameterizedType)t).getActualTypeArguments()[0];
        	
        	DB_Table dbTable = entityClass.getAnnotation(DB_Table.class);
        	String tableName = dbTable.name();
        	
			st = dbAccess.conn.createStatement();
	        String sql = " SELECT * from " + tableName + ";";  
	        ResultSet rs = st.executeQuery(sql);  
	        while(rs.next()){
	            try {
	            	ctrl data = entityClass.newInstance();
	            	
	            	//SuperClassの処理「id,extend1,extend2,extend3」
					Field f = entityClass.getSuperclass().getDeclaredField("id");
					f.setAccessible(true);  
					f.set(data, rs.getString("id"));
					
					//全部のフィールド
					Field[] fields = entityClass.getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {
						Field field = fields[i];
						field.setAccessible(true);
						DB_Field dbField = field.getAnnotation(DB_Field.class);
						field.set(data, rs.getString(dbField.name()));
					}
					
					targetDataList.add(data);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	        }  
	        rs.close();  
	        st.close();  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		dbAccess.DisConnect();

		return targetDataList;
	}
}
