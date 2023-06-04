package kr.co.tobby48.medihand.payload.request.management; 

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kr.co.tobby48.medihand.models.User;

/**
 * <pre>
 * kr.co.tobby48.medihand.payload.form 
 * ManagementForm.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2022. 10. 22.
 * @author : tobby48
 * @version : v1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManagementRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String name;
	protected String cellPhone;
	protected String birthDate;
	protected String symptom;
	protected Long startdt;
	protected Long enddt;
	protected Integer status;
	
	private transient User user_id;
	

	public ManagementRequest() {
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public String getSymptom() {
		return symptom;
	}


	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}


	public Long getStartdt() {
		return startdt;
	}


	public void setStartdt(Long startdt) {
		this.startdt = startdt;
	}


	public Long getEnddt() {
		return enddt;
	}


	public void setEnddt(Long enddt) {
		this.enddt = enddt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

}
