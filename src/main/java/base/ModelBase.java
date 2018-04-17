package base;


public class ModelBase {
	
	@DB_Field(name="id")
	protected String id;
	
	@DB_Field(name="extend1")
	protected String extend1;
	
	@DB_Field(name="extend2")
	protected String extend2;
	
	@DB_Field(name="extend3")
	protected String extend3;
	
	public String getId() {
		return this.id;
	}

	public String getExtend1() {
		return extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public String getExtend3() {
		return extend3;
	}
}
