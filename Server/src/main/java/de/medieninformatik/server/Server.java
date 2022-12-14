package de.medieninformatik.server;

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

/**
 * //TODO:
 * @author Elisa Johanna Woelk (m30192)
 */
public class Server {

    /**
     * TODO:
     */
    private static final Logger LOGGER = Logger.getLogger("org.glassfish");

    /**
     * TODO:
     * @param args
     * @throws URISyntaxException
     * @throws IOException
     */
    public static void main(String[] args) throws URISyntaxException, IOException {
        // listen on every available network interface
        URI baseUri = new URI("http://localhost:8080/rest"); //TODO:
        ResourceConfig config = ResourceConfig.forApplicationClass(ReservationApplication.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        StaticHttpHandler handler = new StaticHttpHandler("web");
        handler.setFileCacheEnabled(false);
        ServerConfiguration serverConfig = server.getServerConfiguration();
        serverConfig.addHttpHandler(handler, "/");

        if(!server.isStarted()) server.start();
        LOGGER.log(INFO, "http://localhost:8080/rest/");
        LOGGER.log(INFO, "Server started");
        LOGGER.log(INFO, "ENTER stops the Server");
        System.in.read();
        server.shutdownNow();
    }
}
