package com.cursos.api.authorizationserver.mapper;

import com.cursos.api.authorizationserver.persistence.entity.security.ClientApp;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Collectors;

public class ClientAppMapper {

    public static RegisteredClient toRegisteredClient(ClientApp clientApp){

        RegisteredClient client = RegisteredClient.withId(clientApp.getClientId())
                .clientId(clientApp.getClientId())//client = clientId
                .clientSecret(clientApp.getClientSecret())
                .clientIdIssuedAt(new Date(System.currentTimeMillis()).toInstant())//recibe un objeto Instant en lugar de un Date
                .clientAuthenticationMethods(clientAuthMethods -> {
                    clientApp.getClientAuthenticationMethods().stream()
                            .map(method -> new ClientAuthenticationMethod(method))
                            //.forEach(each -> clientAuthMethods.add(each));
                            .forEach(clientAuthMethods::add);
                })
                .authorizationGrantTypes(authGrantTypes -> {
                    clientApp.getAuthorizationGrantTypes().stream()
                            .map(grantType -> new AuthorizationGrantType(grantType))
                            .forEach(authGrantTypes::add);
                })
                .redirectUris(redirectUris ->
                        clientApp.getRedirectUris().stream().forEach(redirectUris::add))
                .scopes(scopes -> clientApp.getScopes().stream().forEach(scopes::add))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(clientApp.getDurationInMinutes())) //Aquí se configura el Access Token para los clientes
                        .refreshTokenTimeToLive(Duration.ofMinutes(clientApp.getDurationInMinutes() * 4)) //Aqui se configura el tiempo del RefreshToken, en este caso 4 veces mayor al accessToken
                        .build())//se define el tiempo de token por cada cliente
                .clientSettings(ClientSettings.builder()
                        .requireProofKey(clientApp.isRequiredProofKey())
                        .build())//que tipo de flujo quiere seguir un cliente: authorization code o authorization code with PKCE
                .build();

        return client;
    }
}
