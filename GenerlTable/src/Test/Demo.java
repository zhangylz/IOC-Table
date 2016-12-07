package Test;

import BeanFactory.IOCFactory;

public class Demo {

	private String tableData;
	private String name;
	private String age;
	private String sex;
	
	public static void main(String[] args) throws Exception {
		Demo one = IOCFactory.newInstance().getBean(Demo.class);
		System.out.println(one);
	}

	public String getTableData() {
		return tableData;
	}

	public void setTableData(String tableData) {
		this.tableData = tableData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "demo [tableData=" + tableData + ", name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}

	public Demo(String tableData, String name, String age, String sex) {
		super();
		this.tableData = tableData;
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public Demo() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		IOCFactory.newInstance().initModel(this);
	}

}
