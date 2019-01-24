package persist;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import factory.DataFactory;
import model.Employment;
import model.Person;

public class PersistData implements DataFactory {

	public PersistData(String file, String path)  {

		String CSVFILEPATH = "E:\\winter2019(2)\\assignment1_ImportData\\src\\main\\resources\\data.csv";
		String JSONFILEPATH = "E:\\winter2019(2)\\assignment1_ImportData\\src\\main\\resources\\data.json";
		if (file.equals("CSV")) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("assignment1_ImportData");
			EntityManager em = emf.createEntityManager();
			readCsv(CSVFILEPATH, em);
			emf.close();
			em.close();

		} else if (file.equals("CSVFILE")) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("assignment1_ImportData");
			EntityManager em = emf.createEntityManager();
			readCsv(path, em);
			emf.close();
			em.close();
		} else if (file.equals("JSON")) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("assignment1_ImportData");
			EntityManager em = emf.createEntityManager();
			readJson(JSONFILEPATH, em);
			emf.close();
			em.close();
		} else if (file.equals("JSONFILE")) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("assignment1_ImportData");
			EntityManager em = emf.createEntityManager();
			readCsv(path, em);
			emf.close();
			em.close();
		} else if (file.equals("CSVJSON")) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("assignment1_ImportData");
			EntityManager em = emf.createEntityManager();
			readJson(JSONFILEPATH, em);
			readCsv(CSVFILEPATH, em);
			emf.close();
			em.close();
		} else
			System.out.println("Enter CSV or JSON");
		// listPerson(em);

	}

	public void listPerson() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("assignment1_ImportData");
		EntityManager entityManager = emf.createEntityManager();
		try {

			entityManager.getTransaction().begin();
			@SuppressWarnings("unchecked")
			List<Person> persons = entityManager.createQuery("from Person").getResultList();
			for (Person per : persons)
				System.out.println(per);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			emf.close();
			entityManager.close();
		}
	}

	public void listEmployment() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("assignment1_ImportData");
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			@SuppressWarnings("unchecked")
			List<Employment> employments = entityManager.createQuery("from Employment").getResultList();
			for (Employment emp : employments)
				System.out.println(emp);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			emf.close();
			entityManager.close();
			System.out.println("End");
		}
	}

	@SuppressWarnings("unchecked")
	public void readJson(String jSONFILEPATH, EntityManager em) {

		JSONParser jsonParser = new JSONParser();
		try {
			FileReader reader;
			reader = new FileReader(jSONFILEPATH);
			JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

			for (Object obj : jsonArray) {
				em.getTransaction().begin();
				// System.out.println(obj);
				Employment employment = new Employment();
				JSONObject personobj = (JSONObject) obj;
				// for employment
				JSONArray jsonArray2 = new JSONArray();
				jsonArray2.add(personobj.get("employment"));
				System.out.println(jsonArray2);
				for (Object obj2 : jsonArray2) {

					JSONObject employmentObj = (JSONObject) obj2;
					System.out.println();
					employment.setEmployerName((String)employmentObj.get("employerName"));
					employment.setDesignation((String)employmentObj.get("designation"));
					String d =(String) employmentObj.get("employmentDate");
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date udate = sdf1.parse(d);
					java.sql.Date sdate = new java.sql.Date(udate.getTime());
					employment.setEmploymentDate(sdate);
					em.persist(employment);
				}
				Person person = new Person();
				person.setFirstName((String)personobj.get("firstName"));
				person.setLastName((String)personobj.get("lastName"));
				person.setMiddleName((String)personobj.get("middleName"));
				person.setMobile((String)personobj.get("mobile"));
				person.setGender((String)personobj.get("gender"));
				person.setEmail((String)personobj.get("email"));
				person.setCity((String)personobj.get("city"));
				person.setState((String)(String)personobj.get("state"));
				person.setCountry((String)personobj.get("country"));
				person.setEmployment(employment);
				em.persist(person);

				em.getTransaction().commit();

			}
		} catch (FileNotFoundException e) {
			System.out.println("File NOT FOUND. Pleae enter proper path and file name.(JSON or CSV");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {

		}
	}

	public void readCsv(String file, EntityManager em) {
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
		} catch (IOException e) {
			System.out.println("File NOT FOUND. Pleae enter proper path and file name.(JSON or CSV");
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

	}
}
