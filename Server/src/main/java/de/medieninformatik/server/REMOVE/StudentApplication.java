package de.medieninformatik.server.REMOVE;

import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

public class StudentApplication extends Application {
    private Set<Object> singletons = new HashSet<>();
    private Set<Class<?>> classes = new HashSet<>();

    public StudentApplication() {
        singletons.add(new StudentRest());
        classes.add(StudentRest.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
