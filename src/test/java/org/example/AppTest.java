package org.example;

import org.example.domain.Student;
import org.example.repository.NotaXMLRepo;
import org.example.repository.StudentXMLRepo;
import org.example.repository.TemaXMLRepo;
import org.example.service.Service;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {
    Service service;
    Student student;
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
        this.service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    void addStudentSuccessful() {
        this.student = new Student("300", "nfaib", 100, "ewofohewf");
        assert this.service.addStudent(this.student) == null;
    }

    @Test
    void addStudentIdEmptyFailure() {
        this.student = new Student("", "nfaib", 100, "ewofohewf");
        try {
            this.service.addStudent(this.student);
            assert false;
        }
        catch (Exception e) {

        }
    }

    @Test
    void addStudentIdNullFailure() {
        this.student = new Student(null, "nfaib", 100, "ewofohewf");
        try {
            this.service.addStudent(this.student);
            assert false;
        }
        catch (Exception e) {}
    }

    @Test
    void addStudentNumeEmptyFailure() {
        this.student = new Student("300", "", 100, "ewofohewf");
        try {
            this.service.addStudent(this.student);
            assert false;
        }
        catch (Exception e) {}
    }

    @Test
    void addStudentNumeNullFailure() {
        this.student = new Student("300", null, 100, "ewofohewf");
        try {
            this.service.addStudent(this.student);
            assert false;
        }
        catch (Exception e) {}
    }

    @Test
    void addStudentGrupaLessThan0Failure() {
        this.student = new Student("300", "nfaib", -1, "ewofohewf");
        try {
            this.service.addStudent(this.student);
            assert false;
        }
        catch (Exception e) {}
    }

    @Test
    void addStudentEmailEmptyFailure() {
        this.student = new Student("300", "nfaib", 100, "");
        try {
            this.service.addStudent(this.student);
            assert false;
        }
        catch (Exception e) {}
    }

    @Test
    void addStudentEmailNullFailure() {
        this.student = new Student("300", "nfaib", 100, null);
        try {
            this.service.addStudent(this.student);
            assert false;
        }
        catch (Exception e) {}
    }
}
