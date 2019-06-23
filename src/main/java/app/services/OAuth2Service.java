package app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OAuth2Service {

    @Value("${client.id.google}")
    private String clientIdGoogle;
    @Value("${client.secret.google}")
    private String clientSecretGoogle;
    @Value("${client.id.facebook}")
    private String clientIdFacebook;
    @Value("${client.secret.facebook}")
    private String clientSecretFacebook;

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

    public String getClientIdFacebook() {
        return clientIdFacebook;
    }

    public void setClientIdFacebook(String clientIdFacebook) {
        this.clientIdFacebook = clientIdFacebook;
    }

    public String getClientSecretFacebook() {
        return clientSecretFacebook;
    }

    public void setClientSecretFacebook(String clientSecretFacebook) {
        this.clientSecretFacebook = clientSecretFacebook;
    }
}
