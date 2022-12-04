package de.medieninformatik.client;

/**
 * TODO:
 * @author Elisa Johanna Woelk (m30192)
 */
public class Main {

    /**
     * TODO:
     * @param args <-
     */
    public static void main(String[] args) {

        final String BASE_URI = "http://localhost:8080/rest";

        ReservationClient reservations = new ReservationClient(BASE_URI);

        //~ ✓
        reservations.getAllSeats(); //print all seats as either [ ] (free) or [X] (booked)

        //~ ✓
        reservations.getReservation(1, 2); //get Reservation for seat in row 1, column 2

        //~ ✓
        reservations.makeReservation(1, 2, "Me"); //book seat in row 1, column 2

        //~ ✓
        reservations.hasReservation(1, 2); //check, if seat in row 1, column 2 is booked

        //~ ✓
        reservations.getReservation(1, 2); //get reservation for booked seat in row 1, column 2

        //~ ✓
        reservations.getAllSeats(); //print all seats as either [ ] (free) or [X] (booked)

        //~ ✓
        reservations.deleteReservation(1, 2); //delete reservation for seat in row 1, column 2

        //~ ✓
        reservations.getReservation(1, 2); //check if reservation for seat in row 1, column 2 was deleted
    }
}
