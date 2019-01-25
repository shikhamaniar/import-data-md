package com.emxcel.importData;

public class MainClass {

	public static void main(String[] args)  {
//		write CSV or JSON or CSVJSON or FILE
		String option = "JSON";
		@SuppressWarnings("unused")
		DataServicesFactory obj = new DataServicesFactory(option);
		
		
//		See the table content
//		obj.listPerson();
//		obj.listEmployment();
	}

}
