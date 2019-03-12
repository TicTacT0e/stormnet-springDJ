package app.service;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/test")
public class TestWeb {

    @GET
    public String message() {
        return "Hello";
    }
}
