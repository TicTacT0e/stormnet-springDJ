package app.resources;

import app.security.OAuth2Service;
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


    private String clientSecret = "8UL-DCcDlQD1jLjh2tehN-42";
    private String clientId = "741125894809-j3tls88pr4u1lmadblb85rv086ecnsvp.apps.googleusercontent.com";
    private String userAuthirizationUri = "https://accounts.google.com/o/oauth2/v2/auth";
    private String scope = "https://www.googleapis.com/auth/userinfo.email,https://www.googleapis.com/auth/userinfo.profile";
    private String accessTokenUri = "https://www.googleapis.com/oauth2/v4/token";
    private String tokenName = "authorization_code";
    private String oauth2FilterCallbackPath = "/oauth2/callback";
    private String userInfoUri = "https://www.googleapis.com/oauth2/v3/userinfo";

    private static final String BASE_URI = "/";

    @GET
    @Path("/login/google")
    @Produces("text/html")
    public Response googleAuthentication() {
        OAuth2Service.setClientIdentifier(new ClientIdentifier(
                oAuth2PropertyProvider.getClientIdGoogle(),
                oAuth2PropertyProvider.getClientSecretGoogle()
        ));
        final String redirectURI =
                UriBuilder.fromUri(uriInfo.getBaseUri())
                        .path("/oauth2/authorize").build().toString();

        final OAuth2CodeGrantFlow flow = OAuth2ClientSupport
                .googleFlowBuilder(
                        OAuth2Service.getClientIdentifier(),
                        redirectURI,
                        "https://www.googleapis.com/auth/plus.login"
                )
                .prompt(OAuth2FlowGoogleBuilder.Prompt.CONSENT).build();

        OAuth2Service.setFlow(flow);
        final String googleAuthURI = flow.start();

        return Response.seeOther(UriBuilder.fromUri(googleAuthURI).build()).build();
    }

    @GET
    @Path("/authorize")
    public Response authorize(
            @QueryParam("code") String code,
            @QueryParam("state") String state
    ) {
        final OAuth2CodeGrantFlow flow = OAuth2Service.getFlow();
        final TokenResult tokenResult = flow.finish(code, state);
        OAuth2Service.setAccessToken(tokenResult.getAccessToken());
        final String redirectUri =
                UriBuilder.fromUri(uriInfo.getBaseUri())
                .path(BASE_URI).build().toString();

        return Response.seeOther(UriBuilder.fromUri(redirectUri).build()).build();
    }


    @GET
    @Path("/secured")
    @Produces("text/html")
    public Response securedEndPoint() {
        return OAuth2Service.getAccessToken() == null ?
                Response.status(Response.Status.UNAUTHORIZED.getStatusCode()).build() :
                Response.ok("Secured end point").build();
    }

}
