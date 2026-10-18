package ru.arkhipov.lab6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.arkhipov.lab6.dto.ApiResponse;
import ru.arkhipov.lab6.entity.Student;
import ru.arkhipov.lab6.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<ApiResponse<List<Student>>> allStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(ApiResponse.ok("Найдено студентов: " + students.size(), students));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudent(@PathVariable int id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return notFound(id);
        }
        return ResponseEntity.ok(ApiResponse.ok("Студент найден", student));
    }

    @PostMapping("/students")
    public ResponseEntity<ApiResponse<Student>> saveStudent(@RequestBody Student student) {
        student.setId(0); // новая запись, id выдаст база данных
        Student saved = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Студент добавлен", saved));
    }

    @PutMapping("/students")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@RequestBody Student student) {
        if (studentService.getStudent(student.getId()) == null) {
            return notFound(student.getId());
        }
        Student updated = studentService.saveStudent(student);
        return ResponseEntity.ok(ApiResponse.ok("Студент изменён", updated));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable int id) {
        if (studentService.getStudent(id) == null) {
            return notFound(id);
        }
        studentService.deleteStudent(id);
        return ResponseEntity.ok(ApiResponse.ok("Студент с id=" + id + " удалён", null));
    }

    private <T> ResponseEntity<ApiResponse<T>> notFound(int id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Студент с id=" + id + " не найден"));
    }
}
