package com.example.springproject.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    //SELECT * from Student Where email = ?
    Optional<Student> findStudentByEmail(String email);
}
