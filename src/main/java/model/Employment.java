package model;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name = "employer_name")
	private String employerName;
	private String designation;
	@Column(name = "employment_date",columnDefinition="DATE")
	private java.sql.Date employmentDate;

	public Employment() {
	}

	public Employment(long id, String employerName, String designation, java.sql.Date employmentDate) {
		this.employerName = employerName;
		this.designation = designation;
		this.employmentDate = employmentDate;
	}

	@Override
	public String toString() {
		return "Employment [id=" + id + ", employerName=" + employerName + ", designation=" + designation
				+ ", employmentDate=" + employmentDate + "]";
	}

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

	public java.sql.Date getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(java.sql.Date sdate) {
		this.employmentDate = sdate;
	}

}