package com.example.student_management.student;

import com.example.student_management.common.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private IStudent studentEntity;

    public StudentController(IStudent entity) {
        this.studentEntity = entity;
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
            Student studentResponse = studentEntity.updateStudent(student,id);

            if (!ObjectUtils.isEmpty(studentResponse)) {
                commonResponse.setStatusCode(200);
                commonResponse.setResponseMessage("Student Updated Successfully");
                commonResponse.setResponseObject(studentResponse);
            } else {
                commonResponse.setStatusCode(404);
                commonResponse.setResponseMessage("Student Not Found!");
                commonResponse.setResponseObject(studentResponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return commonResponse;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse<Student> deleteStudent(@PathVariable("id") int id) {
        CommonResponse<Student> commonResponse = new CommonResponse<>();
        try {
            Student student = studentEntity.deleteStudent(id);
            if (!ObjectUtils.isEmpty(student)) {
                commonResponse.setStatusCode(200);
                commonResponse.setResponseMessage("Student Deleted Successfully");
                commonResponse.setResponseObject(student);
            }
            else {
                commonResponse.setStatusCode(404);
                commonResponse.setResponseMessage("Student Not Found!");
                commonResponse.setResponseObject(student);
            }

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
                commonResponse.setResponseObject(response);
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
