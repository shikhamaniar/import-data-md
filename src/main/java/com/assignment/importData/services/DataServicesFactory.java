package com.assignment.importData.services;

public class DataServicesFactory {

	public DataServicesFactory(String option) {
		CSVDataService csv;
		JSONDataService json;
		if (option.equals("CSV")) {
			csv = new CSVDataService();
			csv.readData();

		} else if (option.equals("JSON")) {
			json = new JSONDataService();
			json.readData();

		} else if (option.equals("CSVJSON")) {
			json = new JSONDataService();
			json.readData();
			csv = new CSVDataService();
			csv.readData();

		} else
			System.out.println("Enter CSV or JSON or CSVJSON");
		// listPerson(em);

	}
//
//	public void listPerson() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("assignment1_ImportData");
//		EntityManager entityManager = emf.createEntityManager();
//		try {
//
//			entityManager.getTransaction().begin();
//			@SuppressWarnings("unchecked")
//			List<Person> persons = entityManager.createQuery("from Person").getResultList();
//			for (Person per : persons)
//				System.out.println(per);
//			entityManager.getTransaction().commit();
//
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			emf.close();
//			entityManager.close();
//		}
//	}
//
//	public void listEmployment() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("assignment1_ImportData");
//		EntityManager entityManager = emf.createEntityManager();
//		try {
//			entityManager.getTransaction().begin();
//			@SuppressWarnings("unchecked")
//			List<Employment> employments = entityManager.createQuery("from Employment").getResultList();
//			for (Employment emp : employments)
//				System.out.println(emp);
//			entityManager.getTransaction().commit();
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			emf.close();
//			entityManager.close();
//			System.out.println("End");
//		}
//	}

}
