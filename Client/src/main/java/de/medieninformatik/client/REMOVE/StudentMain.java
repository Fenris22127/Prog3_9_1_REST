package de.medieninformatik.client.REMOVE;

import de.medieninformatik.server.REMOVE.Student;

public class StudentMain {
    public static void main(String[] args) {
        final String BASE_URI = "http://localhost:8080/rest";

        StudentClient students = new StudentClient(BASE_URI);
        students.get("/studenten/1"); //holt Student mit ID 1
        students.get("/studenten");

        students.get("/studenten/check?id=1");

        // hinzufügen eines neuen Studenten mit POST
        students.post("/studenten", new Student(4, "Neville"));

        // Fehler: studenten/5 (Student mit ID 5) existiert nicht, daher kein PUT möglich
        students.put("/studenten/5", new Student(5,"Draco"));

        // Überschreiben eines bestehenden Studenten
        students.put("/studenten/2", new Student(2, "Ronald"));

        students.get("/studenten");
        students.delete("/studenten/4"); //löscht Student mit ID 4
        students.get("/studenten");
    }
}
