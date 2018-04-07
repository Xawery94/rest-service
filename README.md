## Student Rest-service
Backend side for student service written in JAVA using Mongodb for storage data. Using this service you can manage students, grades and courses. Below are described dependencies and simple REST Api examples.

## Dependencies
* JAVA 8 - https://www.java.com/pl/download/
* Spring Boot - https://projects.spring.io/spring-boot/
* Mongo - https://www.mongodb.com/

## API

[GET] Requests:
````
 * /students -> List[Student]
 * /students/{index} -> {Studnet}
 * /students/{index}/courses/ -> List[Courses] for Student
 * /students/{index}/courses/{courseName}/ -> Course
 * /students/{index}/courses/{courseName}/grades -> List[Grade] for Course and Student
 * /students/{index}/courses/{courseName}/grades/{value} -> List[Grade] for Course and Student
````
---

## Example response for 

### [GET] /students/{index}

```json
{
    "index": "123456",
    "name": "Jan",
    "lastName": "Kowalski",
    "birthday": "2000-10-20T00:00:00.000+0000",
    "grades": [
        {
            "value": 4.5,
            "date": "2018-03-31T17:55:05.791+0000",
            "courseName": "Spring"
        },
        {
            "value": 4.5,
            "date": "2018-03-31T17:55:06.479+0000",
            "courseName": "Spring"
        }
    ],
    "courses": [
        {
            "id": "1",
            "name": "Spring",
            "teacher": "Stefan"
        }
    ]
}
```

```xml
<Student>
    <index>123456</index>
    <name>Jan</name>
    <lastName>Kowalski</lastName>
    <birthday>2000-10-20T00:00:00.000+0000</birthday>
    <grades>
        <grades>
            <value>4.5</value>
            <date>2018-03-31T17:55:05.791+0000</date>
            <courseName>Spring</courseName>
        </grades>
        <grades>
            <value>4.5</value>
            <date>2018-03-31T17:55:06.479+0000</date>
            <courseName>Spring</courseName>
        </grades>
    </grades>
    <courses>
        <courses>
            <id>1</id>
            <name>Spring</name>
            <teacher>Stefan</teacher>
        </courses>
    </courses>
</Student>
```
