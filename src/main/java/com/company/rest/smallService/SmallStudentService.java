package com.company.rest.smallService;

import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SmallStudentService {

    private static List<SmallStudent> students = new ArrayList<>();
    private static List<SmallGrade> gradeList = new ArrayList<>();

    static {
        SmallCourse course1 = new SmallCourse("Course1", "Spring", "Steps");
        SmallCourse course2 = new SmallCourse("Course2", "Spring MVC", "Examples");
        SmallCourse course3 = new SmallCourse("Course3", "Spring Boot", "Studen");
        SmallCourse course4 = new SmallCourse("Course4", "Maven", "Most popular");

        SmallStudent student1 = new SmallStudent(
                "123456",
                "Ranga",
                "Hiker",
                new Date(),
                new ArrayList<>(Arrays.asList(course1, course2, course3))
        );

        SmallStudent student2 = new SmallStudent(
                "654321",
                "Satish",
                "Programmer",
                new Date(),
                new ArrayList<>(Arrays.asList(course1, course4))
        );


        SmallGrade grade1 = new SmallGrade(2.0, new Date(), student1.getIndex(), course1.getName());
        SmallGrade grade11 = new SmallGrade(3.5, new Date(), student1.getIndex(), course1.getName());
        SmallGrade grade12 = new SmallGrade(3.5, new Date(), student1.getIndex(), course1.getName());
        SmallGrade grade13 = new SmallGrade(3.5, new Date(), student1.getIndex(), course1.getName());
        SmallGrade grade2 = new SmallGrade(3.0, new Date(), student1.getIndex(), course2.getName());
        SmallGrade grade3 = new SmallGrade(5.0, new Date(), student1.getIndex(), course3.getName());
        SmallGrade grade4 = new SmallGrade(4.5, new Date(), student2.getIndex(), course1.getName());
        SmallGrade grade5 = new SmallGrade(3.5, new Date(), student2.getIndex(), course4.getName());

        students.add(student1);
        students.add(student2);

        gradeList.add(grade1);
        gradeList.add(grade11);
        gradeList.add(grade12);
        gradeList.add(grade13);
        gradeList.add(grade2);
        gradeList.add(grade3);
        gradeList.add(grade4);
        gradeList.add(grade5);
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

    public List<SmallGrade> retrieveGrade(String index, String courseName) {
        List<SmallGrade> grades = new ArrayList<>();

        for (SmallGrade grade : gradeList){
            if (grade.getStudent().equals(index) && grade.getCourseName().equals(courseName)){
                grades.add(grade);
            }
        }

        return grades;
    }

    public List<SmallGrade> getOneGrade(String index, String courseName,  double gradeValue){
        List<SmallGrade> grades = retrieveGrade(index, courseName);
        List<SmallGrade> gradesToReturn = new ArrayList<>();

        for (SmallGrade grade: grades) {

            if (grade.getMark() == gradeValue){
                gradesToReturn.add(grade);
            }
        }

        return gradesToReturn;
    }
}
