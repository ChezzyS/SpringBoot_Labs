package ru.arkhipov.lab6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.arkhipov.lab6.entity.Subject;

import java.util.List;

@Slf4j
@Repository
public class SubjectDAOImpl implements SubjectDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> allSubjects = entityManager.createQuery("from Subject", Subject.class).getResultList();
        log.info("getAllSubjects: {}", allSubjects);
        return allSubjects;
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return entityManager.merge(subject);
    }

    @Override
    public Subject getSubject(int id) {
        return entityManager.find(Subject.class, id);
    }

    @Override
    public void deleteSubject(int id) {
        entityManager.createQuery("delete from Subject where id = :subjectId")
                .setParameter("subjectId", id)
                .executeUpdate();
    }
}
