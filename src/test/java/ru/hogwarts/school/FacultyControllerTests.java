package ru.hogwarts.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerTests {

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testAddFaculty() throws Exception {

        long id = 1L;
        String name = "Ruttenfork";
        String color = "Red";

        long id1 = 2L;
        String name1 = "Glufendort";
        String color1 = "Green";

        String name3 = "rutTenFoRk";
        String color3 = "rED";

        String studentName = "John";
        int studentAge = 23;


        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        JSONObject facultyObject1 = new JSONObject();
        facultyObject.put("id", id1);
        facultyObject.put("name", name1);
        facultyObject.put("color", color1);


        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        Faculty faculty1 = new Faculty();
        faculty.setId(id1);
        faculty.setName(name1);
        faculty.setColor(color1);

        Student student = new Student();
        student.setId(id);
        student.setName(studentName);
        student.setAge(studentAge);
        student.setFaculty(faculty);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        this.mockMvc.perform(MockMvcRequestBuilders //Create Faculty
                        .post("/faculty", faculty)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.color").value(color1));

    }

    @Test
    public void testFindById() throws Exception {

        long id = 1L;
        String name = "Ruttenfork";
        String color = "Red";


        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.findById(any(long.class))).thenReturn(Optional.of(faculty));

        this.mockMvc.perform(MockMvcRequestBuilders //Get Faculty By Id
                        .get("/faculty/find/{facultyId}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void testFindByColor() throws Exception {

        long id = 1L;
        String name = "Ruttenfork";
        String color = "Red";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        Collection<Faculty> facultyCollection = new ArrayList<>();

        facultyCollection.add(faculty);

        when(facultyRepository.findByColor(any(String.class))).thenReturn(facultyCollection);

        this.mockMvc.perform(MockMvcRequestBuilders //Get Faculty By Color
                        .get("/faculty/color/{color}", color)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].color").value(color));
    }

    @Test
    public void testFindByNameAndColorIgnoreCase() throws Exception {

        long id = 1L;
        String name = "Ruttenfork";
        String color = "Red";

        long id1 = 2L;
        String name1 = "Glufendort";
        String color1 = "Green";

        String name3 = "rutTenFoRk";
        String color3 = "rED";

        String studentName = "John";
        int studentAge = 23;


        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        JSONObject facultyObject1 = new JSONObject();
        facultyObject.put("id", id1);
        facultyObject.put("name", name1);
        facultyObject.put("color", color1);


        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        Collection<Faculty> facultyCollection = new ArrayList<>();

        facultyCollection.add(faculty);

        when(facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name3, color3)).thenReturn(facultyCollection);


        this.mockMvc.perform(MockMvcRequestBuilders //Get Faculty By Name Ignore Case Or Color Ignore Case
                        .get("/faculty")
                        .queryParam("name", name3)
                        .queryParam("color", color3)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].color").value(color))
                .andExpect(jsonPath("$[0].name").value(name));

    }

    @Test
    public void testDeleteById() throws Exception {

        long id = 1L;
        String name = "Ruttenfork";
        String color = "Red";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.getById(id)).thenReturn(faculty);

        this.mockMvc.perform(MockMvcRequestBuilders //Remove Faculty
                        .delete("/faculty/remove/{facultyId}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testUpdateFaculty() throws Exception {

        long id = 1L;
        String name = "Ruttenfork";
        String color = "Red";

        long id1 = 2L;
        String name1 = "Glufendort";
        String color1 = "Green";

        String name3 = "rutTenFoRk";
        String color3 = "rED";

        String studentName = "John";
        int studentAge = 23;


        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        JSONObject facultyObject1 = new JSONObject();
        facultyObject.put("id", id1);
        facultyObject.put("name", name1);
        facultyObject.put("color", color1);


        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        Faculty faculty1 = new Faculty();
        faculty.setId(id1);
        faculty.setName(name1);
        faculty.setColor(color1);

        when(facultyRepository.findById(any(long.class))).thenReturn(Optional.of(faculty));
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);


        this.mockMvc.perform(MockMvcRequestBuilders //Update Faculty
                        .put("/faculty", faculty)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.color").value(color1));

    }

    @Test
    public void testfindFacultyByStudentsIsContaining() throws Exception {

        long facultyId = 22L;
        String name = "Greenfield";
        String color = "Green";

        long id = 1L;
        String studentName = "John";
        int studentAge = 90;


        Faculty faculty = new Faculty();
        faculty.setId(facultyId);;
        faculty.setName(name);
        faculty.setColor(color);

        Student student = new Student();
        student.setId(id);
        student.setName(studentName);
        student.setAge(studentAge);
        student.setFaculty(faculty);

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("studentName", studentName);
        studentObject.put("studentAge", studentAge);
        studentObject.put("faculty", faculty);

        when(facultyRepository.findFacultyByStudentsIsContaining(any(Student.class))).thenReturn(faculty.getId());

        this.mockMvc.perform(MockMvcRequestBuilders //Get Faculty By Student
                        .get("/faculty/student/{student}", student)
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value(color));
    }

    public static Student valueOf(String json) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Student.class);
    }
}
