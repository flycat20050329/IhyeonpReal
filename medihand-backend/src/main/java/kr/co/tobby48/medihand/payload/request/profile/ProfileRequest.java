package kr.co.tobby48.medihand.payload.request.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequest {
	private Long id;
	private String name;
    private String email;
    private String optime;
    private String phone;
    private String fax;
    private String map;
}
