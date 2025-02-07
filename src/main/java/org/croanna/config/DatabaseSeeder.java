package org.croanna.config;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.croanna.enums.ExamType;
import org.croanna.enums.StatusType;
import org.croanna.models.*;

import java.time.LocalDateTime;
import java.util.Set;

@ApplicationScoped
public class DatabaseSeeder {

    @Inject
    EntityManager em;

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        // runs on startup
        boolean isNotAlreadySeeded = em.createQuery("SELECT COUNT(v) FROM Vehicle v", Long.class)
                .getSingleResult() == 0;
        if (isNotAlreadySeeded) {
            seedDatabase();
        }
    }

    private void seedDatabase() {
        Category categoryA = createCategory("A", "Motorcycles", 30, 24);
        Category categoryB = createCategory("B", "Personal vehicles up to 3500kg", 40, 18);

        Vehicle vehicle1 = createVehicle("ZG122PP", "Audi A3", 2022);
        Vehicle vehicle2 = createVehicle("KA787SR", "BMW X6", 2020);

        Instructor instructor1 = createInstructor("Stjepan Bukvic", "+385911441414", "16:00-21:00", Set.of(categoryB));
        Instructor instructor2 = createInstructor("Mislav Nortic", "+385987654321", "09:00-17:00", Set.of(categoryA, categoryB));

        Driver driver1 = createDriver("Ana Sporost", "+38591111442", categoryB, 10, false, false);
        Driver driver2 = createDriver("Rajko Brzic", "+38591881442", categoryA, 20, true, false);

        createDrivingLesson(driver1, instructor1, LocalDateTime.now().plusDays(1).withHour(10),
                LocalDateTime.now().plusDays(1).withHour(11).withMinute(30), "Karlovac", "Pogazila covjeka i prosla na crveno");

        createExam(driver1, instructor1, LocalDateTime.now().plusDays(7), ExamType.THEORY, StatusType.FAILED);
        createExam(driver2, instructor2, LocalDateTime.now().minusDays(7), ExamType.THEORY, StatusType.PASSED);
        createExam(driver2, instructor2, LocalDateTime.now().minusDays(7), ExamType.PRACTICAL, StatusType.FAILED);

        createInstructorVehicle(instructor1, vehicle1);
    }

    private Category createCategory(String title, String description, int requiredHours, int minimalAge) {
        Category category = new Category();
        category.setTitle(title);
        category.setDescription(description);
        category.setRequiredHours(requiredHours);
        category.setMinimalAge(minimalAge);
        em.persist(category);
        return category;
    }

    private Vehicle createVehicle(String licencePlate, String model, int year) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicencePlate(licencePlate);
        vehicle.setModel(model);
        vehicle.setYear(year);
        em.persist(vehicle);
        return vehicle;
    }

    private Instructor createInstructor(String name, String phone, String availability, Set<Category> categories) {
        Instructor instructor = new Instructor();
        instructor.setName(name);
        instructor.setPhone(phone);
        instructor.setAvailability(availability);
        instructor.setCategories(categories);
        em.persist(instructor);
        return instructor;
    }

    private Driver createDriver(String name, String phone, Category category, int hoursDriven, boolean passedTheory, boolean passedPractical) {
        Driver driver = new Driver();
        driver.setName(name);
        driver.setPhone(phone);
        driver.setCategory(category);
        driver.setHoursDriven(hoursDriven);
        driver.setPassedTheoryTest(passedTheory);
        driver.setPassedPracticalTest(passedPractical);
        em.persist(driver);
        return driver;
    }

    private void createDrivingLesson(Driver driver, Instructor instructor, LocalDateTime start, LocalDateTime end, String location, String comment) {
        DrivingLesson lesson = new DrivingLesson();
        lesson.setStartTime(start);
        lesson.setEndTime(end);
        lesson.setLocation(location);
        lesson.setComment(comment);
        lesson.setDriver(driver);
        lesson.setInstructor(instructor);
        lesson.setStatus(StatusType.SCHEDULED);
        em.persist(lesson);
    }

    private void createExam(Driver driver, Instructor instructor, LocalDateTime date, ExamType type, StatusType status) {
        Exam exam = new Exam();
        exam.setDate(date);
        exam.setType(type);
        exam.setDriver(driver);
        exam.setInstructor(instructor);
        exam.setStatus(status);
        em.persist(exam);
    }

    private void createInstructorVehicle(Instructor instructor, Vehicle vehicle) {
        InstructorVehicle instructorVehicle = new InstructorVehicle();
        instructorVehicle.setInstructor(instructor);
        instructorVehicle.setVehicle(vehicle);
        em.persist(instructorVehicle);
    }
}
