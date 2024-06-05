package com.cursos.api.authorizationserver.persistence.entity.security;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClientApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId; //identifiacion a traves del clientId
    private String clientSecret; //contraseña del cliente

    //Esta anotacion crea una tabla llamda clientAuthenticationMethods y realiza un arelacion OneToMany entre esa tabla
    //Sin embargo cuando se desea realizar una consulta SQL por ejemeplo para obtener la entidad Cliente y al mismo
    //registros relacionados se agrega fetch = FetchType.EAGER
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> clientAuthenticationMethods;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorizationGrantTypes;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> redirectUris; //a esta variable se nos mandará el authorization code

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> scopes;

    private int durationInMinutes;
    private boolean requiredProofKey;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public List<String> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }
    public void setClientAuthenticationMethods(List<String> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    public List<String> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }
    public void setAuthorizationGrantTypes(List<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public List<String> getScopes() {
        return scopes;
    }
    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public List<String> getRedirectUris() {
        return redirectUris;
    }
    public void setRedirectUris(List<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }
    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public boolean isRequiredProofKey() {
        return requiredProofKey;
    }
    public void setRequiredProofKey(boolean requiredProofKey) {
        this.requiredProofKey = requiredProofKey;
    }
}
