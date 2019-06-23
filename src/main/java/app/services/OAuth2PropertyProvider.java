package app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OAuth2PropertyProvider {

    @Value("${client.id.google}")
    private String clientIdGoogle;
    @Value("${client.secret.google}")
    private String clientSecretGoogle;
    @Value("${google.scope}")
    private String googleScope;

    public String getClientIdGoogle() {
        return clientIdGoogle;
    }

    public void setClientIdGoogle(String clientIdGoogle) {
        this.clientIdGoogle = clientIdGoogle;
    }

    public String getClientSecretGoogle() {
        return clientSecretGoogle;
    }

    public void setClientSecretGoogle(String clientSecretGoogle) {
        this.clientSecretGoogle = clientSecretGoogle;
    }

    public String getGoogleScope() {
        return googleScope;
    }

    public void setGoogleScope(String googleScope) {
        this.googleScope = googleScope;
    }
}
