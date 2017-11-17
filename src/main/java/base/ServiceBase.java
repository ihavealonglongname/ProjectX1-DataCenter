package base;

import java.lang.reflect.*;
import java.util.ArrayList;

import model_data.Account;

public class ServiceBase<controller, model_data> {
	
	private Class<controller> controller_class;
	private controller ctrl;
//	private Class model_data_class;
	
	public ServiceBase() {
    	try {
//			Field f_model_data_class = controller_class.getSuperclass().getDeclaredField("model_data_class");
//			f_model_data_class.setAccessible(true);
    		
    		Type type = getClass().getGenericSuperclass();
        	controller_class = (Class<controller>)((ParameterizedType)type).getActualTypeArguments()[0];
			ctrl = controller_class.newInstance();
			
//			model_data_class = (Class) f_model_data_class.get(ctrl);
			
//			System.out.println(obj.getName());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//    	System.out.println(controller_class.getCanonicalName());
//    	System.out.println(controller_class.getName());
	}
	
	protected ArrayList<model_data> queryAll(){
		Object invoke = null;
		try {
			Method queryAll = controller_class.getSuperclass().getMethod("queryAll");
			invoke = queryAll.invoke(ctrl, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (ArrayList<model_data>) invoke;
	}
}
