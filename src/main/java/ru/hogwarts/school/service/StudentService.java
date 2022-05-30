package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student createStudent(Student student) {
        studentRepository.save(student);
        return student;
    }
    public Student getStudentById(Long studentId) {
        logger.info("Was invoked method for getting student by ID");
        return studentRepository.findById(studentId).get();
    }
    public Student updateStudent(Long studentId, Student student) {
        logger.info("Was invoked method for updating student by ID");
        studentRepository.save(student);
        return student;
    }
    public void deleteStudent(Long studentId) {
        logger.info("Was invoked method for delete student by ID");
        studentRepository.deleteById(studentId);
    }
    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method for getting student by age");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findStudentByAgeBetween(int minAge, int maxAge) {
        logger.info("Was invoked method for getting students with age between min and max");
        return studentRepository.findStudentByAgeBetween(minAge, maxAge);
    }

    public Collection<Student> findStudentByFacultyId(Long faculty_id) {
        logger.info("Was invoked method for getting students by faculty");
        return studentRepository.findStudentByFacultyId(faculty_id);
    }

    public Long getAllStudentsUsingQuery() {
        logger.info("Was invoked method for getting all student using query");
        return studentRepository.getAllStudentsUsingQuery();
    }

    public Long getAvgAgeOfStudents() {
        logger.info("Was invoked method for getting students average age");
        return studentRepository.getAvgAgeOfStudents();
    }

    public List<Student> getFiveLastStudents() {
        logger.info("Was invoked method for getting 5 last students");
        return studentRepository.getFiveLastStudents();
    }

    public List<String> getAStudents() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::new)
                .map(String::toUpperCase)
                .sorted().filter(e -> e.startsWith("H"))//В моей БД нет имён, начинающихся с "А"
                .collect(Collectors.toList());

    }

    public double getStudentsAvgAge() {
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge).average().getAsDouble();
    }

}