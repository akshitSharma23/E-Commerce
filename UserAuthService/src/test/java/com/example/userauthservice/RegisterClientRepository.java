package com.example.userauthservice;

import com.example.userauthservice.Security.Repositories.AuthorizationConsentRepository;
import com.example.userauthservice.Security.Repositories.ClientRepository;
import com.example.userauthservice.Security.Repositories.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.test.annotation.Commit;

import java.util.UUID;

@SpringBootTest
public class RegisterClientRepository {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JpaRegisteredClientRepository jpaRegisteredClientRepository;

    @Test
    @Commit
    public void  insertClient() {
            RegisteredClient postmanClient = RegisteredClient.withId(UUID.randomUUID().toString())
                    .clientId("postman")
                    .clientSecret(passwordEncoder.encode("postmanPassword"))
                    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                    .redirectUri("https://oauth.pstmn.io/v1/callback")
                    .postLogoutRedirectUri("http://127.0.0.1:9000/")
                    .scope(OidcScopes.OPENID)
                    .scope(OidcScopes.PROFILE)
                    .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                    .build();
            jpaRegisteredClientRepository.save(postmanClient);
        }
}
