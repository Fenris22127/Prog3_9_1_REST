package de.medieninformatik.server;

public class Reservation {
    private String name;
    private boolean booked;

    public Reservation(String name, boolean booked) {
        this.name = name;
        this.booked = booked;
    }

    @Override
    public String toString() {
        String n = (name == null || name.isBlank() ? "" : String.format(" (%s)", name));
        String b = (booked ? "Booked" : "Free");
        return String.format("%s%s", b, n);
    }
}
