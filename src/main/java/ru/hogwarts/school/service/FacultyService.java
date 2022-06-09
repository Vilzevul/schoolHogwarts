package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    public final Map<Long, Faculty> facultyMap = new HashMap<Long, Faculty>();
    private Long idFaculty = 0L;


    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++idFaculty);
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

    public Collection<Faculty> colorFacultyFilter(String param) {
        return facultyMap.values().stream().
                filter(v -> v.getColor().equals(param)).
                collect(Collectors.toList());
    }

}

