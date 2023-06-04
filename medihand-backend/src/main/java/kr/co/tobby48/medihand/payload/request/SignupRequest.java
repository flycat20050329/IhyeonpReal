package kr.co.tobby48.medihand.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 50)
  private String username;
  
  @Size(min = 3, max = 255)
  private String email;


  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

}
