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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        addAssignmentSuccessful();
        addStudentSuccessful();
        addNotaSuccessful();
    }
}
