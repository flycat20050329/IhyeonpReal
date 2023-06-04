package kr.co.tobby48.medihand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
//@ComponentScan(basePackages={"com.ykm.ihyeon.repository"})
public class MedihandApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MedihandApplication.class);
	    app.addListeners(new ApplicationPidFileWriter()); // ApplicationPidFileWriter 설정
	    app.run(args);
	}

}
