package app.resources;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@Path("/login")
public class LoginController {

    //id
    //741125894809-j3tls88pr4u1lmadblb85rv086ecnsvp.apps.googleusercontent.com

    //secret
    //8UL-DCcDlQD1jLjh2tehN-42


    //facebook
    //382308039060983

    //secret
    //9142e1cc1afe5a21c4c9c2c497fac3dc

    @Context
    UriInfo uriInfo;

    @GET
    @Path("/google")
    @Produces("text/html")
    public Response authenticateGoogle() {
        try {
            OAuthClientRequest request = OAuthClientRequest
                    .authorizationProvider(OAuthProviderType.GOOGLE)
                    .setClientId("741125894809-j3tls88pr4u1lmadblb85rv086ecnsvp.apps.googleusercontent.com")
                    .setResponseType("code")
                    .setScope("https://www.googleapis.com/auth/plus.login")
                    .setRedirectURI(
                            UriBuilder.fromUri(uriInfo.getBaseUri())
                                    .path("oauth2callback").build().toString())
                    .buildQueryMessage();
            URI redirect = new URI(request.getLocationUri());
            return Response.seeOther(redirect).build();
        } catch (OAuthSystemException | URISyntaxException e) {
            throw new WebApplicationException(e);
        }
    }

    @GET
    @Path("/facebook")
    @Produces("text/html")
    public Response authenticateFacebook() {
        try {
            OAuthClientRequest request = OAuthClientRequest
                    .authorizationProvider(OAuthProviderType.FACEBOOK)
                    .setClientId("151640435578187")
                    .setRedirectURI(
                            UriBuilder.fromUri(uriInfo.getBaseUri())
                                    .path("oauth2callback").build().toString())
                    .buildQueryMessage();
            URI redirect = new URI(request.getLocationUri());
            return Response.seeOther(redirect).build();
        } catch (OAuthSystemException | URISyntaxException e) {
            throw new WebApplicationException(e);
        }
    }
}
