package de.medieninformatik.server.REMOVE;

import de.medieninformatik.server.REMOVE.StudentApplication;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class StudentServer {

    private static final Logger LOGGER = Logger.getLogger("org.glassfish");

    public static void main(String[] args) throws IOException, URISyntaxException {
        // listen on every available network interface
        URI baseUri = new URI("http://localhost:8080/rest");
        ResourceConfig config = ResourceConfig.forApplicationClass(StudentApplication.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        // Optional: Einbindung statischer Webseiten:
        StaticHttpHandler handler = new StaticHttpHandler("web");
        handler.setFileCacheEnabled(false);
        ServerConfiguration serverConfig = server.getServerConfiguration();
        serverConfig.addHttpHandler(handler, "/");

        if(!server.isStarted()) server.start();
        LOGGER.log(INFO, "http://localhost:8080/rest/");
        LOGGER.log(INFO, "ENTER stoppt den Server");
        System.in.read();
        server.shutdownNow();
    }
}
