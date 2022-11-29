package de.medieninformatik.server;

import de.medieninformatik.client.Student;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Map;
import java.util.stream.IntStream;

@Path("reservations")
public class ReservationRest {
    private static Map<Integer, Student> studenten;
    private final Reservation dummy = new Reservation("", false);
    private static Reservation[][] seats;
    private static final int ROWS = 10;
    private static final int COLUMNS = 20;

    static {
        seats =
                IntStream.range(0, ROWS) //rows (x)
                        .mapToObj(x -> IntStream.range(0, COLUMNS) //columns (y)
                                .mapToObj(y -> new Reservation("", false))
                                .toArray(Reservation[]::new))
                        .toArray(Reservation[][]::new);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public static Response getAllSeatsAsText() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                stringBuilder.append(seats[row][col].toString()).append(System.lineSeparator());
            }
        }
        return Response.ok(stringBuilder.toString()).build();
    }

    @GET
    @Path("{seat}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getReservationAsText(@PathParam("seat") String seatNR) {
        System.out.println("GET " + seatNR);
        String[] split = seatNR.split("-");
        Reservation r = seats[Integer.parseInt(split[0])][Integer.parseInt(split[1])];
        return Response.ok(r.toString()).build();
    }

    @PUT
    @Path("{seat}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response putReservationBySeat(@PathParam("seat") String seatNR, String student) {
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
    @Path("{seat}")
    public Response deleteStudent (@PathParam("seat") String seatNR) {
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

    public static int[] getSeat(String s) {
        int[] seat = new int[2];
        return seat;
    }
}
