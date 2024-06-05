
package com.cursos.api.authorizationserver.persistence.entity.security;

import jakarta.persistence.*;

@Entity
public class GrantedPermission { 
    
    /*Nota: esta es una clase de rompimiento de relacion ManyToMany entre operacion y rol
    */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne //Muchos de estos permisos va a estar asignados a un mismo rol
    @JoinColumn(name = "role_id") //role_id es una llave foranea
    private Role role;
    
    @ManyToOne
    @JoinColumn(name = "operation_id") //llave foranea
    private Operation operation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
    
}
