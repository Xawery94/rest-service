package com.company.rest.smallService;

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
    private SecureRandom random = new SecureRandom();

    static {
        SmallCourse course1 = new SmallCourse("1", "Spring", "Arnold");
        SmallCourse course2 = new SmallCourse("2", "Spring MVC", "Popin");
        SmallCourse course3 = new SmallCourse("3", "Spring Boot", "Gowin");
        SmallCourse course4 = new SmallCourse("4", "Maven", "Tom");

        SmallStudent student1 = new SmallStudent(
                "123456",
                "Ranga",
                "Hiker",
                new Date()
        );

        SmallStudent student2 = new SmallStudent(
                "654321",
                "Satish",
                "Programmer",
                new Date()
        );

        SmallGrade grade1 = new SmallGrade(2.0, course1.getName());
        SmallGrade grade2 = new SmallGrade(3.5, course2.getName());
        SmallGrade grade3 = new SmallGrade(3.5, course3.getName());
        SmallGrade grade4 = new SmallGrade(3.5, course4.getName());
        SmallGrade grade5 = new SmallGrade(5.0, course4.getName());

        students.add(student1);
        students.add(student2);

        student1.setGrades(Arrays.asList(grade1, grade2));
        student2.setGrades(Arrays.asList(grade3, grade4, grade5));

        student1.setCourses(Arrays.asList(course1, course2));
        student2.setCourses(Arrays.asList(course1, course3, course4));

    }

    //  GET Controller
    public List<SmallStudent> retrieveAllStudents() {
        return students;
    }

    public SmallStudent retrieveStudent(String index) {
        for (SmallStudent student : students) {
            if (student.getIndex().equals(index)) {
                return student;
            }
        }
        return null;
    }

    public List<SmallCourse> retrieveCourses(String index) {
        SmallStudent student = retrieveStudent(index);

        if (student == null) {
            return null;
        }

        return student.getCourses();
    }

    public SmallCourse retrieveCourse(String index, String courseName) {
        SmallStudent student = retrieveStudent(index);

        if (student == null) {
            return null;
        }

        for (SmallCourse course : student.getCourses()) {
            if (course.getName().equals(courseName)) {
                return course;
            }
        }

        return null;
    }

    public List<SmallGrade> retrieveGrade(String index, String courseName) {
        SmallStudent student = retrieveStudent(index);

        List<SmallGrade> grades = student.getGrades().stream()
                .filter(x -> x.getCourseName().equals(courseName))
                .collect(Collectors.toList());

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
        SmallStudent student = retrieveStudent(index);

        if (student == null) {
            return null;
        }

        return student.getGrades();
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


    //  POST Controller
    public SmallStudent addStudent(SmallStudent student){
        students.add(student);

        return student;
    }



    public SmallCourse addCourse(String index, SmallCourse course) {
        SmallStudent student = retrieveStudent(index);

        if (student == null) {
            return null;
        }

        String randomId = new BigInteger(130, random).toString(32);
        course.setId(randomId);

        student.getCourses().add(course);

        return course;
    }

    public SmallStudent updateStudent(String index, SmallStudent student) {
        SmallStudent currentStudent = retrieveStudent(index);
        students.remove(currentStudent);

        currentStudent.setName(student.getName());
        currentStudent.setLastName(student.getLastName());
        currentStudent.setBirthday(student.getBirthday());

        students.add(currentStudent);

        
        return currentStudent;
    }
}
