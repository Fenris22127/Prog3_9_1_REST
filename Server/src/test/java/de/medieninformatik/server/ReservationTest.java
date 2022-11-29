package de.medieninformatik.server;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static de.medieninformatik.server.ReservationRest.getSeat;
import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void toStringTest() {
        Reservation resEmpty = new Reservation("", false);
        Reservation resBlank = new Reservation(" ", false);
        Reservation resName = new Reservation("Firstname Lastname", true);
        System.out.println("Empty Name: " + resEmpty);
        System.out.println("Blank Name: " + resBlank);
        System.out.println("Normal Name: " + resName);
    }

    @Test
    void getSeatTest() {
        String s = "1-2";
        int[] seatNr = new int[2];
        String[] split = s.split("-");
        seatNr[0] = Integer.parseInt(split[0]);
        seatNr[1] = Integer.parseInt(split[1]);
        System.out.println(Arrays.toString(split));
        System.out.println(Arrays.toString(seatNr));
    }
}