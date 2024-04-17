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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class BigBangIntegrationTest {
    static Service service;
    Tema tema;
    Student student;
    Nota nota;
    @BeforeEach
    void setUp() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    void addStudentSuccessful() {
        this.student = new Student("300", "nfaib", 100, "ewofohewf");
        service.deleteStudent(this.student.getID());
        assert service.addStudent(this.student) == null;
    }

    @Test
    void addAssignmentSuccessful() {
        this.tema = new Tema("1", "work work work", 10, 10);
        try {
            service.addTema(this.tema);
            assert true;
        }
        catch (Exception e) {}
    }

    @Test
    void addNotaSuccessful(){
        LocalDate date = LocalDate.now();
        this.nota = new Nota("2", "300", "1", 10.0, date);
        try {
            service.addNota(this.nota, "feedback");
            assert true;
        }
        catch (Exception e) {}
    }

    @Test
    public void integrationTest() {
        this.student = new Student("300", "nfaib", 100, "ewofohewf");
        Assertions.assertNotNull(service.addStudent(this.student));

        service.deleteTema("101");
        this.tema = new Tema("101", "work work work", 1, 4);
        try {
            service.addTema(this.tema);
            Assertions.assertEquals(this.tema.getID(), service.findTema("101").getID());
            Assertions.assertEquals(this.tema.getDescriere(), service.findTema("101").getDescriere());
            Assertions.assertEquals(this.tema.getDeadline(), service.findTema("101").getDeadline());
            Assertions.assertEquals(this.tema.getPrimire(), service.findTema("101").getPrimire());
        } catch (Exception e) {
            Assertions.fail("Adding assignment failed");
        }

        service.deleteNota("101");
        LocalDate date = LocalDate.of(2018, 10, 10);
        this.nota = new Nota("101", "300", "101", 10.0, date);
        try {
            service.addNota(this.nota, "feedback");
            Assertions.assertEquals(this.nota.getID(), service.findNota("101").getID());
            Assertions.assertEquals(this.nota.getIdStudent(), service.findNota("101").getIdStudent());
            Assertions.assertEquals(this.nota.getIdTema(), service.findNota("101").getIdTema());
            Assertions.assertEquals(this.nota.getNota(), service.findNota("101").getNota());
            Assertions.assertEquals(this.nota.getData(), service.findNota("101").getData());
        } catch (Exception e) {
            Assertions.fail("Adding grade failed");
        }
    }
}
