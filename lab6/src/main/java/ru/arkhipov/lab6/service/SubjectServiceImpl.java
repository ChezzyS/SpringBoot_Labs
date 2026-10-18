package ru.arkhipov.lab6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.arkhipov.lab6.dao.SubjectDAO;
import ru.arkhipov.lab6.entity.Subject;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectDAO subjectDAO;

    @Override
    @Transactional
    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }

    @Override
    @Transactional
    public Subject saveSubject(Subject subject) {
        return subjectDAO.saveSubject(subject);
    }

    @Override
    @Transactional
    public Subject getSubject(int id) {
        return subjectDAO.getSubject(id);
    }

    @Override
    @Transactional
    public void deleteSubject(int id) {
        subjectDAO.deleteSubject(id);
    }
}
