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
import ru.arkhipov.lab6.entity.Subject;
import ru.arkhipov.lab6.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/subjects")
    public ResponseEntity<ApiResponse<List<Subject>>> allSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(ApiResponse.ok("Найдено дисциплин: " + subjects.size(), subjects));
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<ApiResponse<Subject>> getSubject(@PathVariable int id) {
        Subject subject = subjectService.getSubject(id);
        if (subject == null) {
            return notFound(id);
        }
        return ResponseEntity.ok(ApiResponse.ok("Дисциплина найдена", subject));
    }

    @PostMapping("/subjects")
    public ResponseEntity<ApiResponse<Subject>> saveSubject(@RequestBody Subject subject) {
        subject.setId(0); // новая запись, id выдаст база данных
        Subject saved = subjectService.saveSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Дисциплина добавлена", saved));
    }

    @PutMapping("/subjects")
    public ResponseEntity<ApiResponse<Subject>> updateSubject(@RequestBody Subject subject) {
        if (subjectService.getSubject(subject.getId()) == null) {
            return notFound(subject.getId());
        }
        Subject updated = subjectService.saveSubject(subject);
        return ResponseEntity.ok(ApiResponse.ok("Дисциплина изменена", updated));
    }

    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSubject(@PathVariable int id) {
        if (subjectService.getSubject(id) == null) {
            return notFound(id);
        }
        subjectService.deleteSubject(id);
        return ResponseEntity.ok(ApiResponse.ok("Дисциплина с id=" + id + " удалена", null));
    }

    private <T> ResponseEntity<ApiResponse<T>> notFound(int id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Дисциплина с id=" + id + " не найдена"));
    }
}
