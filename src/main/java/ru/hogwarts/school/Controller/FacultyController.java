package ru.hogwarts.school.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Map;

@RequestMapping("faculty")
@RestController
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity createFaculty(@RequestBody Faculty faculty) {
        Faculty createFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createFaculty);

    }

    @GetMapping("{idFaculty}")
    public ResponseEntity getFaculty(@PathVariable Long idFaculty) {
        Faculty faculty = facultyService.getFacultyId(idFaculty);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping()
    public ResponseEntity updateFaculty(@RequestBody Faculty faculty) {
        Faculty updateStudent = facultyService.updateFaculty(faculty);
        return ResponseEntity.ok(updateFaculty(faculty));
    }

    @DeleteMapping("{idFaculty}")
    public ResponseEntity deleteFaculty(@PathVariable Long idFaculty) {
        Faculty faculty = facultyService.getFacultyId(idFaculty);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public Map<Long, Faculty> colorFacultyFilter(@RequestParam(value = "color") String param) {
        return facultyService.colorFacultyFilter(param);
    }
}