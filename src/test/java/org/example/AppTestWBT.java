package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.example.domain.Tema;
import org.example.repository.NotaXMLRepo;
import org.example.repository.StudentXMLRepo;
import org.example.repository.TemaXMLRepo;
import org.example.service.Service;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.example.validation.ValidationException;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTestWBT {
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
    public void test_WBT_NoTC_1() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        int nrOfAssignmentsBefore = 0;
        for (Tema tema : service.getAllTeme()) nrOfAssignmentsBefore++;
        try{
            Tema newAssignment = new Tema("", "temaDesc", 6, 5);

            service.addTema(newAssignment);
        }
        catch (ValidationException e){
        }
        int nrofAssignmentsAfter = 0;
        for (Tema tema : service.getAllTeme()) nrofAssignmentsAfter++;
        assertEquals(nrofAssignmentsAfter, nrOfAssignmentsBefore);
    }

    @Test
    public void test_WBT_NoTC_2() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        int nrOfAssignmentsBefore = 0;
        for (Tema tema : service.getAllTeme()) nrOfAssignmentsBefore++;
        try{
            Tema newAssignment = new Tema("id2", "", 6, 5);
            service.addTema(newAssignment);
        }
        catch (ValidationException e){
        }
        int nrofAssignmentsAfter = 0;
        for (Tema tema : service.getAllTeme()) nrofAssignmentsAfter++;
        assertEquals(nrofAssignmentsAfter, nrOfAssignmentsBefore);
    }

    @Test
    public void test_WBT_NoTC_3() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        int nrOfAssignmentsBefore = 0;
        for (Tema tema : service.getAllTeme()) nrOfAssignmentsBefore++;
        try{
            Tema newAssignment = new Tema("id3", "temaDesc", 20, 5);
            service.addTema(newAssignment);
        }
        catch (ValidationException e){
        }
        int nrofAssignmentsAfter = 0;
        for (Tema tema : service.getAllTeme()) nrofAssignmentsAfter++;
        assertEquals(nrofAssignmentsAfter, nrOfAssignmentsBefore);
    }

    @Test
    public void test_WBT_NoTC_4() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        int nrOfAssignmentsBefore = 0;
        for (Tema tema : service.getAllTeme()) nrOfAssignmentsBefore++;
        try{
            Tema newAssignment = new Tema("id4", "temaDesc", 6, 20);
            service.addTema(newAssignment);
        }
        catch (ValidationException e){
        }
        int nrofAssignmentsAfter = 0;
        for (Tema tema : service.getAllTeme()) nrofAssignmentsAfter++;
        assertEquals(nrofAssignmentsAfter, nrOfAssignmentsBefore);
    }

    @Test
    public void test_WBT_NoTC_5() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrOfAssignmentsBefore = 0;
        for (Tema tema : service.getAllTeme()) nrOfAssignmentsBefore++;

        Tema newAssignment = new Tema("id5", "temaDesc", 6, 5);
        service.addTema(newAssignment);

        int nrofAssignmentsAfter = 0;
        for (Tema tema : service.getAllTeme()) nrofAssignmentsAfter++;

        assertEquals(nrofAssignmentsAfter, nrOfAssignmentsBefore + 1);

        service.deleteTema("id5");

        nrofAssignmentsAfter = 0;
        for (Tema tema : service.getAllTeme()) nrofAssignmentsAfter++;

        assertEquals(nrofAssignmentsAfter, nrOfAssignmentsBefore);
    }


}