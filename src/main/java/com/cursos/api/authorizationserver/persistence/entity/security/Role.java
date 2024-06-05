
package com.cursos.api.authorizationserver.persistence.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @JsonIgnore//se coloca esta anotacion para evitar la serializacion infinita
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER) //un rol tiene varios permisos asociados
    private List<GrantedPermission> permissions; //lista de permisos, es decir que dentro GrantedPermission hay un atributo llamado role con el fin de mapear correctamente @OneToMany

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GrantedPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<GrantedPermission> permissions) {
        this.permissions = permissions;
    }  
}
