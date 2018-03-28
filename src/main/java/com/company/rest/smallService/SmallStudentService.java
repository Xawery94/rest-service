package com.company.rest.smallService;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class SmallStudentService {

    private static List<SmallStudent> students = new ArrayList<>();

    static {
        SmallCourse course1 = new SmallCourse("Course1", "Spring", "Steps");
        SmallCourse course2 = new SmallCourse("Course2", "Spring MVC", "Examples");
        SmallCourse course3 = new SmallCourse("Course3", "Spring Boot", "Studen");
        SmallCourse course4 = new SmallCourse("Course4", "Maven", "Most popular");

        SmallStudent ranga = new SmallStudent(
                "123456",
                "Ranga",
                "Hiker",
                new Date(),
                new ArrayList<>(Arrays.asList(course1, course2, course3))
        );

        SmallStudent satish = new SmallStudent(
                "654321",
                "Satish",
                "Programmer",
                new Date(),
                new ArrayList<>(Arrays.asList(course1, course4))
        );


        SmallGrade grade1 = new SmallGrade(2.0, new Date(), "123456");
        SmallGrade grade2 = new SmallGrade(3.0, new Date(), "123456");
        SmallGrade grade3 = new SmallGrade(5.0, new Date(), "123456");
        SmallGrade grade4 = new SmallGrade(4.5, new Date(), "654321");
        SmallGrade grade5 = new SmallGrade(3.5, new Date(), "654321");

        course1.setGrade(Arrays.asList(grade2, grade3, grade5));
        course2.setGrade(Arrays.asList(grade1, grade3));
        course3.setGrade(Arrays.asList(grade1, grade2, grade3));
        course4.setGrade(Arrays.asList(grade4, grade5));

        students.add(ranga);
        students.add(satish);
    }

    public List<SmallStudent> retrieveAllStudents() {
        return students;
    }

    public SmallStudent retrieveStudent(String studentIndex) {
        for (SmallStudent student : students) {
            if (student.getIndex().equals(studentIndex)) {
                return student;
            }
        }
        return null;
    }

    public List<SmallCourse> retrieveCourses(String studentId) {
        SmallStudent student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        return student.getCourses();
    }

    public SmallCourse retrieveCourse(String studentId, String courseId) {
        SmallStudent student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        for (SmallCourse course : student.getCourses()) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }

        return null;
    }

    private SecureRandom random = new SecureRandom();

    public SmallCourse addCourse(String studentId, SmallCourse course) {
        SmallStudent student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        String randomId = new BigInteger(130, random).toString(32);
        course.setId(randomId);

        student.getCourses().add(course);

        return course;
    }
}
