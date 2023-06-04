package kr.co.tobby48.medihand.payload.request.patient; 

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kr.co.tobby48.medihand.models.User;

/**
 * <pre>
 * kr.co.tobby48.medihand.payload.form 
 * AddPatientForm.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2022. 10. 22.
 * @author : tobby48
 * @version : v1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPatientRequest implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "{validation.name.required}")
	private String name;
	@NotNull(message = "{validation.age.required}")
	private Integer age;
	@NotNull(message = "{validation.sex.required}")
	private Integer sex;
	
	@NotNull(message = "{validation.address.required}")
	private String address;
	@NotNull(message = "{validation.city.required}")
	private String city;
	@NotNull(message = "{validation.state.required}")
	private String state;
	@NotNull(message = "{validation.zipCode.required}")
	private String zipCode;
	
	private String homePhone;
	@NotNull(message = "{validation.cellPhone.required}")
	private String cellPhone;
	private String email;
	
	@NotNull(message = "{validation.birthDate.required}")
	private String birthDate;
	@NotNull(message = "{validation.occupation.required}")
	private String occupation;
	
	private String ssn;
	private String referring;
	
	@NotNull(message = "{validation.eName.required}")
	private String eName;
	@NotNull(message = "{validation.eRelationShip.required}")
	private String eRelationShip;
	@NotNull(message = "{validation.ePhone.required}")
	private String ePhone;
	
	private String insuFirst;
	private String insuTwo;
	private String insuThree;
	private String insuFour;
	private String insuFive;
	private String insuSix;
	
	
	private transient User user_id;
	
	
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

	public User getUser_id() {
		return user_id;
	}
	
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
}