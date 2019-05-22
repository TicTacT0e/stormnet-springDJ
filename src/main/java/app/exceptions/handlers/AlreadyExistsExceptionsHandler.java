package app.exceptions.handlers;

import app.exceptions.EntityAlreadyExistsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AlreadyExistsExceptionsHandler
        implements ExceptionMapper<EntityAlreadyExistsException> {

    @Override
    public Response toResponse(EntityAlreadyExistsException exception) {
        return Response
                .status(Response.Status.CONFLICT.getStatusCode()).build();
    }
}
