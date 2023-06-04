package kr.co.tobby48.medihand.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.co.tobby48.medihand.payload.request.patient.AddPatientRequest;
import kr.co.tobby48.medihand.payload.request.patient.UpdatePatientRequest;

@Entity
@Table(name = "patient", 
uniqueConstraints = { 
		@UniqueConstraint(columnNames = {"name", "age", "birthDate"})
})
public class Patient {
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
	private Integer age;

	@NotBlank
	@NotNull
	private Integer sex;

	@NotBlank
	@NotNull
	@Size(max = 200)
	private String address;
	///////////////////////////////////////////////
	

	@NotBlank
	@NotNull
	@Size(max = 50)
	private String city;

	@NotBlank
	@NotNull
	@Size(max = 20)
	private String state;

	@NotBlank
	@NotNull
	@Size(max = 20)
	private String zipCode;
	///////////////////////////////////////////////
	
	
	@Size(max = 20)
	private String homePhone;
	
	@NotBlank
	@NotNull
	@Size(max = 20)
	private String cellPhone;
	
	@Size(max = 100)
	private String email;
	///////////////////////////////////////////////
	
	
	@NotBlank
	@NotNull
	@Size(max = 80)
	private String birthDate;
	
	@NotBlank
	@NotNull
	@Size(max = 100)
	private String occupation;
	///////////////////////////////////////////////
	

//	@NotNull
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
	///////////////////////////////////////////////
	

	@Size(max = 50)
	private String ssn;
	
	@Size(max = 200)
	private String referring;
	///////////////////////////////////////////////
	
	@NotBlank
	@NotNull
	@Size(max = 80)
	private String eName;
	
	@NotBlank
	@NotNull
	@Size(max = 50)
	private String eRelationShip;
	
	@NotBlank
	@NotNull
	@Size(max = 20)
	private String ePhone;
	///////////////////////////////////////////////
	
	
	@Size(max = 100)
	private String insuFirst;
	
	@Size(max = 100)
	private String insuTwo;
	
	@Size(max = 100)
	private String insuThree;
	
	@Size(max = 100)
	private String insuFour;
	
	@Size(max = 100)
	private String insuFive;
	
	@Size(max = 100)
	private String insuSix;
	///////////////////////////////////////////////
	
	@OneToMany(mappedBy = "patient")    
	private Set<Management> managements = new HashSet<>();
	
	@NotBlank
	@NotNull
	@ManyToOne
	private User user;

	
	
	public Patient() {}
	
	public Patient(AddPatientRequest patientForm) {
		setName(patientForm.getName());
		setAge(patientForm.getAge());
        setSex(patientForm.getSex());
        
        setAddress(patientForm.getAddress());
        setCity(patientForm.getCity());
        setState(patientForm.getState());
        setZipCode(patientForm.getZipCode());
        
        setHomePhone(patientForm.getHomePhone());
        setCellPhone(patientForm.getCellPhone());
        setEmail(patientForm.getEmail());
        
        setBirthDate(patientForm.getBirthDate());
        setOccupation(patientForm.getOccupation());
        
        setSsn(patientForm.getSsn());
        setReferring(patientForm.getReferring());
        
        seteName(patientForm.geteName());
        seteRelationShip(patientForm.geteRelationShip());
        setePhone(patientForm.getePhone());
        
        setInsuFirst(patientForm.getInsuFirst());
        setInsuTwo(patientForm.getInsuTwo());
        setInsuThree(patientForm.getInsuThree());
        setInsuFour(patientForm.getInsuFour());
        setInsuFive(patientForm.getInsuFive());
        setInsuSix(patientForm.getInsuSix());
        
	}

	public Patient(AddPatientRequest patientForm, User user) {
		this(patientForm);
        setUser(user);
    }
	
	public Patient(UpdatePatientRequest patientForm, User user) {
		this(patientForm);
		setId(patientForm.getId());
        setUser(user);
    }
	
	
	
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getReferring() {
		return referring;
	}

	public void setReferring(String referring) {
		this.referring = referring;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteRelationShip() {
		return eRelationShip;
	}

	public void seteRelationShip(String eRelationShip) {
		this.eRelationShip = eRelationShip;
	}

	public String getePhone() {
		return ePhone;
	}

	public void setePhone(String ePhone) {
		this.ePhone = ePhone;
	}

	public String getInsuFirst() {
		return insuFirst;
	}

	public void setInsuFirst(String insuFirst) {
		this.insuFirst = insuFirst;
	}

	public String getInsuTwo() {
		return insuTwo;
	}

	public void setInsuTwo(String insuTwo) {
		this.insuTwo = insuTwo;
	}

	public String getInsuThree() {
		return insuThree;
	}

	public void setInsuThree(String insuThree) {
		this.insuThree = insuThree;
	}

	public String getInsuFour() {
		return insuFour;
	}

	public void setInsuFour(String insuFour) {
		this.insuFour = insuFour;
	}

	public String getInsuFive() {
		return insuFive;
	}

	public void setInsuFive(String insuFive) {
		this.insuFive = insuFive;
	}

	public String getInsuSix() {
		return insuSix;
	}

	public void setInsuSix(String insuSix) {
		this.insuSix = insuSix;
	}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
