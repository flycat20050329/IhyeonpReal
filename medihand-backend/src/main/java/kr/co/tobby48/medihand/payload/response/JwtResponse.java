package kr.co.tobby48.medihand.payload.response;

import java.util.List;

import kr.co.tobby48.medihand.models.User;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private List<String> roles;
  private User user;

  public JwtResponse(String accessToken, Long id, String username, List<String> roles, User user) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.roles = roles;
    this.user = user;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }

  public User getUser() {
	  return user;
  }

  public void setUser(User user) {
	  this.user = user;
  }

}
