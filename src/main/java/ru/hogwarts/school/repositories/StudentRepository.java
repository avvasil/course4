package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    Collection<Student> findByAge(int age);

    Collection<Student> findStudentByAgeBetween(int minAge, int maxAge);

    Collection<Student> findStudentByFacultyId(Long faculty_id);

    Student getById(Long studentId);

    @Query(value = "SELECT COUNT(*) FROM hogwarts.public.student", nativeQuery = true)
    Long getAllStudentsUsingQuery();

    @Query(value = "SELECT AVG(age) as avgAge FROM hogwarts.public.student", nativeQuery = true)
    Long getAvgAgeOfStudents();

    @Query(value = "SELECT * FROM hogwarts.public.student OFFSET 2 FETCH NEXT 5 ROWS ONLY", nativeQuery = true)
    List<Student> getFiveLastStudents();


}
