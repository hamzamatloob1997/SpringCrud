package com.example.springproject.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/user")
    public List<Student> getStudents () {
        return studentService.getStudents();
    }
    @PostMapping(value = "/user")
    public String registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
        return "Added Successfully";
    }
    @DeleteMapping(value = "/user/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
        return "Deleted Successfully";
    }
    @PutMapping(value = "/user/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
        return "Updated Successfully";
    }
}