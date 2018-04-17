package base;

import java.util.ArrayList;
import java.lang.reflect.*;
import java.sql.*;
import base_db.DBAccessor;

public class ControllerBase<model_data> {
	
	private Class<model_data> model_data_class;
	
	public ControllerBase() {
		Type type = getClass().getGenericSuperclass();
		model_data_class = (Class<model_data>)((ParameterizedType)type).getActualTypeArguments()[0];
	}
	
	public ArrayList<model_data> queryAll(){
		ArrayList<model_data> targetDataList = new ArrayList<model_data>();
		
        Statement st;
		try {
			Connection conn = DBAccessor.getConnection();
			
        	
        	DB_Table dbTable = model_data_class.getAnnotation(DB_Table.class);
        	String tableName = dbTable.name();
        	
			//st = dbAccess.conn.createStatement();
        	st = conn.createStatement();
	        String sql = " SELECT * from " + tableName + ";";  
	        ResultSet rs = st.executeQuery(sql);  
	        while(rs.next()){
	            try {
	            	model_data data = model_data_class.newInstance();
	            	
	            	//SuperClassの処理「id,extend1,extend2,extend3」
					Field f = model_data_class.getSuperclass().getDeclaredField("id");
					f.setAccessible(true);  
					f.set(data, rs.getString("id"));
					
					//全部のフィールド
					Field[] fields = model_data_class.getDeclaredFields();
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
//	        rs.close();  
//	        st.close();  
	        
	        DBAccessor.release(conn, st, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		//dbAccess.DisConnect();
		
		return targetDataList;
	}
}
