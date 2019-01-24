package Main;

import persist.PersistData;
import factory.DataFactory;

public class MainClass {

	public static void main(String[] args)  {
		// write CSV or JSON or CSVJSON or FILE
		String option = "CSVJSON";
		// if FILE enter the path
		String filepath = "";
		DataFactory obj = new PersistData(option, filepath);
		System.out.println("Both files added");// when executing jar file
		System.out.println("Display Table");
		// See the table content
		obj.listPerson();
		obj.listEmployment();
	}

}
