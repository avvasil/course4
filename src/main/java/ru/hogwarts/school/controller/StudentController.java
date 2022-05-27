package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")

public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{studentId}")
    public ResponseEntity getStudent(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping()
    public ResponseEntity updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student.getId(), student);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<Collection<Student>> getStudentsByAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.findByAge(age));
    }

    @GetMapping()
    public ResponseEntity<Collection<Student>> getStudentsByAgeBetweenMinAndMax(@RequestParam int minAge,
                                                                                @RequestParam int maxAge) {
        return ResponseEntity.ok(studentService.findStudentByAgeBetween(minAge, maxAge));
    }

    @GetMapping("/faculty/{faculty_id}") //Получение студентов данного факультета
    public ResponseEntity<Collection<Student>> getStudentsByFacultyId(@PathVariable Long faculty_id) {
        return ResponseEntity.ok(studentService.findStudentByFacultyId(faculty_id));
    }

    @GetMapping("/getAllStudentsUsingQuery")
    public Long getAllStudentsUsingQuery() {
        return studentService.getAllStudentsUsingQuery();
    }

    @GetMapping("/getAvgAgeOfStudents")
    public Long getAvgAgeOfStudents() {
        return studentService.getAvgAgeOfStudents();
    }

    @GetMapping("/getFiveLastStudents")
    public List<Student> getFiveLastStudents() {
        return studentService.getFiveLastStudents();
    }
}