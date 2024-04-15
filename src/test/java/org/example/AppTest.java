package org.example;

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

import static org.junit.Assert.assertEquals;

public class AppTest {
    Service service;
    Tema tema;
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

//    @Test
//    void addStudentSuccessful() {
//        this.student = new Student("300", "nfaib", 100, "ewofohewf");
//        this.service.deleteStudent(this.student.getID());
//        assert this.service.addStudent(this.student) == null;
//    }
//
//    @Test
//    void addStudentIdEmptyFailure() {
//        this.student = new Student("", "nfaib", 100, "ewofohewf");
//        try {
//            this.service.addStudent(this.student);
//            assert false;
//        }
//        catch (Exception e) {
//
//        }
//    }
//
//    @Test
//    void addStudentIdNullFailure() {
//        this.student = new Student(null, "nfaib", 100, "ewofohewf");
//        try {
//            this.service.addStudent(this.student);
//            assert false;
//        }
//        catch (Exception e) {}
//    }
//
//    @Test
//    void addStudentNumeEmptyFailure() {
//        this.student = new Student("300", "", 100, "ewofohewf");
//        try {
//            this.service.addStudent(this.student);
//            assert false;
//        }
//        catch (Exception e) {}
//    }
//
//    @Test
//    void addStudentNumeNullFailure() {
//        this.student = new Student("300", null, 100, "ewofohewf");
//        try {
//            this.service.addStudent(this.student);
//            assert false;
//        }
//        catch (Exception e) {}
//    }
//
//    @Test
//    void addStudentGrupaLessThan0Failure() {
//        this.student = new Student("300", "nfaib", -1, "ewofohewf");
//        try {
//            this.service.addStudent(this.student);
//            assert false;
//        }
//        catch (Exception e) {}
//    }
//
//    @Test
//    void addStudentEmailEmptyFailure() {
//        this.student = new Student("300", "nfaib", 100, "");
//        try {
//            this.service.addStudent(this.student);
//            assert false;
//        }
//        catch (Exception e) {}
//    }
//
//    @Test
//    void addStudentEmailNullFailure() {
//        this.student = new Student("300", "nfaib", 100, null);
//        try {
//            this.service.addStudent(this.student);
//            assert false;
//        }
//        catch (Exception e) {}
//    }
    @Test
    void addAssignmentSuccessful() {
        this.tema = new Tema("1", "work work work", 10, 10);
        try {
            this.service.addTema(this.tema);
            assert true;
        }
        catch (Exception e) {}
    }

    @Test
    void addAssignmentAlreadyExists() {
        this.tema = new Tema("1", "file repository", 2, 1);
        try {
            Tema tm = this.service.addTema(this.tema);
            Assertions.assertEquals(tm, this.tema);
        }
        catch (Exception e) {}
    }

    @Test
    void addAssignmentDescriptionEmptyFailure() {
        this.tema = new Tema("1", "", 10, 10);
        try {
            this.service.addTema(this.tema);
            assert false;
        }
        catch (Exception e) {}
    }

    @Test
    void addAssignmentDeadlineInvalidFailure() {
        this.tema = new Tema("13", "desc", 0, 10);
        try {
            this.service.addTema(this.tema);
            assert false;
        }
        catch (Exception e) {}
    }

    @Test
    void addAssignmentPrimireInvalidFailure() {
        this.tema = new Tema("13", "desc", 10, 0);
        try {
            this.service.addTema(this.tema);
            assert false;
        }
        catch (Exception e) {}
    }
}
