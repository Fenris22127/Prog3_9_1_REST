package de.medieninformatik.server;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static de.medieninformatik.server.REMOVE.StudentRest.getAllStudentsAsText;

class StudentRestTest {

    @Test
    void getAllStudentsAsTextTest() {
        Response response = getAllStudentsAsText();
        System.out.println(response);
    }

    @Test
    void getStudentAsTextTest() {
    }

    @Test
    void putStudentsByIDTest() {
    }

    @Test
    void newStudentTest() {
    }

    @Test
    void deleteStudentTest() {
    }
}