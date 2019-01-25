package com.emxcel.importData.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_employment")
public class Employment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "employer_name")
	private String employerName;
	private String designation;
	@Column(name = "employment_date", columnDefinition = "DATE")
	private Date employmentDate;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public Date getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(java.sql.Date sdate) {
		this.employmentDate = sdate;
	}

}