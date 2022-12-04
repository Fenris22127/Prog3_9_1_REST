package de.medieninformatik.server;

/**
 * TODO:
 * @author Elisa Johanna Woelk (m30192)
 */
public interface IReservation {

    /**
     * TODO:
     */
    void getAllSeats();

    /**
     * TODO:
     */
    void getAllReservations();

    /**
     * TODO:
     * @param row
     * @param col
     */
    void getReservation(int row, int col);

    /**
     * TODO:
     * @param row
     * @param col
     */
    void hasReservation(int row, int col);

    /**
     * TODO:
     * @param row
     * @param col
     * @param name
     */
    void makeReservation(int row, int col, String name);

    /**
     * TODO:
     * @param row
     * @param col
     */
    void deleteReservation(int row, int col);
}
