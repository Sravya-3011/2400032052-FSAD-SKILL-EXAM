package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

/**
 * ClientDemo - Demonstrates Hibernate CRUD operations on the Course entity.
 *
 * Operations:
 *   I.  Insert a new Course record into the database.
 *   II. View (retrieve) a Course record by its ID.
 *
 * Package  : com.klef.fsad.exam
 * Database : fsadexam
 */
public class ClientDemo {

    // ─── SessionFactory (shared, thread-safe) ──────────────────────────────────
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")   // loads src/main/resources/hibernate.cfg.xml
                    .buildSessionFactory();
            System.out.println("\n[Hibernate] SessionFactory created successfully.");
        } catch (Exception e) {
            System.err.println("[Hibernate] SessionFactory creation FAILED: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    // ─── Main ──────────────────────────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   KLEF University – FSAD Hibernate Exam  ║");
        System.out.println("║   Database : fsadexam                     ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // ── Operation I : Insert a new Course ──────────────────────────────────
        int generatedId = insertCourse();

        // ── Operation II : View the Course by ID ───────────────────────────────
        if (generatedId > 0) {
            viewCourseById(generatedId);
        }

        // ── (Optional) Insert a second course and retrieve it ──────────────────
        int secondId = insertSecondCourse();
        if (secondId > 0) {
            viewCourseById(secondId);
        }

        // ── Close factory ──────────────────────────────────────────────────────
        sessionFactory.close();
        System.out.println("\n[Hibernate] SessionFactory closed. Program ended.\n");
    }

    // ─── Operation I : Insert ──────────────────────────────────────────────────

    /**
     * Inserts a new Course record and returns the auto-generated ID.
     */
    public static int insertCourse() {
        System.out.println("\n─── Operation I : Insert New Course ───────────────────");

        // Build the persistent object
        Course course = new Course(
                "Full Stack Application Development",
                "Covers front-end (React), back-end (Spring Boot) and DevOps fundamentals.",
                LocalDate.of(2025, 1, 15),
                "Active",
                "Dr. A. Ramesh",
                16,
                60,
                4.0
        );

        int savedId = 0;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {

            tx = session.beginTransaction();
            savedId = (int) session.save(course);   // Hibernate generates the ID automatically
            tx.commit();

            System.out.println("[SUCCESS] Course inserted. Auto-generated ID = " + savedId);

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("[ERROR] Insert failed: " + e.getMessage());
            e.printStackTrace();
        }
        return savedId;
    }

    // ─── Helper : Insert a second course ──────────────────────────────────────

    private static int insertSecondCourse() {
        System.out.println("\n─── Inserting Second Course (bonus demo) ──────────────");

        Course course = new Course(
                "Database Management Systems",
                "Relational databases, SQL, normalization, indexing and transactions.",
                LocalDate.of(2025, 2, 10),
                "Active",
                "Prof. S. Kumar",
                12,
                75,
                3.5
        );

        int savedId = 0;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {

            tx = session.beginTransaction();
            savedId = (int) session.save(course);
            tx.commit();
            System.out.println("[SUCCESS] Second Course inserted. Auto-generated ID = " + savedId);

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("[ERROR] Insert failed: " + e.getMessage());
            e.printStackTrace();
        }
        return savedId;
    }

    // ─── Operation II : View by ID ─────────────────────────────────────────────

    /**
     * Retrieves and displays a Course record by its primary key (ID).
     */
    public static void viewCourseById(int id) {
        System.out.println("\n─── Operation II : View Course by ID = " + id + " ──────────");

        try (Session session = sessionFactory.openSession()) {

            Course course = session.get(Course.class, id);   // returns null if not found

            if (course != null) {
                System.out.println("[SUCCESS] Record found:" + course);
            } else {
                System.out.println("[INFO] No Course found with ID = " + id);
            }

        } catch (Exception e) {
            System.err.println("[ERROR] View failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
