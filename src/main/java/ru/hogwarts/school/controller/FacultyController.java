package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/faculty")
@RestController
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createFaculty);

    }

    @GetMapping("/{idFaculty}")
    public ResponseEntity<Optional<Faculty>> getFaculty(@PathVariable Long idFaculty) {
        Optional<Faculty> faculty = facultyService.getFacultyId(idFaculty);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping()
    public ResponseEntity<Faculty> updatedFaculty(@RequestBody Faculty faculty) {
        Faculty updateFaculty = facultyService.updateFaculty(faculty);
        return ResponseEntity.ok(updateFaculty);
    }

    @DeleteMapping("/{idFaculty}")
    public ResponseEntity deleteFaculty(@PathVariable Long idFaculty) {
        facultyService.deleteFaculty(idFaculty);


        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Collection<Faculty> colorFacultyFilter(@RequestParam(value = "color") String param) {
        return facultyService.colorFacultyFilter(param);
    }
}