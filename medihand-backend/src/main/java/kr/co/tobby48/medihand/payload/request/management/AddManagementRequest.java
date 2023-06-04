package kr.co.tobby48.medihand.payload.request.management; 

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <pre>
 * kr.co.tobby48.medihand.payload.form 
 * AddManagementForm.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2022. 10. 22.
 * @author : tobby48
 * @version : v1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddManagementRequest extends ManagementRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer patient_id;
	

	public AddManagementRequest() {
	}


	public Integer getPatient_id() {
		return patient_id;
	}


	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}
}