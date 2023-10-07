package com.example.student_management;

import com.example.student_management.student.IStudent;
import com.example.student_management.student.Student;
import com.example.student_management.student.StudentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

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
            Student rock = new Student("Rock","Johnson","rockjohnson1980@gmail.com");
            Student john = new Student("John","Cena","johncena1975@gmail.com");
            Student seth = new Student("Seth","Rollins","sethrollins1988@gmail.com");
            Student triple = new Student("Triple","H","tripleh1965@gmail.com");
            List<Student> students = new ArrayList<>();
            students.add(rock);
            students.add(john);
            students.add(seth);
            students.add(triple);

            studentDBUtils.saveStudent(students);
        };
    }
}
