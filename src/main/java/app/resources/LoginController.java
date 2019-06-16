package app.resources;

import app.services.OAuth2Service;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Context
    private UriInfo uriInfo;

    @Autowired
    private OAuth2Service oAuth2Service;

    private static final String RESPONSE_TYPE = "code";
    private static final String GOOGLE_SCOPE
            = "https://www.googleapis.com/auth/plus.login";
    private static final String CALLBACK = "oauth2callback";

    @GET
    @Path("/google")
    @Produces("text/html")
    public Response authenticateGoogle() {
        try {
            OAuthClientRequest request = OAuthClientRequest
                    .authorizationProvider(OAuthProviderType.GOOGLE)
                    .setClientId(oAuth2Service.getClientIdGoogle())
                    .setResponseType(RESPONSE_TYPE)
                    .setScope(GOOGLE_SCOPE)
                    .setRedirectURI(
                            UriBuilder.fromUri(uriInfo.getBaseUri())
                                    .path(CALLBACK).build().toString())
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
                    .setClientId(oAuth2Service.getClientIdFacebook())
                    .setRedirectURI(
                            UriBuilder.fromUri(uriInfo.getBaseUri())
                                    .path(CALLBACK).build().toString())
                    .buildQueryMessage();
            URI redirect = new URI(request.getLocationUri());
            return Response.seeOther(redirect).build();
        } catch (OAuthSystemException | URISyntaxException e) {
            throw new WebApplicationException(e);
        }
    }
}
