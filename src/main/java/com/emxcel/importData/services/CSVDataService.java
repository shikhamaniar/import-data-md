package com.emxcel.importData.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.emxcel.importData.model.Employment;
import com.emxcel.importData.model.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CSVDataService implements DataService {
	EntityManagerFactory emf;
	EntityManager em;
	String file;

	@Override
	public void readData() {
		file = "E:\\winter2019(2)\\importData\\src\\main\\resources\\data.csv";
		emf = Persistence.createEntityManagerFactory("assignment1_ImportData");
		em = emf.createEntityManager();
		// Create an object of filereader
		// class with CSV file as a parameter.
		FileReader filereader = null;
		try {
			filereader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// create csvReader object passing
		// file reader as a parameter
		CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
		String[] nextRecord;

		// we are going to read data line by line
		try {
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
			System.out.println("Data Added (JSON)");
		} catch (IOException e) {
			System.out.println("File NOT FOUND. Pleae enter proper path and file name.(JSON or CSV");
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		} finally {
			emf.close();
			em.close();
		}
	}
}
