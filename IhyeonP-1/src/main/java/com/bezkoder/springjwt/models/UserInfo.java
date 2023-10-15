package com.bezkoder.springjwt.models;

public class UserInfo {
	String email = "";
	String schoolName = "";
	String schoolCode = "";
	String locationCode = "";
	String grade = "";
	String ban = "";
	
	UserInfo(String email, String schoolCode, String schoolName, String locationCode, String grade, String ban){
		this.email = email;
		this.schoolName = schoolName;
		this.schoolCode = schoolCode;
		this.locationCode = locationCode;
		this.grade = grade;
		this.ban = ban;
	}
}
