
package com.cursos.api.authorizationserver.persistence.entity.security;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class JwtToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 2048)
    private String token;
    
    private Date expiration;
    private boolean isValid;
    
    @ManyToOne //muchos token pueden pertenecer a un usuario
    @JoinColumn(name = "user_id")//llave foranea que esta la tabla jwtToken
    private User user;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiration() {
        return expiration;
    }
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public boolean isIsValid() {
        return isValid;
    }
    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }    
}
