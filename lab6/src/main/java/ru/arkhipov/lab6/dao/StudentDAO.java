package ru.arkhipov.lab6.dao;

import ru.arkhipov.lab6.entity.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudent(int id);

    void deleteStudent(int id);
}
