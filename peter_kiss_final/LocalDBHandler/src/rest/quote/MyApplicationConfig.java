package rest.quote;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("QuoteAdapter")
public class MyApplicationConfig extends ResourceConfig {
    public MyApplicationConfig () {
        packages("rest.quote");
    }
}