package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")

public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("/find/{facultyId}")
    public ResponseEntity getFaculty(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if(faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping()
    public ResponseEntity updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty.getId(), faculty);
        if(faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/remove/{facultyId}")
    public ResponseEntity deleteFaculty(@PathVariable Long facultyId) {
        facultyService.deleteFaculty(facultyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<Collection<Faculty>> getFacultiesByColor(@PathVariable("color") String color) {
        return ResponseEntity.ok(facultyService.getFacultiesByColor(color));
    }

    @GetMapping()
    public ResponseEntity<Collection<Faculty>> getFacultyByNameIgnoreCaseOrColorIgnoreCase
            (@RequestParam (required = false) String name,
             @RequestParam (required = false) String color) {
        return ResponseEntity.ok(facultyService.getFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color));
    }

    @GetMapping("/student/{student}") //Получение факультета студента
    public ResponseEntity getFacultyByStudentsIsContaining(@PathVariable Student student) {
        return ResponseEntity.ok(facultyService.getFacultyByStudentsIsContaining(student));
    }

    @GetMapping("/getLongestFacultyName")
    public String getLongestFacultyName() {
        return facultyService.getLongestFacultyName();
    }

    @GetMapping("/returnOptimizedResult")
    public Integer getOptimizedResult() {
        return facultyService.getOptimizedResult();
    }
}