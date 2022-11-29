package de.medieninformatik.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class StudentClient {
    private final Client client;
    private final String baseURI;

    public StudentClient (String uri) {
        this.baseURI = uri;
        this.client = ClientBuilder.newClient();
    }

    public void get(String uri) {
        WebTarget target = getTarget("GET", uri);
        Response response = target.request().accept(MediaType.TEXT_PLAIN).get();
        if (status(response) == 200) {
            String student = response.readEntity(String.class);
            System.out.println(student);
        }
    }

    public void post(String uri , Student student) {
        WebTarget target = getTarget("POST", uri);
        String stud = student.getId() + ": " + student.getName();
        Entity<String>entity = Entity.entity(stud, MediaType.TEXT_PLAIN);
        Response response = target.request().post(entity);
        if (status(response) == 201) {
            String location = response.getLocation().toString();
            System.out.println("Location: " + location );
        }
    }

    public void put(String uri, Student student) {
        WebTarget target = getTarget("PUT", uri);
        String stud = student.getId() + ": " + student.getName();
        Entity<String> entity = Entity.entity(stud, MediaType.TEXT_PLAIN );
        Response response = target.request().put(entity);
        status(response);
    }

    public void delete(String uri) {
        WebTarget target = getTarget("DELETE", uri);
        Response response = target.request().delete();
        status(response);
    }

    private WebTarget getTarget(String crud , String uri) {
        System.out.printf("%n--- %s %s%s%n", crud, baseURI, uri);
        return client.target(baseURI + uri);
    }

    private int status(Response response) {
        int code = response.getStatus();
        String reason = response.getStatusInfo().getReasonPhrase();
        System.out.printf("Status: %d %s%n", code, reason);
        return code;
    }
}
