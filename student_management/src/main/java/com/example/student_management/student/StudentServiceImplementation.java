package com.example.student_management.student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentServiceImplementation implements IStudent{

    // Inject EntityManager
    private EntityManager entityManager;

    @Autowired
    public StudentServiceImplementation(EntityManager theManager) {
        this.entityManager = theManager;
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        entityManager.persist(student);
        return null;
    }

    @Override
    @Transactional
    public Student updateStudent(Student student, int id) {
        Student studenFromDB = entityManager.find(Student.class,id);
        studenFromDB.setFirstName(student.getFirstName());
        studenFromDB.setLastName(student.getLastName());
        studenFromDB.setEmail(student.getEmail());

        // Update Student
        entityManager.persist(studenFromDB);
        return studenFromDB;
    }

    @Override
    @Transactional
    public Student deleteStudent(int id) {
        Student student = entityManager.find(Student.class,id);
        entityManager.remove(student);
        return student;
    }

    @Override
    public Student getStudentById(int id) {
        Student student = entityManager.find(Student.class,id);
        System.out.println(student);
        return student;
    }

    @Override
    public List<Student> getListOfStudents() {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);
        List<Student> students = query.getResultList();

        for(Student student : students) {
            System.out.println(student);
        }

        return students;
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
