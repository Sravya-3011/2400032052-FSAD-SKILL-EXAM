# KLEF University – FSAD Hibernate Exam Project

## Project Structure
```
HibernateExam/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/klef/fsad/exam/
        │       ├── Course.java        ← Entity (Persistent Object)
        │       └── ClientDemo.java    ← Client with Insert & View operations
        └── resources/
            └── hibernate.cfg.xml     ← DB configuration
```

## Prerequisites
- Java 11+
- Maven 3.6+
- MySQL Server running on localhost:3306

## Database Setup (Run in MySQL)
```sql
CREATE DATABASE IF NOT EXISTS fsadexam;
-- Hibernate's hbm2ddl.auto=update will auto-create the 'courses' table.
```

## Configure DB Credentials
Edit `src/main/resources/hibernate.cfg.xml`:
```xml
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">root</property>
```
Change `root`/`root` to your MySQL username and password.

## Build & Run
```bash
# From the HibernateExam/ directory:
mvn clean compile
mvn exec:java
```

## Operations Demonstrated
| # | Operation | Method in ClientDemo |
|---|-----------|----------------------|
| I | Insert new Course record | `insertCourse()` |
| II | View Course record by ID | `viewCourseById(int id)` |

## Entity: Course
| Column | Java Field | Type | Notes |
|--------|-----------|------|-------|
| course_id | id | int | Auto-generated PK |
| course_name | name | String | NOT NULL |
| course_description | description | String | |
| course_date | date | LocalDate | |
| course_status | status | String | Active/Inactive/Completed |
| instructor_name | instructorName | String | |
| duration_weeks | durationWeeks | int | |
| max_students | maxStudents | int | |
| course_credits | credits | double | |
