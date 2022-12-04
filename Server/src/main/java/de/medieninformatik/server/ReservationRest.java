package de.medieninformatik.server;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.logging.Logger;
import java.util.stream.IntStream;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

/**
 * TODO:
 * @author Elisa Johanna Woelk (m30192)
 */
@Path("reservations")
public class ReservationRest {

    /**
     * TODO:
     */
    private final Logger logger = Logger.getLogger(ReservationRest.class.getName());

    /**
     * TODO:
     */
    private static Reservation[][] seats;

    /**
     * TODO:
     */
    private static final int ROWS = 10;

    /**
     * TODO:
     */
    private static final int COLUMNS = 20;

    static {
        seats =
                IntStream.range(0, ROWS) //rows (x)
                        .mapToObj(x -> IntStream.range(0, COLUMNS) //columns (y)
                                .mapToObj(y -> new Reservation("", false))
                                .toArray(Reservation[]::new))
                        .toArray(Reservation[][]::new);
    }

    /**
     * TODO:
     * @return
     */
    @GET
    @Path("/seats")
    @Produces(MediaType.TEXT_PLAIN)
    public static Response getAllSeats() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (seats[row][col].isBooked()) {
                    stringBuilder.append("[X] ");
                }
                else {
                    stringBuilder.append("[ ] ");
                }
            }
            stringBuilder.append(System.lineSeparator());
        }
        return Response.ok(stringBuilder.toString()).build();
    }

    /**
     * TODO:
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public static Response getAllReservations() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                stringBuilder.append(seats[row][col].toString()).append(System.lineSeparator());
            }
        }
        return Response.ok(stringBuilder.toString()).build();
    }

    /**
     * TODO:
     * @param row
     * @param col
     * @return
     */
    @GET
    @Path("{row}/{col}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getReservation(
            @PathParam("row") int row,
            @PathParam("col") int col) {
        logger.log(INFO, "Getting Reservation for Seat in row {0} column {1}", new Object[] {row, col});
        Reservation r = seats[row][col];
        return Response.ok(r.toString()).build();
    }

    /**
     * TODO:
     * @param row
     * @param col
     * @return
     */
    @GET
    @Path("/check")
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkReservation(
            @QueryParam("row") int row,
            @QueryParam("column") int col) {
        boolean exists = seats[row][col].isBooked();
        return Response.ok(exists).build();
    }

    /**
     * TODO:
     * @param row
     * @param col
     * @param name
     * @return
     */
    @PUT
    @Path("{row}/{col}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response makeReservation(
            @PathParam("row") int row,
            @PathParam("col") int col,
            String name) {
        Response response;
        try {
            if (!seats[row][col].isBooked()) {
                seats[row][col].setName(name);
                seats[row][col].setBooked(true);
                logger.log(INFO, "Created new Reservation under {0} for seat {1}-{2}", new Object[]{name, row, col});
                response = Response.noContent().status(Response.Status.OK).build();
            } else {
                logger.log(INFO, "Seat {0}-{1} is already booked under {2}", new Object[]{row, col, seats[row][col].getName()});
                response = Response.noContent().status(Response.Status.CONFLICT).build();
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            logger.log(WARNING, "Seat {0}-{1} is not a valid seat!", new Object[]{row, col});
            response = Response.noContent().status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }

    /**
     * TODO:
     * @param row
     * @param col
     * @return
     */
    @DELETE
    @Path("{row}/{col}")
    public Response deleteReservation(
            @PathParam("row") int row,
            @PathParam("col") int col) {
        Response response;
        try {
            if (seats[row][col].isBooked()) {
                seats[row][col].setBooked(false);
                seats[row][col].setName("");
                logger.log(INFO, "Reservation for seat {0}-{1} under {2} was deleted!",
                        new Object[]{row, col, seats[row][col].getName()});
                response = Response.noContent().status(Response.Status.OK).build();
            } else {
                logger.log(WARNING, "Reservation for seat {0}-{1} could not be deleted, seat is not booked!",
                        new Object[]{row, col});
                response = Response.noContent().status(Response.Status.NOT_FOUND).build();
            }
        }
        catch(ArrayIndexOutOfBoundsException e) {
            logger.log(WARNING, "Seat {0}-{1} is not a valid seat!", new Object[]{row, col});
            response = Response.noContent().status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }
}
