package net.lavaguides.student_management_system_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "net.lavaguides.student_management_system_2.repository")
public class StudentManagementSystem2Application {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystem2Application.class, args);
	}

}
