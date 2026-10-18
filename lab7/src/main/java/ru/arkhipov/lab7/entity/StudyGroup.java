package ru.arkhipov.lab7.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Учебная группа.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STUDY_GROUPS")
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Название (шифр) группы, например РИ-210001
    @Column(name = "name")
    private String name;

    // Факультет (институт)
    @Column(name = "faculty")
    private String faculty;

    // Курс
    @Column(name = "course")
    private Integer course;

    // Количество студентов в группе
    @Column(name = "students_count")
    private Integer studentsCount;
}
