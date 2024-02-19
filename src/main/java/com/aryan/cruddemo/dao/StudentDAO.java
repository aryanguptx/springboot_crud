package com.aryan.cruddemo.dao;

import com.aryan.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);
    Student findById(Integer id);

    List<Student> findByFirstName(String firstName);

    void updateEmailByFirstName(List<Student> students, String email);
    List<Student> findAll();
}
