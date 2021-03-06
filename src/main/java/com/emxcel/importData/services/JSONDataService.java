package com.emxcel.importData.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.emxcel.importData.model.Employment;
import com.emxcel.importData.model.Person;

public class JSONDataService implements DataService {
	PersistenceManager pm;
	EntityManager em;
	String file = "E:\\winter2019(2)\\importData\\src\\main\\resources\\data.json";

	@SuppressWarnings("unchecked")
	@Override
	public void readData() {
		pm = new PersistenceManager();
		em = pm.getEntityManager();
		JSONParser jsonParser;
		FileReader reader;
		JSONArray jsonArray;
		try {
			jsonParser = new JSONParser();
			reader = new FileReader(file);
			jsonArray = (JSONArray) jsonParser.parse(reader);

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
					employment.setEmployerName((String) employmentObj.get("employerName"));
					employment.setDesignation((String) employmentObj.get("designation"));
					String d = (String) employmentObj.get("employmentDate");
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date udate = sdf1.parse(d);
					java.sql.Date sdate = new java.sql.Date(udate.getTime());
					employment.setEmploymentDate(sdate);
					em.persist(employment);
				}
				Person person = new Person();
				person.setFirstName((String) personobj.get("firstName"));
				person.setLastName((String) personobj.get("lastName"));
				person.setMiddleName((String) personobj.get("middleName"));
				person.setMobile((String) personobj.get("mobile"));
				person.setGender((String) personobj.get("gender"));
				person.setEmail((String) personobj.get("email"));
				person.setCity((String) personobj.get("city"));
				person.setState((String) personobj.get("state"));
				person.setCountry((String) personobj.get("country"));
				person.setEmployment(employment);
				em.persist(person);

				em.getTransaction().commit();

			}
			System.out.println("Data Added (JSON)");
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
			pm.close();
			em.close();
		}

	}
}
