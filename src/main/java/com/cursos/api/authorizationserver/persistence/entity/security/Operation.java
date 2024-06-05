
package com.cursos.api.authorizationserver.persistence.entity.security;

import jakarta.persistence.*;


@Entity
public class Operation { //este clase contiene el nombre de la operacion
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name; //este name va contener por ejemplo READ_ALL_PRODUCTS 
    private String path; //este path va a contener la ruta secundaria, ejemplo: {productId}/disabled 
    private String HttpMethod; //va contener el tipo del metodo que usa el path, ej: POST, GET, PUT, DELETE
    private boolean permitAll; //este atributo el estado false o true con respecto si esta operacion es publica o no 
    
    //@ManyToOne //muchas operacion van a estar relacionadas a un mismo modulo
    //@JoinColumn(name = "module_id") //llave foranea
    //private Module module;

    @Column(name = "module_id")
    private long moduleId;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpMethod() {
        return HttpMethod;
    }

    public void setHttpMethod(String HttpMethod) {
        this.HttpMethod = HttpMethod;
    }

    public boolean isPermitAll() {
        return permitAll;
    }

    public void setPermitAll(boolean permitAll) {
        this.permitAll = permitAll;
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }
}
