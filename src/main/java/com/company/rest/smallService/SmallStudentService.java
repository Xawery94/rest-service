package com.company.rest.smallService;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SmallStudentService {

    private static List<SmallStudent> students = new ArrayList<>();
    private static List<SmallGrade> gradeList = new ArrayList<>();
    private static List<SmallCourse> courseList = new ArrayList<>();

    static {
        SmallCourse course1 = new SmallCourse("Course1", "Spring", "Steps");
        SmallCourse course2 = new SmallCourse("Course2", "Spring MVC", "Examples");
        SmallCourse course3 = new SmallCourse("Course3", "Spring Boot", "Stude");
        SmallCourse course4 = new SmallCourse("Course4", "Maven", "Most popular");

        SmallStudent student1 = new SmallStudent(
                "123456",
                "Ranga",
                "Hiker",
                new Date(),
                courseList
        );

        SmallStudent student2 = new SmallStudent(
                "654321",
                "Satish",
                "Programmer",
                new Date(),
                courseList
        );

        SmallGrade grade11 = new SmallGrade(2.0, student1.getIndex(), course1.getName());
        SmallGrade grade12 = new SmallGrade(3.5, student1.getIndex(), course2.getName());
        SmallGrade grade13 = new SmallGrade(3.5, student1.getIndex(), course3.getName());
        SmallGrade grade14 = new SmallGrade(3.5, student1.getIndex(), course4.getName());

        SmallGrade grade21 = new SmallGrade(3.0, student2.getIndex(), course1.getName());
        SmallGrade grade22 = new SmallGrade(5.0, student2.getIndex(), course2.getName());
        SmallGrade grade23 = new SmallGrade(4.5, student2.getIndex(), course3.getName());
        SmallGrade grade24 = new SmallGrade(3.5, student2.getIndex(), course4.getName());

        students.add(student1);
        students.add(student2);

        gradeList.add(grade11);
        gradeList.add(grade12);
        gradeList.add(grade13);
        gradeList.add(grade21);
        gradeList.add(grade23);
        gradeList.add(grade24);

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);

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

    public List<?> retrieveCourses(String index) {
        SmallStudent student = retrieveStudent(index);

        if (student == null) {
            return null;
        }

        return student.getCourses();
    }

    public SmallCourse retrieveCourse(String studentId, String courseName) {
        SmallStudent student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        for (SmallCourse course : student.getCourses()) {
            if (course.getName().equals(courseName)) {

                /*
                final List<SmallGrade> grades = gradeList.stream()
                        .filter(g -> g.getCourseName().equals(courseName))
                        .filter(s -> s.getStudent().equals(student.getIndex()))
                        .collect(Collectors.toList());
                course.setGrade(grades);

                */

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

        for (SmallGrade grade : gradeList) {
            if (grade.getStudent().equals(index) && grade.getCourseName().equals(courseName)) {
                grades.add(grade);
            }
        }

        return grades;
    }

    public List<SmallGrade> getOneGrade(String index, String courseName, double gradeValue) {
        List<SmallGrade> grades = retrieveGrade(index, courseName);
        List<SmallGrade> gradesToReturn = new ArrayList<>();

        for (SmallGrade grade : grades) {

            if (grade.getMark() == gradeValue) {
                gradesToReturn.add(grade);
            }
        }

        return gradesToReturn;
    }

    public List<SmallGrade> retrieveGradeForStudent(String index) {
        final List<SmallGrade> grades = gradeList.stream()
                .filter(grade -> grade.getStudent().equals(index))
                .collect(Collectors.toList());

        if (grades == null) {
            return null;
        }

        return grades;
    }

    public List<SmallGrade> retrieveOneTypeGradeForStudent(String index, double value) {
        List<SmallGrade> gradesForStudent = retrieveGradeForStudent(index);

        final List<SmallGrade> grades = gradesForStudent.stream()
                .filter(grade -> grade.getMark() == value)
                .collect(Collectors.toList());


        if (grades == null) {
            return null;
        }

        return grades;
    }

}
