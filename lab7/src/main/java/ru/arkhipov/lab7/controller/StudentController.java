package ru.arkhipov.lab7.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.arkhipov.lab7.dao.StudentRepository;
import ru.arkhipov.lab7.entity.Student;

import java.util.Optional;

@Slf4j
@Controller
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping({"/list", "/"})
    public ModelAndView getAllStudents() {
        ModelAndView mav = new ModelAndView("list-students");
        mav.addObject("students", studentRepository.findAll());
        return mav;
    }

    @GetMapping("/addStudentForm")
    public ModelAndView addStudentForm() {
        ModelAndView mav = new ModelAndView("add-student-form");
        mav.addObject("student", new Student());
        return mav;
    }

    @PostMapping("/saveStudent")
    public RedirectView saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        log.info("Сохранён студент: {}", student);
        return new RedirectView("list");
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long studentId) {
        ModelAndView mav = new ModelAndView("add-student-form");
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = optionalStudent.orElseGet(Student::new);
        mav.addObject("student", student);
        return mav;
    }

    @GetMapping("/deleteStudent")
    public RedirectView deleteStudent(@RequestParam Long studentId) {
        studentRepository.deleteById(studentId);
        log.info("Удалён студент с id={}", studentId);
        return new RedirectView("list");
    }
}
