package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        studentRepository.save(student);
        return student;
    }
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).get();
    }
    public Student updateStudent(Long studentId, Student student) {
        studentRepository.save(student);
        return student;
    }
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
    public Collection<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findStudentByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findStudentByAgeBetween(minAge, maxAge);
    }

    public Collection<Student> findStudentByFacultyId(Long faculty_id) {
        return studentRepository.findStudentByFacultyId(faculty_id);
    }

}