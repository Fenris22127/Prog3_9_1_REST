package de.medieninformatik.server;

import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO:
 * @author Elisa Johanna Woelk (m30192)
 */
public class ReservationApplication extends Application {

    /**
     * TODO:
     */
    private Set<Object> singletons = new HashSet<>();

    /**
     * TODO:
     */
    private Set<Class<?>> classes = new HashSet<>();

    /**
     * TODO:
     */
    public ReservationApplication() {
        singletons.add(new ReservationRest());
        classes.add(ReservationRest.class);
    }

    /**
     * TODO:
     * @return
     */
    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    /**
     * TODO:
     * @return
     */
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
