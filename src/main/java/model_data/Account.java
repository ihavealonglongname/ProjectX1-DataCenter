package model_data;

import base.DB_Field;
import base.DB_Table;
import base.ModelBase;

@DB_Table(name="account")
public class Account extends ModelBase {
	
	@DB_Field(name="account")
	private String account;
	
	@DB_Field(name="pwd")
	private String password;
	
	@DB_Field(name="reg_datetime")
	private String reg_datetime;
	
	
	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public String getReg_datetime() {
		return reg_datetime;
	}
}