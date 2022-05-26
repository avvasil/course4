package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    Student student1 = new Student();
    Student student2 = new Student();
    final long id = 1;
    final String name1 = "Bob";
    final String name2 = "Ivan";
    final int age1 = 23;
    final int age2 = 78;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testCreateStudent() throws Exception {

        student1.setId(id);
        student1.setName(name1);
        student1.setAge(age1);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port, student1, Student.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentById() throws Exception {

        student1.setId(id);
        student1.setName(name1);
        student1.setAge(age1);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/1", Student.class))
                .isInstanceOf(Student.class);
    }

//    @Test
//    public void testUpdateStudent() throws Exception {
//
//        Student student1 = new Student();
//        Student student2 = new Student();
//        final long id = 1;
//        final String name1 = "Bob";
//        final String name2 = "Ivan";
//        final int age1 = 23;
//        final int age2 = 78;
//
//        Assertions
//                .assertThat(this.restTemplate.put("http://localhost:" + port, Student.class))
//                .is
//
//    }

//	}
//    @Test
//    public void testDeleteStudent() throws Exception {
//
//        student1.setId(id);
//        student1.setName(name1);
//        student1.setAge(age1);
//
//        Assertions
//                .assertThat(this.restTemplate.delete("http://localhost:" + port + "/student/1", "id" , "1", Void.class));
//    }

    @Test
    public void testGetStudentByAge() throws Exception {

        student1.setId(id);
        student1.setName(name1);
        student1.setAge(age1);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age/23", List.class))
                .isNotEmpty();
    }

    @Test
    public void testGetStudentByAgeBetweenMinAndMax() throws Exception {

        student1.setId(id);
        student1.setName(name1);
        student1.setAge(age1);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", List.class, "minAge", 0, "maxAge",1000))
                .isNotEmpty();
    }

    @Test
    public void testGetStudentFaculty() throws Exception {

        Faculty faculty = new Faculty();
        faculty.setId(22L);
        faculty.setColor("Green");
        faculty.setName("Greenfield");

        student1.setId(id);
        student1.setName(name1);
        student1.setAge(age1);
        student1.setFaculty(faculty);

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/22", List.class))
                .isNotEmpty();
    }
}
