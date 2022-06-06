package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    public Map<Long, Faculty> facultyMap = new HashMap<Long, Faculty>();
    private Long idFaculty = 0L;


    public Faculty createFaculty(Faculty faculty) {
        idFaculty++;
        return facultyMap.put(idFaculty, faculty);
    }

    public Faculty getFacultyId(Long idFaculty) {
        return facultyMap.get(idFaculty);
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyMap.put(faculty.getId(), faculty);
    }

    public Faculty deleteFaculty(Long idFaculty) {
        return facultyMap.remove(idFaculty);

    }

    public Map<Long, Faculty> colorFacultyFilter(String param) {
        return facultyMap.entrySet().stream().
                filter(v -> v.getValue().getColor().equals(param)).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}

