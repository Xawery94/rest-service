## Student Rest-service
Backend side for student service written in JAVA using Mongodb for storage data. Using this service you can manage students, grades and courses. Below are described dependencies and simple REST Api examples.

## Dependencies
* JAVA 8 - https://www.java.com/pl/download/
* Spring Boot - https://projects.spring.io/spring-boot/
* Mongo - https://www.mongodb.com/

## API

[GET] Requests for student:
```
 * /students -> List[Student]
 * /students?name={name} -> Student by name
 * /students?lastNmae={lastName} -> Student by lastName
 * /students?name={name}&lastName={lastName} -> Student by name and lastName
 * /students?birthday={Date} -> Student by birthday
 * /students?birthdayBefore={Date} -> List{Student} birthday before putted Date
 * /students?birthdayAfter={Date} -> List{Student} birthday after putted Date
 * /students/{index} -> {Studnet}
 * /students/{index}/courses/ -> List[Courses] for Student
 * /students/{index}/courses/{courseName}/ -> Course
 * /students/{index}/courses/{courseName}/grades -> List[Grade] for Course and Student
 * /students/{index}/courses/{courseName}/grades?valueGrater={value} -> List[Grade] grater than Value
 * /students/{index}/courses/{courseName}/grades?valueLess={value} -> List[Grade] less than Value
 * /students/{index}/courses/{courseName}/grades/{value} -> List[Grade] for Course and Student
```

[GET] Requests for course:
```
* /courses -> List{Course}
* /courses?teacher={name} -> Course
```



---

## Example response for 

### [GET] /students/{index}

```json
{
    "index": "123456",
    "name": "Jan",
    "lastName": "Kowalski",
    "birthday": "2000-10-20"
}
```

```xml
<Student>
    <index>123456</index>
    <name>Jan</name>
    <lastName>Kowalski</lastName>
    <birthday>2000-10-20</birthday>
</Student>
```
