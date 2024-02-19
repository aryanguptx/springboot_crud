package com.aryan.cruddemo;

import com.aryan.cruddemo.dao.StudentDAO;
import com.aryan.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);
			//System.out.println(findStudent(studentDAO));
			//System.out.println(getStudents(studentDAO));

			System.out.println(studentDAO.findByFirstName("paul"));
			updateEmail(studentDAO, "paul");
			System.out.println(studentDAO.findByFirstName("paul"));
		};
	}

	private Student findStudent(StudentDAO studentDAO){
		return studentDAO.findById(3);
	}

	private void updateEmail(StudentDAO studentDAO, String name){
		List<Student> students = studentDAO.findByFirstName(name);
		studentDAO.updateEmailByFirstName(students, "Test@gmail.com");
		System.out.println("Update successful");
	}

	private List<Student> getStudents(StudentDAO studentDAO){
		return studentDAO.findAll();
	}

	private void createStudent(StudentDAO studentDAO, String firstName, String secondName, String email) {
		//create student
		System.out.println("creating student object...");
		Student theStudent = new Student(firstName, secondName, email);

		//save the student
		System.out.println("Saving the student...");
		studentDAO.save(theStudent);

		//Display id of the student that was saved
		System.out.println("Saved with id: " + theStudent.getId());

	}
}
