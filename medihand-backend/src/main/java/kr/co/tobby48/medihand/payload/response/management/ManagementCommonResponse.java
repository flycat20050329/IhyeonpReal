package kr.co.tobby48.medihand.payload.response.management;

import java.util.Date;
import java.util.List;

import kr.co.tobby48.medihand.models.Management;
import kr.co.tobby48.medihand.models.Patient;

public class ManagementCommonResponse {
	private String title;
	private String cellPhone;
	private String birthDate;
	private String description;
	private Date startdt;
	private Date enddt;
	private Patient patient;
	
	private String status;
	private List<Object> subtasks;

	public ManagementCommonResponse(Management management, String status, List<Object> subtasks) {
		this.title = management.getName();
		this.cellPhone = management.getCellPhone();
		this.birthDate = management.getBirthDate();
		this.description = management.getSymptom();
		
		this.startdt = management.getStartdt();
		this.enddt = management.getEnddt();
		this.patient = management.getPatient();
		
		this.status = status;
		this.subtasks = subtasks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartdt() {
		return startdt;
	}

	public void setStartdt(Date startdt) {
		this.startdt = startdt;
	}

	public Date getEnddt() {
		return enddt;
	}

	public void setEnddt(Date enddt) {
		this.enddt = enddt;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Object> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(List<Object> subtasks) {
		this.subtasks = subtasks;
	}

}
