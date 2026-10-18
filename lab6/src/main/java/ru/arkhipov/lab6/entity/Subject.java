package ru.arkhipov.lab6.entity;

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
 * Учебная дисциплина (вторая таблица БД).
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUBJECTS")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Название дисциплины
    @Column(name = "name")
    private String name;

    // Преподаватель
    @Column(name = "teacher")
    private String teacher;

    // Количество часов
    @Column(name = "hours")
    private Integer hours;

    // Семестр
    @Column(name = "semester")
    private Integer semester;
}
