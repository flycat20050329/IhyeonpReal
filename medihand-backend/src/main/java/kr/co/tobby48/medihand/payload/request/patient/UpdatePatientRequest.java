package kr.co.tobby48.medihand.payload.request.patient; 

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <pre>
 * kr.co.tobby48.medihand.payload.form 
 * UpdatePatientForm.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2022. 10. 22.
 * @author : tobby48
 * @version : v1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePatientRequest extends AddPatientRequest implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "{validation.id.required}")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}