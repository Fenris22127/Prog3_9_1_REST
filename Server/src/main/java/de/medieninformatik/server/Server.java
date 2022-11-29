package de.medieninformatik.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Logger.getLogger ("org.glassfish").setLevel(Level.ALL);

        // listen on every available network interface
        URI baseUri = new URI("http://localhost:8080/rest");
        ResourceConfig config = ResourceConfig.forApplicationClass (StudentApplication.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        // Optional: Einbindung statischer Webseiten:
        StaticHttpHandler handler = new StaticHttpHandler("web");
        handler.setFileCacheEnabled(false);
        ServerConfiguration serverConfig = server.getServerConfiguration();
        serverConfig.addHttpHandler(handler, "/");

        if(!server.isStarted()) server.start();
        System.out.println("http://localhost:8080/rest/");
        System.out.println("ENTER stoppt den Server");
        System.in.read();
        server.shutdownNow();
    }
}
