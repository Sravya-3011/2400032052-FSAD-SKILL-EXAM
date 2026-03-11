package com.klef.fsad.exam;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Course Entity Class - Persistent Object mapped to 'courses' table in fsadexam DB.
 * Package : com.klef.fsad.exam
 */
@Entity
@Table(name = "courses")
public class Course {

    // ─── Fields ────────────────────────────────────────────────────────────────

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Auto-increment primary key
    @Column(name = "course_id")
    private int id;

    @Column(name = "course_name", nullable = false, length = 100)
    private String name;

    @Column(name = "course_description", length = 500)
    private String description;

    @Column(name = "course_date")
    private LocalDate date;

    @Column(name = "course_status", length = 20)
    private String status;          // e.g., "Active", "Inactive", "Completed"

    @Column(name = "instructor_name", length = 100)
    private String instructorName;

    @Column(name = "duration_weeks")
    private int durationWeeks;

    @Column(name = "max_students")
    private int maxStudents;

    @Column(name = "course_credits")
    private double credits;

    // ─── Constructors ──────────────────────────────────────────────────────────

    /** Default no-arg constructor required by Hibernate */
    public Course() {}

    /** Convenience constructor for inserting new courses */
    public Course(String name, String description, LocalDate date, String status,
                  String instructorName, int durationWeeks, int maxStudents, double credits) {
        this.name          = name;
        this.description   = description;
        this.date          = date;
        this.status        = status;
        this.instructorName = instructorName;
        this.durationWeeks = durationWeeks;
        this.maxStudents   = maxStudents;
        this.credits       = credits;
    }

    // ─── Getters & Setters ─────────────────────────────────────────────────────

    public int getId()                          { return id; }
    public void setId(int id)                   { this.id = id; }

    public String getName()                     { return name; }
    public void setName(String name)            { this.name = name; }

    public String getDescription()              { return description; }
    public void setDescription(String desc)     { this.description = desc; }

    public LocalDate getDate()                  { return date; }
    public void setDate(LocalDate date)         { this.date = date; }

    public String getStatus()                   { return status; }
    public void setStatus(String status)        { this.status = status; }

    public String getInstructorName()           { return instructorName; }
    public void setInstructorName(String name)  { this.instructorName = name; }

    public int getDurationWeeks()               { return durationWeeks; }
    public void setDurationWeeks(int weeks)     { this.durationWeeks = weeks; }

    public int getMaxStudents()                 { return maxStudents; }
    public void setMaxStudents(int max)         { this.maxStudents = max; }

    public double getCredits()                  { return credits; }
    public void setCredits(double credits)      { this.credits = credits; }

    // ─── toString ──────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "\n========== Course Details ==========\n" +
               "  ID            : " + id            + "\n" +
               "  Name          : " + name          + "\n" +
               "  Description   : " + description   + "\n" +
               "  Date          : " + date          + "\n" +
               "  Status        : " + status        + "\n" +
               "  Instructor    : " + instructorName + "\n" +
               "  Duration (wks): " + durationWeeks  + "\n" +
               "  Max Students  : " + maxStudents    + "\n" +
               "  Credits       : " + credits        + "\n" +
               "====================================";
    }
}
