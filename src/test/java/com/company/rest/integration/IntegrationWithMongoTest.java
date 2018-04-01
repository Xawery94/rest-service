package com.company.rest.integration;

import com.company.rest.RestApplication;
import com.company.rest.entity.Student;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationWithMongoTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    /*
    String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";
    Course expectedCourse = new Course("1", "Spring", "Arnold");
    List<Student> expectedStudentList = Collections.singletonList(expectedStudent);
    */

    Student expectedStudent = new Student("123456", "Jan", "Kowal", null);

    @Before
    public void setUp() {
        List<MediaType> accept = Collections.singletonList(MediaType.APPLICATION_JSON);
        headers.setAccept(accept);
    }

    @Ignore
    @Test
    public void getStudentList() throws Exception {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/students"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{\"index\":\"123456\",\"name\":\"Jan\",\"lastName\":\"Kowal\",\"birthday\":\"2000-02-01T00:00:00.000+0000\",\"grades\":null,\"courses\":null}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
