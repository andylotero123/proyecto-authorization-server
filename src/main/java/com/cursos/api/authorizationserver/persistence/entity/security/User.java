package com.cursos.api.authorizationserver.persistence.entity.security;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "\"user\"")
/*NOTA: user es una palabra reservada en varias vaces de datos, por lo que la tabla
        no se llamará user, sino literalmente "user" y esta es una forma válida
 */
public class User implements UserDetails { //implementa este UserDeails, porque recibe como parametro el username, que es buscado en base de datos para hacer comparaciones de user y password

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //estrategia de autogeneración de Id
    private Long id;

    @Column(unique = true)
    private String username;
    
    private String name;
    private String password;
    
    @ManyToOne //hace referencia a que muchos usuarios van a tener asociado un mismo rol
    @JoinColumn(name = "role_id") //llave foranea que esta en la tabla User como un campo
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        //implemento el metodo get authority, entoces convierto sus permisos en una coleccion de objeto que implemente GrantedAuthority
        if(role == null) return  null;
        if(role.getPermissions() == null) return null; //si los permisos del rol son null, retorno null
        
        //obtengo los permisosm de role, luego los convierto en string de datos y se mapea ese string de datos a los objetos GrantedAuthority
        //NOTA: en la clase 45: ya no retorno si no lo guardo en una lista por motivo de que uso en la clase HttpSecurityConfig el metodo
        //      HasAnyRole con sus roles para corregir el error del prefijo ROLE_ y retorno un authority
        
        List<SimpleGrantedAuthority> authorities = role.getPermissions().stream() //saco la numeracion de los roles, porque cada getPermissions es una enumeración
                .map(each -> each.getOperation().getName())//para obtener el nombre de la operacion
                .map(each -> new SimpleGrantedAuthority(each))
        //        .map(each -> {
        //           String permission = each.name();
        //          return new SimpleGrantedAuthority(permission);
        //        })
                .collect(Collectors.toList());//luego convierto ese permiso de SimpleGrantedAuthority lo convierto a una lista
        //NOTA: hay otra forma de hacerlo, pero por ahora lo dejare asi
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.getName()));
        return authorities;
        /*NOTA: hasRole llama a hasAuthority pero le concatena al inicio ROLE_ nos da como resultado ROLE_ADMINISTRATOR por ende el
                SimpleGranteAuthority se debe de crear como "ROLE_" + this.role
        */
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public boolean isAccountNonExpired() { //esta cuenta no está expirada: true
        return true; //esta cuenta no esta expirada?
    }

    @Override
    public boolean isAccountNonLocked() {//esta cuenta no esta bloqueada
        return true; //esta cuenta no está bloqueada?
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;//las credenciales no estan expiradas?
    }

    @Override
    public boolean isEnabled() {
        return true; //este usuario está habilitado?
    }

    //agrego metodos accesores

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    

    public void setUsername(String username){
        this.username = username;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public Role getRole(){
        return role;
    }    
    public void setRole(Role role){
        this.role = role;
    }
}
