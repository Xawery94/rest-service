package com.company.rest.services.impl;

import com.company.rest.dao.CourseRepository;
import com.company.rest.dao.StudentRepository;
import com.company.rest.entity.Course;
import com.company.rest.entity.Grade;
import com.company.rest.entity.Student;
import com.company.rest.exceptions.AddNewGradeException;
import com.company.rest.exceptions.StudentDataExceptions;
import com.company.rest.exceptions.StudentDeleteException;
import com.company.rest.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(String index) {
        return studentRepository.findOneByIndex(index);
    }

    @Override
    public List<Student> getStudentsByNameAndLastName(String name, String lastName) {
        List<Student> studentList = studentRepository.findAll();

        if (name != null && lastName != null) {
            final List<Student> students = studentList.stream()
                    .filter(sn -> sn.getName().equals(name))
                    .filter(sl -> sl.getLastName().equals(lastName))
                    .collect(Collectors.toList());

            if (students == null) {
                return null;
            } else {
                return students;
            }
        } else if (name != null) {
            return studentRepository.findAllByName(name);
        } else if (lastName != null) {
            return studentRepository.findAllByLastName(lastName);
        } else {
            return studentList;
        }
    }

    @Override
    public List<Student> getStudentsByBirthday(Date date) {
        return studentRepository.findAllByBirthday(date);
    }

    @Override
    public List<Student> getStudentsByBirthdayBefore(Date date) {
        List<Student> studentList = studentRepository.findAll();

        final List<Student> students = studentList.stream()
                .filter(s -> s.getBirthday().before(date))
                .collect(Collectors.toList());


        if (students == null) {
            return null;
        } else {
            return students;
        }
    }

    @Override
    public List<Student> getStudentsByBirthdayAfter(Date date) {
        List<Student> studentList = studentRepository.findAll();

        final List<Student> students = studentList.stream()
                .filter(s -> s.getBirthday().after(date))
                .collect(Collectors.toList());


        if (students == null) {
            return null;
        } else {
            return students;
        }
    }

    @Override
    public List<Grade> retrieveGradeGraterThan(String index, String courseName, double value) {
        Student student = studentRepository.findOneByIndex(index);

        if (student.getGrades() == null) {
            return null;
        }

        return student.getGrades().stream()
                .filter(x -> x.getCourseName().equals(courseName))
                .filter(g -> g.getValue() > value)
                .collect(Collectors.toList());
    }

    @Override
    public List<Grade> retrieveGradeLessThan(String index, String courseName, double value) {
        Student student = studentRepository.findOneByIndex(index);

        if (student.getGrades() == null) {
            return null;
        }

        return student.getGrades().stream()
                .filter(x -> x.getCourseName().equals(courseName))
                .filter(g -> g.getValue() < value)
                .collect(Collectors.toList());
    }

    @Override
    public Student addStudent(Student body) {
        if (body.getIndex() == null || body.getName() == null || body.getLastName() == null || body.getBirthday().toString() == null) {
            throw new StudentDataExceptions();
        }

        Student existingStudent = studentRepository.findOneByIndex(body.getIndex());

        if (existingStudent != null) {
            try {
                studentRepository.save(body);
            } catch (Exception exc) {
                log.warn("Trying add duplicate student with index {}", body.getIndex());
                return null;
            }
            return body;
        }

        studentRepository.save(body);
        return body;
    }

    @Override
    public Student updateStudent(String index, Student student) {
        Student currentStudent = studentRepository.findOneByIndex(index);

        currentStudent.setName(student.getName());
        currentStudent.setLastName(student.getLastName());
        currentStudent.setBirthday(student.getBirthday());

        studentRepository.save(currentStudent);
        return currentStudent;
    }

    @Override
    public void deleteStudent(String index) {
        if (index != null) {
            studentRepository.deleteByIndex(index);
        } else {
            throw new StudentDeleteException();
        }
    }

    @Override
    public List<Course> retrieveAllCourses(String index) {
        if (index != null) {
            Student student = studentRepository.findOneByIndex(index);
            return student.getCourses();
        } else {
            return null;
        }
    }

    @Override
    public Course retrieveCourse(String index, String courseName) {
        Student student = studentRepository.findOneByIndex(index);

        if (student == null) {
            return null;
        }

        if (student.getCourses() == null) {
            return null;
        }

        for (Course course : student.getCourses()) {
            if (course.getName().equals(courseName)) {
                return course;
            }
        }

        return null;
    }

    @Override
    public List<Grade> retrieveGrade(String index, String courseName) {
        Student student = studentRepository.findOneByIndex(index);

        if (student.getGrades() == null) {
            return null;
        }

        return student.getGrades().stream()
                .filter(x -> x.getCourseName().equals(courseName))
                .collect(Collectors.toList());
    }

    @Override
    public Grade getOneGrade(String index, String courseName, Integer id) {
        if (id != null) {
            List<Grade> grades = retrieveGrade(index, courseName);

            if (grades != null) {
                for (Grade grade : grades) {
                    if (grade.getId().equals(id)) {
                        return grade;
                    }
                }
            }
        }


        return null;
    }

    @Override
    public void deleteCourse(String index, String courseName) {
        Student student = studentRepository.findOneByIndex(index);
        if (student == null) {
            throw new StudentDeleteException();
        }

        List<Course> courses = student.getCourses();
        courses.removeIf(o -> o.getName().equals(courseName));
        student.setCourses(courses);
        studentRepository.save(student);
    }

    @Override
    public Grade addNewGrade(String index, String courseName, Grade gradeBody) {
        if (index != null) {
            Student student = studentRepository.findOneByIndex(index);

            Grade grade = new Grade(gradeBody.getId(), gradeBody.getValue(), new Date(), courseName);
            List<Grade> gradeList = student.getGrades();

            double gradeValue = grade.getValue();

            if (gradeValue < 2.0 || gradeValue > 5.0) {
                throw new AddNewGradeException();
            }

            if (gradeList == null) {
                student.setGrades(Collections.singletonList(grade));
            } else {
                if (containsGradeId(gradeList, gradeBody.getId())) {
                    log.warn("Trying add duplicated grade");
                    return null;
                } else {
                    gradeList.add(grade);
                }
            }

            studentRepository.save(student);
            return grade;
        }

        return null;
    }

    @Override
    public Course updateCourse(String index, String courseName, Course newCourse) {
        if (index != null) {
            Student student = studentRepository.findOneByIndex(index);

            for (Course course : student.getCourses()) {
                if (course.getName().equals(courseName)) {
                    course.setName(newCourse.getName());
                    course.setTeacher(newCourse.getTeacher());

                    studentRepository.save(student);
                    return course;
                }
            }
        }

        return null;
    }

    @Override
    public Grade updateGrade(String index, String courseName, Integer id, Grade newGrade) {
        if (index != null) {
            Student student = studentRepository.findOneByIndex(index);

            for (Grade grade : student.getGrades()) {
                if (grade.getId().equals(id)) {
                    grade.setValue(newGrade.getValue());

                    studentRepository.save(student);
                    return grade;
                }
            }
        }

        return null;
    }

    @Override
    public void deleteGrade(String index, String courseName, Integer id) {
        Student student = studentRepository.findOneByIndex(index);
        if (student == null) {
            throw new StudentDataExceptions();
        }

        List<Grade> gradeList = student.getGrades();

        for (Grade grade : gradeList) {
            if (grade.getId().equals(id)) {
                gradeList.remove(grade);

                student.setGrades(gradeList);
                studentRepository.save(student);
            }
        }
    }

    @Override
    public Course addNewCourse(String index, Course course) {
        if (index != null) {
            Student student = studentRepository.findOneByIndex(index);

            List<Course> courseList = student.getCourses();

            if (courseList == null) {
                student.setCourses(Collections.singletonList(course));
            } else {
                if (containsCourse(student.getCourses(), course.getName())) {
                    log.warn("Trying add duplicated course");
                    return null;
                } else {
                    student.getCourses().add(course);
                }
            }

            studentRepository.save(student);
            return course;

        }
        return null;
    }

    private boolean containsCourse(final List<Course> list, String name) {
        return list
                .stream()
                .anyMatch(c -> c.getName().equals(name));
    }

    private boolean containsGradeId(List<Grade> gradeList, int id) {
        return gradeList
                .stream()
                .anyMatch(g -> g.getId().equals(id));
    }

}
