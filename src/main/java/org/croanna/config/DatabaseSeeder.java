package org.croanna.config;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.croanna.enums.ExamType;
import org.croanna.enums.RoleType;
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
        Category categoryAM = createCategory("AM", "Mopeds and motor cultivators", 15, 15);
        Category categoryA1 = createCategory("A1", "Motorcycles up to 125cc and 11kW", 30, 16);
        Category categoryA2 = createCategory("A2", "Motorcycles up to 35kW", 30, 18);
        Category categoryA = createCategory("A", "All motorcycles", 30, 24);
        Category categoryB = createCategory("B", "Personal vehicles up to 3500kg", 40, 18);
        Category categoryBE = createCategory("BE", "B category vehicles with trailers up to 3500kg", 20, 18);
        Category categoryC1 = createCategory("C1", "Trucks from 3500kg to 7500kg", 50, 18);
        Category categoryC1E = createCategory("C1E", "C1 category vehicles with trailers over 750kg", 50, 18);
        Category categoryC = createCategory("C", "Trucks over 7500kg", 50, 21);
        Category categoryCE = createCategory("CE", "C category vehicles with trailers over 750kg", 50, 21);
        Category categoryD1 = createCategory("D1", "Buses up to 16 passengers", 60, 21);
        Category categoryD1E = createCategory("D1E", "D1 category vehicles with trailers over 750kg", 60, 21);
        Category categoryD = createCategory("D", "Buses with more than 8 passengers", 60, 24);
        Category categoryDE = createCategory("DE", "D category vehicles with trailers over 750kg", 60, 24);
        Category categoryF = createCategory("F", "Tractors", 20, 16);
        Category categoryG = createCategory("G", "Work machines", 20, 16);
        Category categoryH = createCategory("H", "Trams", 30, 21);

        Vehicle vehicle1 = createVehicle("ZG122PP", "Audi A3", 2022);
        Vehicle vehicle2 = createVehicle("KA787SR", "BMW X6", 2020);
        Vehicle vehicle3 = createVehicle("KA123SR", "Audi A1", 2023);
        Vehicle vehicle4 = createVehicle("KA643BR", "Honda Civic", 2020);
        Vehicle vehicle5 = createVehicle("KA785SA", "BMW X5", 2020);
        Vehicle vehicle6 = createVehicle("KA784SA", "BMW X4", 2022);
        Vehicle vehicle7 = createVehicle("KA877IJ", "Renault Clio", 2019);
        Vehicle vehicle8 = createVehicle("KA786BA", "Honda CBR 600", 2021);
        Vehicle vehicle9 = createVehicle("ZG111TT", "Mercedes-Benz Actros", 2023);
        Vehicle vehicle10 = createVehicle("KA222TT", "Volvo FH16", 2022);
        Vehicle vehicle11 = createVehicle("KA333TT", "Scania R730", 2021);
        Vehicle vehicle12 = createVehicle("KA555TT", "MAN TGX", 2023);
        Vehicle vehicle13 = createVehicle("KA101BB", "MAN Lion's Coach", 2022);
        Vehicle vehicle14 = createVehicle("KA777BB", "Mercedes-Benz Tourismo", 2022);
        Vehicle vehicle15 = createVehicle("KA789MP", "Aprilia SR 50", 2019);

        Employee admin = createEmployee("admin", "admin", "admin", "+385123456", RoleType.ADMIN);

        Employee employee1 = createEmployee("Natalija Kokolic", "nkokolic", "12345678", "+38596381414", RoleType.EMPLOYEE);
        Employee employee2 = createEmployee("Mihaela Lakat", "mlakat", "12345678", "+38595544321", RoleType.EMPLOYEE);
        Employee employee3 = createEmployee("Arnold Suton", "asuton", "12345678", "+385984234321", RoleType.EMPLOYEE);
        Employee employee4 = createEmployee("Ivan Kolar", "ikolar", "12345678", "+385912345678", RoleType.EMPLOYEE);
        Employee employee5 = createEmployee("Lana Markovic", "lmarkovic", "12345678", "+385955667788", RoleType.EMPLOYEE);
        Employee employee6 = createEmployee("Petar Novak", "pnovak", "12345678", "+385917774411", RoleType.EMPLOYEE);
        Employee employee7 = createEmployee("Karlo Juric", "kjuric", "12345678", "+385917771122", RoleType.EMPLOYEE);
        Employee employee8 = createEmployee("Nikolina Kovac", "nkovac", "12345678", "+385955667799", RoleType.EMPLOYEE);
        Employee employee9 = createEmployee("Filip Bosnjak", "fbosnjak", "12345678", "+385912223355", RoleType.EMPLOYEE);
        Employee employee10 = createEmployee("Anja Radic", "aradic", "12345678", "+385919887766", RoleType.EMPLOYEE);
        Employee employee11 = createEmployee("Mateo Prgomet", "mprgomet", "12345678", "+385917654321", RoleType.EMPLOYEE);
        Employee employee12 = createEmployee("Valentina Jovic", "vjovic", "12345678", "+385955443322", RoleType.EMPLOYEE);

        Employee instructor1 = createEmployee("Stjepan Bukvic", "sbukvic", "12345678", "+385911441414", RoleType.INSTRUCTOR, "16:00-21:00", Set.of(categoryB));
        Employee instructor2 = createEmployee("Mislav Nortic", "mnortic", "12345678", "+385987654321", RoleType.INSTRUCTOR, "09:00-17:00", Set.of(categoryA, categoryB));
        Employee instructor3 = createEmployee("Laura Kokot", "lkokot", "12345678", "+38598122321", RoleType.INSTRUCTOR, "08:00-16:00", Set.of(categoryA, categoryB));
        Employee instructor4 = createEmployee("Goran Lovric", "glovric", "12345678", "+385998887766", RoleType.INSTRUCTOR, "12:00-20:00", Set.of(categoryC, categoryCE));
        Employee instructor5 = createEmployee("Sandra Peric", "speric", "12345678", "+385915554433", RoleType.INSTRUCTOR, "07:00-15:00", Set.of(categoryD, categoryDE));
        Employee instructor6 = createEmployee("Marko Zuban", "mzuban", "12345678", "+385919876543", RoleType.INSTRUCTOR, "10:00-18:00", Set.of(categoryA1, categoryA2, categoryA));
        Employee instructor7 = createEmployee("Dario Knezevic", "dknezevic", "12345678", "+385911234567", RoleType.INSTRUCTOR, "13:00-21:00", Set.of(categoryB, categoryBE));
        Employee instructor8 = createEmployee("Mirna Samardzic", "msamardzic", "12345678", "+385955667788", RoleType.INSTRUCTOR, "08:00-16:00", Set.of(categoryC, categoryCE));
        Employee instructor9 = createEmployee("Kristijan Pavic", "kpavic", "12345678", "+385919223344", RoleType.INSTRUCTOR, "10:00-18:00", Set.of(categoryD, categoryDE));
        Employee instructor10 = createEmployee("Petra Lovrek", "plovrek", "12345678", "+385912334455", RoleType.INSTRUCTOR, "14:00-22:00", Set.of(categoryA1, categoryA2, categoryA));
        Employee instructor11 = createEmployee("Marko Silovic", "msilovic", "12345678", "+385918765432", RoleType.INSTRUCTOR, "06:00-14:00", Set.of(categoryF, categoryG));
        Employee instructor12 = createEmployee("Tina Medak", "tmedak", "12345678", "+385914567890", RoleType.INSTRUCTOR, "12:00-20:00", Set.of(categoryD1, categoryD1E));

        Driver driver1 = createDriver("Ana Sporost", "+38591111442", categoryB, 10, false, false);
        Driver driver2 = createDriver("Rajko Brzic", "+38591881442", categoryA, 20, true, false);
        Driver driver3 = createDriver("Josip Hrast", "+385912223344", categoryC, 15, true, true);
        Driver driver4 = createDriver("Martina Babic", "+385919998877", categoryD, 12, false, true);
        Driver driver5 = createDriver("Tomislav Kovac", "+385914433221", categoryCE, 25, true, false);
        Driver driver6 = createDriver("Ema Stanic", "+385917774455", categoryBE, 8, false, false);
        Driver driver7 = createDriver("Ivan Zoric", "+385911223344", categoryC, 18, true, false);
        Driver driver8 = createDriver("Luka Tomasic", "+385955667700", categoryCE, 22, true, true);
        Driver driver9 = createDriver("Nina Petrak", "+385917771199", categoryD, 16, false, true);
        Driver driver10 = createDriver("Matej Vlasic", "+385912345678", categoryDE, 20, true, false);
        Driver driver11 = createDriver("Andrea Milic", "+385918887766", categoryB, 5, false, false);
        Driver driver12 = createDriver("Stjepan Orlic", "+385919876543", categoryBE, 7, false, false);
        Driver driver13 = createDriver("Sara Novak", "+385914433221", categoryD1, 12, false, true);
        Driver driver14 = createDriver("Davor Stanic", "+385917774455", categoryD1E, 14, true, true);
        Driver driver15 = createDriver("Marija Bakaric", "+385911112233", categoryF, 9, false, false);
        Driver driver16 = createDriver("Damir Rados", "+385918765432", categoryG, 6, false, false);
        Driver driver17 = createDriver("Tihomir Glavas", "+385912223399", categoryC, 20, true, false);
        Driver driver18 = createDriver("Ema Horvat", "+385955667722", categoryD, 15, false, true);
        Driver driver19 = createDriver("Josip Medved", "+385917774488", categoryCE, 25, true, true);
        Driver driver20 = createDriver("Ivana Jakovljevic", "+385919998811", categoryB, 8, false, false);
        Driver driver21 = createDriver("Filip Vidovic", "+385911114455", categoryA, 12, false, false);
        Driver driver22 = createDriver("Mateja Blazevic", "+385918887733", categoryD1, 14, false, true);
        Driver driver23 = createDriver("Stipe Pavlic", "+385917665544", categoryD1E, 18, true, true);
        Driver driver24 = createDriver("Anita Perkovic", "+385919876612", categoryBE, 6, false, false);
        Driver driver25 = createDriver("Zlatko Martinovic", "+385914433999", categoryF, 10, false, false);
        Driver driver26 = createDriver("Tamara Radic", "+385918765711", categoryG, 5, false, false);

        createDrivingLesson(driver1, instructor1, LocalDateTime.now().plusDays(1).withHour(10),
                LocalDateTime.now().plusDays(1).withHour(11).withMinute(30), "Karlovac", "Pogazila čovjeka i prošla na crveno");
        createDrivingLesson(driver2, instructor2, LocalDateTime.now().plusDays(1).withHour(10),
                LocalDateTime.now().plusDays(1).withHour(10).withMinute(10), "Duga Resa", "Uspješno odrađen sat vožnje");
        createDrivingLesson(driver2, instructor2, LocalDateTime.now().plusDays(1).withHour(10),
                LocalDateTime.now().plusDays(2).withHour(8).withMinute(15), "Karlovac", "Noćna vožnja");
        createDrivingLesson(driver2, instructor2, LocalDateTime.now().plusDays(1).withHour(10),
                LocalDateTime.now().plusDays(3).withHour(8).withMinute(15), "Karlovac", "Bočno parkiranje");
        createDrivingLesson(driver3, instructor3, LocalDateTime.now().plusDays(2).withHour(9),
                LocalDateTime.now().plusDays(2).withHour(10).withMinute(30), "Karlovac", "Prvi sat vožnje, učenje osnovnih manevra");
        createDrivingLesson(driver4, instructor4, LocalDateTime.now().plusDays(3).withHour(14),
                LocalDateTime.now().plusDays(3).withHour(15).withMinute(30), "Duga Resa", "Vožnja u gradskoj gužvi");
        createDrivingLesson(driver5, instructor5, LocalDateTime.now().plusDays(5).withHour(12),
                LocalDateTime.now().plusDays(5).withHour(13).withMinute(30), "Karlovac", "Autocesta i brzo kretanje");
        createDrivingLesson(driver6, instructor6, LocalDateTime.now().plusDays(7).withHour(16),
                LocalDateTime.now().plusDays(7).withHour(17).withMinute(30), "Duga Resa", "Noćna vožnja, kratka i duga svjetla");
        createDrivingLesson(driver7, instructor7, LocalDateTime.now().plusDays(1).withHour(8),
                LocalDateTime.now().plusDays(1).withHour(9).withMinute(30), "Karlovac", "Kružni tok i semafori");
        createDrivingLesson(driver8, instructor8, LocalDateTime.now().plusDays(4).withHour(10),
                LocalDateTime.now().plusDays(4).withHour(11).withMinute(30), "Duga Resa", "Naglo kočenje i izbjegavanje prepreka");
        createDrivingLesson(driver9, instructor9, LocalDateTime.now().plusDays(6).withHour(13),
                LocalDateTime.now().plusDays(6).withHour(14).withMinute(30), "Karlovac", "Vožnja po kiši, prilagođavanje uvjetima");
        createDrivingLesson(driver10, instructor10, LocalDateTime.now().plusDays(8).withHour(15),
                LocalDateTime.now().plusDays(8).withHour(16).withMinute(30), "Duga Resa", "Vožnja uzbrdo i nizbrdo");
        createDrivingLesson(driver11, instructor11, LocalDateTime.now().plusDays(9).withHour(7),
                LocalDateTime.now().plusDays(9).withHour(8).withMinute(30), "Karlovac", "Ubrzavanje i kočenje sa prikolicom");
        createDrivingLesson(driver12, instructor12, LocalDateTime.now().plusDays(10).withHour(11),
                LocalDateTime.now().plusDays(10).withHour(12).withMinute(30), "Duga Resa", "Završna proba prije ispita");

        createExam(driver1, instructor1, LocalDateTime.now().plusDays(7), ExamType.THEORY, StatusType.FAILED);
        createExam(driver2, instructor2, LocalDateTime.now().minusDays(7), ExamType.THEORY, StatusType.PASSED);
        createExam(driver2, instructor2, LocalDateTime.now().minusDays(7), ExamType.PRACTICAL, StatusType.FAILED);
        createExam(driver3, instructor3, LocalDateTime.now().plusDays(10), ExamType.THEORY, StatusType.SCHEDULED);
        createExam(driver4, instructor4, LocalDateTime.now().plusDays(14), ExamType.PRACTICAL, StatusType.SCHEDULED);
        createExam(driver5, instructor5, LocalDateTime.now().minusDays(3), ExamType.THEORY, StatusType.PASSED);
        createExam(driver6, instructor6, LocalDateTime.now().minusDays(1), ExamType.PRACTICAL, StatusType.FAILED);
        createExam(driver7, instructor7, LocalDateTime.now().plusDays(5), ExamType.THEORY, StatusType.SCHEDULED);
        createExam(driver8, instructor8, LocalDateTime.now().minusDays(7), ExamType.PRACTICAL, StatusType.PASSED);
        createExam(driver9, instructor9, LocalDateTime.now().plusDays(12), ExamType.THEORY, StatusType.SCHEDULED);
        createExam(driver10, instructor10, LocalDateTime.now().plusDays(15), ExamType.PRACTICAL, StatusType.SCHEDULED);
        createExam(driver11, instructor11, LocalDateTime.now().minusDays(10), ExamType.THEORY, StatusType.FAILED);
        createExam(driver12, instructor12, LocalDateTime.now().plusDays(3), ExamType.PRACTICAL, StatusType.SCHEDULED);

        createInstructorVehicle(instructor1, vehicle1);
        createInstructorVehicle(instructor2, vehicle2);
        createInstructorVehicle(instructor3, vehicle3);
        createInstructorVehicle(instructor4, vehicle4);
        createInstructorVehicle(instructor5, vehicle5);
        createInstructorVehicle(instructor6, vehicle6);
        createInstructorVehicle(instructor7, vehicle7);
        createInstructorVehicle(instructor8, vehicle8);
        createInstructorVehicle(instructor9, vehicle9);
        createInstructorVehicle(instructor10, vehicle10);
        createInstructorVehicle(instructor11, vehicle11);
        createInstructorVehicle(instructor12, vehicle12);
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

    private Employee createEmployee(String name, String username, String password, String phone, RoleType role) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setPhone(phone);
        employee.setRole(role);
        employee.setUsername(username);
        employee.setPassword(BcryptUtil.bcryptHash(password));
        em.persist(employee);
        return employee;
    }

    private Employee createEmployee(String name, String username, String password, String phone, RoleType role, String availability, Set<Category> categories) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setPhone(phone);
        employee.setRole(role);
        employee.setUsername(username);
        employee.setPassword(BcryptUtil.bcryptHash(password));
        employee.setAvailability(availability);
        employee.setCategories(categories);
        em.persist(employee);
        return employee;
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

    private void createDrivingLesson(Driver driver, Employee instructor, LocalDateTime start, LocalDateTime end, String location, String comment) {
        DrivingLesson lesson = new DrivingLesson();
        lesson.setStartTime(start);
        lesson.setEndTime(end);
        lesson.setLocation(location);
        lesson.setComment(comment);
        lesson.setDriver(driver);
        lesson.setEmployee(instructor);
        lesson.setStatus(StatusType.SCHEDULED);
        em.persist(lesson);
    }

    private void createExam(Driver driver, Employee instructor, LocalDateTime date, ExamType type, StatusType status) {
        Exam exam = new Exam();
        exam.setDate(date);
        exam.setType(type);
        exam.setDriver(driver);
        exam.setEmployee(instructor);
        exam.setStatus(status);
        em.persist(exam);
    }

    private void createInstructorVehicle(Employee instructor, Vehicle vehicle) {
        EmployeeVehicle instructorVehicle = new EmployeeVehicle();
        instructorVehicle.setEmployee(instructor);
        instructorVehicle.setVehicle(vehicle);
        em.persist(instructorVehicle);
    }
}
