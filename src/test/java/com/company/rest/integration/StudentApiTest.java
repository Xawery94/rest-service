package com.company.rest.integration;

import com.company.rest.controllers.StudentController;
import com.company.rest.entity.Course;
import com.company.rest.entity.Student;
import com.company.rest.services.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class, secure = false)
public class StudentApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    Student expectedStudent = new Student("123456", "Jan", "Kowal", null);
    Course expectedCourse = new Course("1", "Spring", "Arnold");
    List<Student> expectedStudentList = Collections.singletonList(expectedStudent);

    @Test
    public void retrieveDetailsForStudent() throws Exception {
        Mockito.when(studentService.getAllStudents()).thenReturn(expectedStudentList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/students").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"index\":\"123456\",\"name\":\"Jan\",\"lastName\":\"Kowal\",\"birthday\":null,\"grades\":null,\"courses\":null}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void retrieveDetailsForCourse() throws Exception {
        Mockito.when(studentService.retrieveCourse("123456", "Spring")).thenReturn(expectedCourse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/students/123456/courses/Spring").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":\"1\",\"name\":\"Spring\",\"teacher\":\"Arnold\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

}
