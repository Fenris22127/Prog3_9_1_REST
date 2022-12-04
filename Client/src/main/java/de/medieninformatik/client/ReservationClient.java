package de.medieninformatik.client;

import de.medieninformatik.server.IReservation;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * TODO:
 * @author Elisa Johanna Woelk (m30192)
 */
public class ReservationClient implements IReservation {

    /**
     * TODO:
     */
    private final Client client;

    /**
     * TODO:
     */
    private final String baseURI;

    /**
     * TODO:
     */
    private static final String URI = "/reservations";

    /**
     * TODO:
     */
    private static final String URI_FORMAT = "%s/%d/%d";

    /**
     * TODO:
     * @param uri
     */
    public ReservationClient(String uri) {
        this.baseURI = uri;
        this.client = ClientBuilder.newClient();
    }

    /**
     * ✓ <i>Successfully prints all seats as either [ ] (free) or [X] (booked)</i> <br>
     * TODO:
     */
    @Override
    public void getAllSeats() {
        WebTarget target = getTarget("GET", String.format("%s/seats", URI));
        Response response = target.request().accept(MediaType.TEXT_PLAIN).get();
        if (status(response) == 200) {
            String reservation = response.readEntity(String.class);
            System.out.println(reservation);
        }
    }

    /**
     * TODO:
     */
    @Override
    public void getAllReservations() {
        WebTarget target = getTarget("GET", URI);
        Response response = target.request().accept(MediaType.TEXT_PLAIN).get();
        if (status(response) == 200) {
            String reservation = response.readEntity(String.class);
            System.out.println(reservation);
        }
    }

    /**
     * ✓ <i>Successfully returns booking status</i>
     * TODO:
     * @param row The row of the seat
     * @param col The column of the seat
     * @return
     */
    @Override
    public void getReservation(int row, int col) {
        WebTarget target = getTarget("GET", String.format(URI_FORMAT, URI, row, col));
        Response response = target.request().accept(MediaType.TEXT_PLAIN).get();
        if (status(response) == 200) {
            String reservation = response.readEntity(String.class);
            System.out.printf("Seat %d-%d is: %s%n", row, col, reservation);
        }
    }

    /**
     * TODO:
     * @param row
     * @param col
     */
    @Override
    public void hasReservation(int row, int col) {
        WebTarget target = getTarget("GET", String.format("%s/check?row=%d&column=%d", URI, row, col));
        Response response = target.request().accept(MediaType.TEXT_PLAIN).get();
        if (status(response) == 200) {
            String reservation = response.readEntity(String.class);
            String ans = (reservation.matches("true") ? "" : " not");
            System.out.printf("Seat %d-%d is%s booked.%n", row, col, ans);
        }
    }

    /**
     * TODO:
     * @param row
     * @param col
     * @param name
     */
    @Override
    public void makeReservation(int row, int col, String name) {
        WebTarget target = getTarget("PUT", String.format(URI_FORMAT, URI, row, col));
        Entity<String> entity = Entity.entity(name, MediaType.TEXT_PLAIN);
        Response response = target.request().put(entity);
        if (status(response) == 200) {
            System.out.printf("Seat %d-%d is booked under %s.%n", row, col, name);
        }
    }

    /**
     * TODO:
     * @param row
     * @param col
     */
    @Override
    public void deleteReservation(int row, int col) {
        WebTarget target = getTarget("DELETE", String.format(URI_FORMAT, URI, row, col));
        Response response = target.request().delete();
        status(response);
    }

    /**
     * TODO:
     * @param annotation
     * @param uri
     * @return
     */
    private WebTarget getTarget(String annotation , String uri) {
        //Ausgabe: --- [POST/PUT/...] [Base-Path][Specific Path]
        System.out.printf("%n--- %s %s%s%n", annotation, baseURI, uri);
        return client.target(baseURI + uri);
    }

    /**
     * TODO:
     * @param response
     * @return
     */
    private int status(Response response) {
        int code = response.getStatus();
        String reason = response.getStatusInfo().getReasonPhrase();
        System.out.printf("Status: %d %s%n", code, reason);
        return code;
    }
}
