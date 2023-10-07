package com.example.student_management;

import com.example.student_management.student.IStudent;
import com.example.student_management.student.Student;
import com.example.student_management.student.StudentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }

    private IStudent studentDBUtils;

    @Autowired
    public StudentManagementApplication (IStudent theStudent) {
        this.studentDBUtils = theStudent;
    }

    @Bean
    public CommandLineRunner commandLineRunner(String[] args) {
        return runner -> {
            Student student = new Student("Roman","Regins","romanregins1985@gmail.com");
            studentDBUtils.getStudentsByLastName("Lesnar");
        };
    }

}
