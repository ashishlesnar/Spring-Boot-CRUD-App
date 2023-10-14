package com.example.student_management.student;

import com.example.student_management.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImplementation implements IStudent {

    // Inject EntityManager
    private StudentRepository studentRepository;
    private EntityManager entityManager;
    @Autowired
    public StudentServiceImplementation(StudentRepository repository, EntityManager manager) {
        this.studentRepository = repository;
        this.entityManager = manager;
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    @Override
    @Transactional
    public List<Student> saveStudent(List<Student> studentList) {
        for(Student student : studentList) {
            studentRepository.save(student);
            System.out.println(student);
        }
        return studentList;
    }

    @Override
    @Transactional
    public Student updateStudent(Student student, int id) {
        Optional<Student> studenFromDB = studentRepository.findById(id);
        Student updatedStudent = null;
        if (studenFromDB.isPresent()) {
            updatedStudent = studenFromDB.get();
            updatedStudent.setFirstName(student.getFirstName());
            updatedStudent.setLastName(student.getLastName());
            updatedStudent.setEmail(student.getEmail());

            // Update Student
            studentRepository.save(updatedStudent);
        }

        return updatedStudent;
    }

    @Override
    @Transactional
    public Student deleteStudent(int id) {
        Optional<Student> student = studentRepository.findById(id);
        Student studentResponse = null;
        if (student.isPresent()) {
            studentResponse = student.get();
            studentRepository.deleteById(id);
        }
        return studentResponse;
    }

    @Override
    public Student getStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        System.out.println(student);
        return null;
    }

    @Override
    public List<Student> getListOfStudents() {
        List<Student> students = studentRepository.findAll();
        if (!students.isEmpty()) {
            return students;
        }
        return null;
    }

    @Override
    public List<Student> getStudentsByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("from Student where lastName = :theLastName", Student.class);
        query.setParameter("theLastName",lastName);
        List<Student> students = query.getResultList();

        for (Student student : students) {
            System.out.println(student);
        }

        return students;
    }
}
