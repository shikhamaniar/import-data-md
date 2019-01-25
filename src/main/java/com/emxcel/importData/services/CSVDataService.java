package com.emxcel.importData.services;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import com.emxcel.importData.model.Employment;
import com.emxcel.importData.model.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CSVDataService implements DataService {
	PersistenceManager pm;
	EntityManager em;
	String file = "E:\\winter2019(2)\\importData\\src\\main\\resources\\data.csv";

	@Override
	public void readData() {
		try {
			pm = new PersistenceManager();
			em = pm.getEntityManager();
			FileReader filereader = new FileReader(file);
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			String[] nextRecord;

			while ((nextRecord = csvReader.readNext()) != null) {
				em.getTransaction().begin();
				Person person = new Person();
				person.setFirstName(nextRecord[0]);
				person.setLastName(nextRecord[1]);
				person.setMiddleName(nextRecord[2]);
				person.setMobile(nextRecord[4]);
				person.setEmail(nextRecord[5]);
				person.setGender(nextRecord[3]);
				person.setCity(nextRecord[6]);
				person.setState(nextRecord[7]);
				person.setCountry(nextRecord[8]);

				Employment employment = new Employment();
				String d = nextRecord[11];
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date udate = sdf1.parse(d);
				java.sql.Date sdate = new java.sql.Date(udate.getTime());
				employment.setEmployerName(nextRecord[9]);
				employment.setDesignation(nextRecord[10]);
				employment.setEmploymentDate(sdate);
				person.setEmployment(employment);
				em.persist(person);
				em.persist(employment);
				em.getTransaction().commit();
			}
			System.out.println("Data Added (CSV)");
		} catch (IOException e) {
			System.out.println("File NOT FOUND. Pleae enter proper path and file name.(JSON or CSV");
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		} finally {
			pm.close();
			em.close();
		}
	}
}
