
package com.cursos.api.authorizationserver.persistence.entity.security;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Module {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;  //este atributo contiene por ejemplo: Product, Category, Auth, Customer 
    private String basePath; //contiene el path base, dependiendo el tipo de modulo, por ejemplo: products, categories ...

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

    public String getBasePath() {
        return basePath;
    }
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
