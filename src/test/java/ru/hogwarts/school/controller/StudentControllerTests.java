import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final FacultyRecord facultyRecord = givenFacultyWith(1L, "Gryffindor", "red");

    private final StudentRecord studentRecordTest = givenStudentWith("StudentName", 20, facultyRecord);

    private final StudentRecord studentRecordEdit = givenStudentWith("StudentEdited", 25, facultyRecord);


    private final int ageTest = 20;

    private final int minAge = 20;

    private final int maxAge = 25;

    @Test
    public void testCreateStudent() {
        Assertions
                .assertThat(this.restTemplate.postForObject(getUriBuilder(), studentRecordTest, StudentRecord.class)).isNotNull();

    }

    @Test
    public void testReadStudentById() {
        Assertions
                .assertThat(this.restTemplate.getForObject(getUriBuilder() + studentRecordTest.getId(), StudentRecord.class)).isNotNull();
    }

    @Test
    public void testDeleteStudentById() {
        this.restTemplate.delete(getUriBuilder() + studentRecordTest.getId(), StudentRecord.class);
    }

    @Test
    public void testPutStudentById() {
        this.restTemplate.put(getUriBuilder() + studentRecordTest.getId(), studentRecordEdit, StudentRecord.class);
    }

    @Test
    public void testGetStudentsByAge() {

        Assertions
                .assertThat(this.restTemplate.getForObject(getUriBuilder() + ageTest, StudentRecord.class)).isNotNull();
    }

    @Test
    public void testGetStudentsByAgeBetween() {

        Assertions
                .assertThat(this.restTemplate.getForObject(getUriBuilder() + maxAge + "&" + minAge, StudentRecord.class)).isNotNull();
    }

    @Test
    public void testGetFacultyByStudent() {
        Assertions
                .assertThat(this.restTemplate.getForObject(getUriBuilder() + studentRecordTest.getId() + "/faculty", FacultyRecord.class));
    }

    private StudentRecord givenStudentWith(String name, int age, FacultyRecord facultyRecord) {
        StudentRecord studentRecord = new StudentRecord();
        studentRecord.setName(name);
        studentRecord.setAge(age);
        studentRecord.setFaculty(facultyRecord);
        return studentRecord;
    }

    private FacultyRecord givenFacultyWith(Long facultyId, String name, String color) {
        FacultyRecord facultyRecord = new FacultyRecord();
        facultyRecord.setId(facultyId);
        facultyRecord.setName(name);
        facultyRecord.setColor(color);
        return facultyRecord;
    }

    private String getUriBuilder() {
        return "http://localhost:" + port + "/hogwarts/students";
    }


}