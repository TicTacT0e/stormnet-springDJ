package app.resources;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.token.OAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Component
@Path("/login")
public class LoginController {

    //id
    //741125894809-j3tls88pr4u1lmadblb85rv086ecnsvp.apps.googleusercontent.com

    //secret
    //8UL-DCcDlQD1jLjh2tehN-42

    @Context
    UriInfo uriInfo;

    @GET
    @Path("/google")
    @Produces("text/html")
    public Response authenticate() {
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
        } catch (OAuthSystemException e) {
            throw new WebApplicationException(e);
        } catch (URISyntaxException e) {
            throw new WebApplicationException(e);
        }
    }

    private static String authorizationRequestBaseUri
            = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls
            = new HashMap<>();

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @GET
    @Path("/oauth_login")
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    public String getLoginPage(Model model) {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }

        clientRegistrations.forEach(registration ->
                oauth2AuthenticationUrls.put(registration.getClientName(),
                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        return "oauth_login";
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "hello";
    }
}
