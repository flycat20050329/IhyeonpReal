package com.ykm.ihyeon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.ykm.ihyeon.repository"} )
public class SpringBootSecurityJwtApplication {

	public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}

}



//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
// 
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@ComponentScan(basePackages={"com.ykm.ihyeon.repository"} )
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class SpringBootSecurityJwtApplication {
//
//	public static void main(String[] args) {
//    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
//	}
//}

