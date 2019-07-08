package app.security;

import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;

public final class OAuth2GoogleCurrentClientInfo {

    private static String accessToken = null;
    private static OAuth2CodeGrantFlow flow;
    private static ClientIdentifier clientIdentifier;

    private OAuth2GoogleCurrentClientInfo() {
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        OAuth2GoogleCurrentClientInfo.accessToken = accessToken;
    }

    public static OAuth2CodeGrantFlow getFlow() {
        return flow;
    }

    public static void setFlow(OAuth2CodeGrantFlow flow) {
        OAuth2GoogleCurrentClientInfo.flow = flow;
    }

    public static ClientIdentifier getClientIdentifier() {
        return clientIdentifier;
    }

    public static void setClientIdentifier(ClientIdentifier clientIdentifier) {
        OAuth2GoogleCurrentClientInfo.clientIdentifier = clientIdentifier;
    }
}
