package kr.co.tobby48.medihand.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.co.tobby48.medihand.payload.request.management.AddManagementRequest;
import kr.co.tobby48.medihand.payload.request.management.ManagementRequest;
import kr.co.tobby48.medihand.payload.request.management.UpdateManagementRequest;

@Entity
@Table(name = "management")
public class Management {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//  @Email
	@NotBlank
	@NotNull
	@Size(max = 80)
	private String name;

	@NotBlank
	@NotNull
	@Size(max = 20)
	private String cellPhone;

	@NotBlank
	@NotNull
	@Size(max = 80)
	private String birthDate;

	@Size(max = 200)
	private String symptom;
	///////////////////////////////////////////////
	

	@Temporal(TemporalType.TIMESTAMP)
	@NotBlank
	@NotNull
	private Date startdt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotBlank
	@NotNull
	private Date enddt;
	///////////////////////////////////////////////
	

	@NotBlank
	@NotNull
	private Integer status;
	
	///////////////////////////////////////////////
	
	
	@ManyToOne
	private Patient patient;
	
	@NotBlank
	@NotNull
	@ManyToOne
	private User user;

	
	
	public Management() {
	}
	public Management(ManagementRequest form, User user) {
		setName(form.getName());
		setCellPhone(form.getCellPhone());
		setBirthDate(form.getBirthDate());
		setSymptom(form.getSymptom());
		setStartdt(new Date(form.getStartdt()));
		setEnddt(new Date(form.getEnddt()));
		setStatus(form.getStatus());
		setUser(user);
	}
	
	public Management(AddManagementRequest form, User user, Patient patient) {
		this(form, user);
		setPatient(patient);
	}
	
	
	public Management(User user, UpdateManagementRequest form) {
		this(form, user);
		setId(form.getId());
	}
//	public Management(User user, UpdateAppointmentForm form, Patient patient) {
//		this(user, form);
//		setPatient_id(patient);
//	}
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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

	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
	public Date getStartdt() {
		return startdt;
	}

	public void setStartdt(Date startdt) {
		this.startdt = startdt;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
	public Date getEnddt() {
		return enddt;
	}

	public void setEnddt(Date enddt) {
		this.enddt = enddt;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
