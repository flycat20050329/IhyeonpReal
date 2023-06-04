package kr.co.tobby48.medihand.payload.request.management; 

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kr.co.tobby48.medihand.models.Patient;

/**
 * <pre>
 * kr.co.tobby48.medihand.payload.form 
 * UpdateManagementForm.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2022. 10. 22.
 * @author : tobby48
 * @version : v1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateManagementRequest extends ManagementRequest {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "{validation.id.required}")
	private Long id;
	
	private Patient patient_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(Patient patient_id) {
		this.patient_id = patient_id;
	}
	
}