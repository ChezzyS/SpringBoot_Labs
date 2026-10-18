package ru.arkhipov.lab6.dao;

import ru.arkhipov.lab6.entity.Subject;

import java.util.List;

public interface SubjectDAO {

    List<Subject> getAllSubjects();

    Subject saveSubject(Subject subject);

    Subject getSubject(int id);

    void deleteSubject(int id);
}
