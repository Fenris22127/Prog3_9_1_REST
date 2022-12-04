package de.medieninformatik.server.REMOVE;

import de.medieninformatik.server.REMOVE.Student;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Path("studenten")
public class StudentRest {
    private static Student dummy;
    private static Map<Integer, Student> studenten;

    static {
        dummy = new Student();
        studenten = new ConcurrentHashMap<>();
        Student s = new Student(1, "Harry");
        studenten.put(s.getId(), s);
        s = new Student(2, "Ron");
        studenten.put(s.getId(), s);
        s = new Student(3, "Hermione");
        studenten.put(s.getId(), s);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public static Response getAllStudentsAsText() {
        StringBuilder stringBuilder = new StringBuilder();
        studenten.forEach((k, s) -> stringBuilder.append(s).append("\n"));
        return Response.ok(stringBuilder.toString()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getStudentAsText(@PathParam("id") int id) {
        System.out.println("GET " + id);
        Student s = studenten.getOrDefault(id, dummy);
        return Response.ok(s.toString()).build();
    }

    @GET
    @Path("check")
    @Produces(MediaType.TEXT_PLAIN)
    public Response studentExists(@QueryParam("id") int id) {
        System.out.println("GET " + id);
        boolean exists = studenten.containsKey(id);
        return Response.ok(exists).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response putStudentsByID(@PathParam("id") int id, String student) {
        String[] s = student.split(": ");
        Student stud = new Student(Integer.parseInt(s[0]), s[1]);
        Response response;
        if (studenten.containsKey(id)) {
            studenten.put(id, stud);
            response = Response.noContent().status(Response.Status.OK).build();
        }
        else {
            response = Response.noContent().status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }
    @POST
    @Consumes (MediaType.TEXT_PLAIN)
    public Response newStudent (String newStudent, @Context UriInfo uriInfo) {
        String[] s = newStudent.split(": ");
        Student stud = new Student(Integer.parseInt(s[0]), s[1]);
        System.out.print("POST: " + stud);
        Integer id = stud.getId();
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(s[0]);
        Student p = studenten.put(id, stud);
        return Response.created(uriBuilder.build()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent (@PathParam("id") int id) {
        Response response;
        if( studenten . containsKey (id)) {
            studenten .remove(id);
            response = Response.noContent().status(Response.Status.OK).build();
        }
        else {
            response = Response.noContent().status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }
}
