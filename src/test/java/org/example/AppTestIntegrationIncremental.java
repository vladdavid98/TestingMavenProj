package org.example;

import org.example.domain.Nota;
import org.example.domain.Student;
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

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class AppTestIntegrationIncremental {
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();

    String filenameStudent = "fisiere/StudentiTest.xml";
    String filenameTema = "fisiere/TemeTest.xml";
    String filenameNota = "fisiere/NoteTest.xml";
    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

    @Test
    public void test_integration_addStudent() {
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
    public void test_integration_addTema() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        int nrOfTemeBefore = 0;
        for (Tema tema : service.getAllTeme()) nrOfTemeBefore++;
        try {
            Student newStudent = new Student("studentId1", "newName", 930, "new@scs.ubbcluj.ro");
            service.addStudent(newStudent);

            Tema newAssignment = new Tema("temaId1", "temaDesc", 9, 8);
            service.addTema(newAssignment);

        } catch (ValidationException e) {
            e.printStackTrace();
        }

        int nrOfTeme = 0;
        for (Tema tema : service.getAllTeme()) nrOfTeme++;

        service.deleteStudent("studentId1");
        service.deleteTema("temaId1");

        assertEquals(nrOfTeme, nrOfTemeBefore + 1);
    }

    @Test
    public void test_integration_addNota() {
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        int nrNoteBefore = 0;
        for (Nota nota : service.getAllNote()) {
            nrNoteBefore++;
        }
        try {
            Student newStudent = new Student("studentId1", "newName", 930, "new@scs.ubbcluj.ro");
            service.addStudent(newStudent);

            Tema newAssignment = new Tema("temaId1", "temaDesc", 10, 10);
            service.addTema(newAssignment);

            Nota newNota = new Nota("notaId1", "studentId1", "temaId1", 10, LocalDate.now());
            service.addNota(newNota, "no feedback");

        } catch (ValidationException e) {
            e.printStackTrace();
        }

        int nrNoteAfter = 0;
        for (Nota nota : service.getAllNote()) {
            nrNoteAfter++;
        }

        service.deleteStudent("studentId1");
        service.deleteTema("temaId1");
        service.deleteNota("notaId1");

        assertEquals(nrNoteBefore + 1, nrNoteAfter);


    }
}
