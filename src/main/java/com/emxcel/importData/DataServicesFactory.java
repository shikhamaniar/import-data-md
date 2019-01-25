package com.emxcel.importData;

import com.emxcel.importData.services.CSVDataService;
import com.emxcel.importData.services.JSONDataService;

public class DataServicesFactory {

	public DataServicesFactory(String option) {

		if (option.equals("CSV")) {
			CSVDataService csv = new CSVDataService();
			csv.readData();
		} else if (option.equals("JSON")) {
			JSONDataService json = new JSONDataService();
			json.readData();
		} else
			System.out.println("Enter CSV or JSON ");
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
