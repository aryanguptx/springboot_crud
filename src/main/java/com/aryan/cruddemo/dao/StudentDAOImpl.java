package com.aryan.cruddemo.dao;

import com.aryan.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
    @Override
    public Student findById(Integer id){
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student where firstName =:theFirstName", Student.class
        );
        theQuery.setParameter("theFirstName", firstName);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void updateEmailByFirstName(List<Student> students, String email) {
//        List<Student> studentsToBeUpdated = findByFirstName()
        for(Student s : students){
            s.setEmail(email);
            entityManager.merge(s);
        }
    }

//    @Override
//    public void updateFirstName(String newName, int id) {
//        Student theStudent = entityManager.find(Student.class, id);
//    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        return theQuery.getResultList();
    }

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
