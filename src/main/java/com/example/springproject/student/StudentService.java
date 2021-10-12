package com.example.springproject.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents () {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with id " + studentId + " does not exists.");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public String updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist."));
        if (name != null && name.length() > 0 ) {
            if(Objects.equals(student.getName(), name)) {
                throw new IllegalStateException("name is same as it is already");
            }
            student.setName(name);
        }
        if (email != null && email.length() > 0){
            Optional<Student> studentOptionalEmail = studentRepository.findStudentByEmail(email);

            if (studentOptionalEmail.isPresent()) {
                throw new IllegalStateException("email is same as it is already taken");
            }
            student.setEmail(email);
        }
        return name;
    }
}