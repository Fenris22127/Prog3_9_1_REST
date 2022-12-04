package de.medieninformatik.server;

/**
 * TODO:
 * @author Elisa Johanna Woelk (m30192)
 */
public class Reservation {

    /**
     * TODO:
     */
    private String name;

    /**
     * TODO:
     */
    private boolean booked;

    /**
     * TODO:
     * @param name
     * @param booked
     */
    public Reservation(String name, boolean booked) {
        this.name = name;
        this.booked = booked;
    }

    /**
     * TODO:
     * @return
     */
    @Override
    public String toString() {
        String n = (name == null || name.isBlank() ? "" : String.format(" (%s)", name));
        String b = (booked ? "Booked" : "Free");
        return String.format("%s%s", b, n);
    }

    /**
     * TODO:
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * TODO:
     * @return
     */
    public boolean isBooked() {
        return booked;
    }

    /**
     * TODO:
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * TODO:
     * @param booked
     */
    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
