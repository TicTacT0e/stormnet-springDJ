package app.resources.oauth2;

import app.security.OAuth2GoogleCurrentClientInfo;
import app.services.OAuth2PropertyProvider;
import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;
import org.glassfish.jersey.client.oauth2.OAuth2FlowGoogleBuilder;
import org.glassfish.jersey.client.oauth2.TokenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Component
@Path("/oauth2")
public class OAuth2Resource {

    @Context
    private UriInfo uriInfo;
    @Autowired
    private OAuth2PropertyProvider oAuth2PropertyProvider;
    private static final String BASE_URI = "/";

    @GET
    @Path("/login/google")
    @Produces("text/html")
    public Response googleAuthentication() {
        OAuth2GoogleCurrentClientInfo.setClientIdentifier(new ClientIdentifier(
                oAuth2PropertyProvider.getClientIdGoogle(),
                oAuth2PropertyProvider.getClientSecretGoogle()
        ));
        final String redirectURI =
                UriBuilder.fromUri(uriInfo.getBaseUri())
                        .path("/oauth2/authorize").build().toString();

        final OAuth2CodeGrantFlow flow = OAuth2ClientSupport
                .googleFlowBuilder(
                        OAuth2GoogleCurrentClientInfo.getClientIdentifier(),
                        redirectURI,
                        oAuth2PropertyProvider.getGoogleScope()
                )
                .prompt(OAuth2FlowGoogleBuilder.Prompt.CONSENT).build();

        OAuth2GoogleCurrentClientInfo.setFlow(flow);
        final String googleAuthURI = flow.start();

        return Response.seeOther(UriBuilder
                .fromUri(googleAuthURI).build()).build();
    }

    @GET
    @Path("/authorize")
    public Response authorize(
            @QueryParam("code") String code,
            @QueryParam("state") String state
    ) {
        final OAuth2CodeGrantFlow flow
                = OAuth2GoogleCurrentClientInfo.getFlow();
        final TokenResult tokenResult = flow.finish(code, state);
        OAuth2GoogleCurrentClientInfo
                .setAccessToken(tokenResult.getAccessToken());
        final String redirectUri =
                UriBuilder.fromUri(uriInfo.getBaseUri())
                        .path(BASE_URI).build().toString();

        return Response.seeOther(UriBuilder
                .fromUri(redirectUri).build()).build();
    }

    @GET
    @Path("/secured")
    @Produces("text/html")
    public Response securedEndPoint() {
        return OAuth2GoogleCurrentClientInfo.getAccessToken() == null
                ? Response.status(Response.Status.UNAUTHORIZED
                        .getStatusCode()).build()
                : Response.ok("Secured end point").build();
    }

}
