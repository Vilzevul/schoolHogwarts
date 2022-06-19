package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> getFacultyId(Long idFaculty) {
        return facultyRepository.findById(idFaculty);
    }

    public Faculty updateFaculty(Faculty faculty) {

        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long idFaculty) {
        facultyRepository.deleteById(idFaculty);

    }

    public Collection<Faculty> colorFacultyFilter(String param) {
        return facultyRepository.findAll().stream().
                filter(v -> v.getColor().equals(param)).
                collect(Collectors.toList());
    }

}

