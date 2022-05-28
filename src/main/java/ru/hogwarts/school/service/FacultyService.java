package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for creating faculty");
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty getFacultyById(Long facultyId) {
        logger.info("Was invoked method for find faculty by ID");
        return facultyRepository.findById(facultyId).get();
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        logger.info("Was invoked method for update faculty by ID");
        facultyRepository.save(faculty);
        return faculty;
    }

    public void deleteFaculty(Long facultyId) {
        logger.info("Was invoked method for delete faculty by ID");
        facultyRepository.deleteById(facultyId);
    }

    public Collection<Faculty> getFacultiesByColor(String color) {
        logger.info("Was invoked method for find faculty by color");
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> getFacultyByNameIgnoreCaseOrColorIgnoreCase(String name, String color) {
        logger.info("Was invoked method for find faculty by name or color ignore case");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public Long getFacultyByStudentsIsContaining(Student student) {
        logger.info("Was invoked method for find faculty by student");
        return facultyRepository.findFacultyByStudentsIsContaining(student);
    }
}