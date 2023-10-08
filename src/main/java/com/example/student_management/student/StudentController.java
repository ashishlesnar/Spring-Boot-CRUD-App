package com.example.student_management.student;

import com.example.student_management.common.CommonResponse;
import com.example.student_management.exceptions.StudentErrorResponse;
import com.example.student_management.exceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private IStudent studentEntity;

    public StudentController(IStudent entity) {
        this.studentEntity = entity;
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception) {
        StudentErrorResponse response = new StudentErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public CommonResponse<Student> saveStudent(@RequestBody Student student) {
        CommonResponse<Student> commonResponse = new CommonResponse<>();
        try {
            Student response = studentEntity.saveStudent(student);
            commonResponse.setStatusCode(200);
            commonResponse.setResponseMessage("Student Save Successfully");
            commonResponse.setResponseObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commonResponse;
    }

    @PutMapping("/update/{id}")
    public CommonResponse<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int id) {
        CommonResponse<Student> commonResponse = new CommonResponse<>();
        try {
            Student response = studentEntity.updateStudent(student,id);
            commonResponse.setStatusCode(200);
            commonResponse.setResponseMessage("Student Updated Successfully");
            commonResponse.setResponseObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commonResponse;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse<Student> deleteStudent(@PathVariable("id") int id) {
        CommonResponse<Student> commonResponse = new CommonResponse<>();
        try {
            Student response = studentEntity.deleteStudent(id);
            commonResponse.setStatusCode(200);
            commonResponse.setResponseMessage("Student Deleted Successfully");
            commonResponse.setResponseObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commonResponse;
    }

    @GetMapping("/view/{id}")
    public CommonResponse<Student> getStudentById(@PathVariable("id") int id) {
        CommonResponse<Student> commonResponse = new CommonResponse<>();
        try {
            Student response = studentEntity.getStudentById(id);
            if (ObjectUtils.isEmpty(response)) {
                commonResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                commonResponse.setResponseMessage("Student Not Found With Id : " + id);
                commonResponse.setResponseObject(null);
            }else {
                commonResponse.setStatusCode(200);
                commonResponse.setResponseMessage("Student Found Successfully");
                commonResponse.setResponseObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResponse;
    }

    @GetMapping("/viewAll")
    public CommonResponse<List<Student>> getAllStudents() {
        CommonResponse<List<Student>> commonResponse = new CommonResponse<>();
        try {
            List<Student> response = studentEntity.getListOfStudents();
            if (ObjectUtils.isEmpty(response)) {
                commonResponse.setResponseMessage("Student Not Found");
                commonResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                commonResponse.setResponseObject(null);
            }else {
                commonResponse.setStatusCode(200);
                commonResponse.setResponseMessage("Students Found Successfully");
                commonResponse.setResponseObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResponse;
    }
}
