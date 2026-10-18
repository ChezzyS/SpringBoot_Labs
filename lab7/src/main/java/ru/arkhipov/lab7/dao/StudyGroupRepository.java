package ru.arkhipov.lab7.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arkhipov.lab7.entity.StudyGroup;

@Repository
public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {

}
