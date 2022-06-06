package ru.hogwarts.school.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("student")
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) {
        Student createStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createStudent);

    }

    @GetMapping("{idStudent}")
    public ResponseEntity getStudent(@PathVariable Long idStudent) {
        Student student = studentService.getStudentId(idStudent);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping()
    public ResponseEntity updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student.getId(), student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("{idStudent}")
    public ResponseEntity deleteStudent(@PathVariable Long idStudent) {
        Student student = studentService.getStudentId(idStudent);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public Map<Long, Student> ageStudentFilter(@RequestParam(value = "age") int param) {
        return studentService.ageStudentFilter(param);
    }

    @GetMapping(path = "/all")
    public Map<Long, Student> getAllStudents() {
        return studentService.getAllStudents();

    }
}
