package com.example.student_management.student;

import java.util.List;

public interface IStudent {
    Student saveStudent(Student student);
    List<Student> saveStudent(List<Student> studentList);
    Student updateStudent(Student student, int id);
    Student deleteStudent(int id);
    Student getStudentById(int id);
    List<Student> getListOfStudents();
    List<Student> getStudentsByLastName(String lastName);
}
