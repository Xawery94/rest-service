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
[
    {
        "index": "123456",
        "name": "Paga",
        "lastName": "Alonso",
        "birthday": "2018-03-31T00:00:00.000+0000",
        "grades": [
            {
                "value": 4,
                "date": "2018-03-31T12:00:36.073+0000",
                "courseName": "Spring"
            },
            {
                "value": 2.5,
                "date": "2018-03-31T12:01:06.370+0000",
                "courseName": "Spring"
            }
        ],
        "courses": [
            {
                "id": "1",
                "name": "Spring",
                "teacher": "Arnold"
            }
        ]
    }
]
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
	<0>
		<index>123456</index>
		<name>Paga</name>
		<lastName>Alonso</lastName>
		<birthday>2018-03-31T00:00:00.000+0000</birthday>
		<grades>
			<value>4</value>
			<date>2018-03-31T12:00:36.073+0000</date>
			<courseName>Spring</courseName>
		</grades>
		<grades>
			<value>2.5</value>
			<date>2018-03-31T12:01:06.370+0000</date>
			<courseName>Spring</courseName>
		</grades>
		<courses>
			<id>1</id>
			<name>Spring</name>
			<teacher>Arnold</teacher>
		</courses>
	</0>
```
