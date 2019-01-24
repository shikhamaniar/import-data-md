package factory;

import javax.persistence.EntityManager;

public interface DataFactory {

	public void listPerson();

	public void listEmployment();

	public void readJson(String jSONFILEPATH ,EntityManager em);

	public void readCsv(String file, EntityManager em);
}
