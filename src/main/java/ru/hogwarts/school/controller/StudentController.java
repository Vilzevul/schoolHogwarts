package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.Map;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity <Student> createStudent(@RequestBody Student student) {
        Student createStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createStudent);

    }

    @GetMapping("/{idStudent}")
    public ResponseEntity <Student> getStudent(@PathVariable Long idStudent) {
        Student student = studentService.getStudentId(idStudent);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity <Student> updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("/{idStudent}")
    public ResponseEntity <Student> deleteStudent(@PathVariable Long idStudent) {
        Student student = studentService.deleteStudent(idStudent);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public Collection<Student> ageStudentFilter(@RequestParam(value = "age") int param) {
        return studentService.ageStudentFilter(param);
    }

    @GetMapping(path = "/all")
    public Map<Long, Student> getAllStudents() {
        return studentService.getAllStudents();

    }
}
