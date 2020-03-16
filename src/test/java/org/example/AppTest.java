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
    public void test_addStudent_valid() {

        String filenameStudent = "fisiere/StudentiTest.xml";
        String filenameTema = "fisiere/TemeTest.xml";
        String filenameNota = "fisiere/NoteTest.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("newId1", "newStudentName1", 930, "newemail1@scs.ubbcluj.ro");
        service.addStudent(newStudent);

        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore + 1);

        service.deleteStudent("newId1");

        nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }

    @Test
    public void test_addStudent_invalid() {

        String filenameStudent = "fisiere/StudentiTest.xml";
        String filenameTema = "fisiere/TemeTest.xml";
        String filenameNota = "fisiere/NoteTest.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

//        service.deleteStudent("newId1");

        int nrofStudentsBefore = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsBefore++;

        Student newStudent = new Student("", "newStudentName1", 930, "newemail1@scs.ubbcluj.ro");
        try {
            service.addStudent(newStudent);
        }
        catch (ValidationException e){
            assertEquals(e.getMessage(),"Id incorect!");
        }


        int nrofStudentsAfter = 0;
        for (Student stud : service.getAllStudenti()) nrofStudentsAfter++;

        assertEquals(nrofStudentsAfter, nrofStudentsBefore);
    }
}
