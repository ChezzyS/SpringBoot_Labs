package ru.arkhipov.lab6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.arkhipov.lab6.entity.Student;

import java.util.List;

@Slf4j
@Repository
public class StudentDAOImpl implements StudentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudents() {
        List<Student> allStudents = entityManager.createQuery("from Student", Student.class).getResultList();
        log.info("getAllStudents: {}", allStudents);
        return allStudents;
    }

    @Override
    public Student saveStudent(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public Student getStudent(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void deleteStudent(int id) {
        entityManager.createQuery("delete from Student where id = :studentId")
                .setParameter("studentId", id)
                .executeUpdate();
    }
}
