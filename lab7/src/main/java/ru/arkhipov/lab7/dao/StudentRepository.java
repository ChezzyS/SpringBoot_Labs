package ru.arkhipov.lab7.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arkhipov.lab7.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
