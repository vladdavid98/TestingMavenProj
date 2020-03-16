package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.example.domain.Student;
import org.example.repository.NotaXMLRepo;
import org.example.repository.StudentXMLRepo;
import org.example.repository.TemaXMLRepo;
import org.example.service.Service;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.example.validation.ValidationException;
import org.example.view.UI;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();

    String filenameStudent = "fisiere/StudentiTest.xml";
    String filenameTema = "fisiere/TemeTest.xml";
    String filenameNota = "fisiere/NoteTest.xml";
    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

    //StudentFileRepository studentFileRepository = new StudentFileRepository(filenameStudent);
    //TemaFileRepository temaFileRepository = new TemaFileRepository(filenameTema);
    //NotaValidator notaValidator = new NotaValidator(studentFileRepository, temaFileRepository);
    //NotaFileRepository notaFileRepository = new NotaFileRepository(filenameNota);


//    UI ui = new UI(service);
//        ui.run();


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void test_NoTC_1() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1", "newName", 930, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("id1");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_2() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("", "newName", 930, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (ValidationException e) {

        }

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_NoTC_3() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student newStudent = new Student("id1", "newName", 930, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        try {
            Student newStudent2 = new Student("id1", "newName", 930, "new@scs.ubbcluj.ro");
            service.addStudent(newStudent2);
        } catch (Exception e) {
        }

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);

        service.deleteStudent("id1");
        int nrofStudents = 0;
        for (Student stud : service.getAllStudenti()) nrofStudents++;

        assertEquals(nrofStudents, 0);
    }

    @Test
    public void test_NoTC_4() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1", "", 930, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
    @Test
    public void test_NoTC_5() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("","newName",930, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
    @Test
    public void test_NoTC_6() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student(" ","newName",930, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        service.deleteStudent(" ");

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
    @Test
    public void test_NoTC_7() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("abc","newName",930, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("abc");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
    @Test
    public void test_NoTC_8() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("123","newName",930, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("123");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
    @Test
    public void test_NoTC_9() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1","",930, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        service.deleteStudent("id1");

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
    @Test
    public void test_NoTC_10() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1"," ",930, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        service.deleteStudent("id1");

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
    @Test
    public void test_NoTC_11() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1","abc",930, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("id1");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
    @Test
    public void test_NoTC_12() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1","123",930, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("id1");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
    @Test
    public void test_NoTC_13() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1","newName",-1, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        service.deleteStudent("id1");

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
    @Test
    public void test_NoTC_14() {

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1","newName",0, "new@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        } catch (Exception e) {
        }
        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        service.deleteStudent("id1");

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
    @Test
    public void test_NoTC_15() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1","newName",1, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("id1");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
    @Test
    public void test_NoTC_16() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("id1","newName",999999999, "new@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("id1");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
}
