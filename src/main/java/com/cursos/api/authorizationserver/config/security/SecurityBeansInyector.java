package com.cursos.api.authorizationserver.config.security;

import com.cursos.api.authorizationserver.exception.ObjectNotFoundException;
import com.cursos.api.authorizationserver.persistence.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeansInyector {
    
    @Autowired
    private UserRepository userRepository;

    //el AuthenticationConfiguration se puede inyectar como parametro del metodo, ya que solo se va a usar para este metodo
   // @Bean
 //   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

   //     return authenticationConfiguration.getAuthenticationManager();
   // }

    //AuthenticationProvider que define la estrategia o proveedor de authentication
    @Bean
    public AuthenticationProvider authenticationProvider() { 
        //este objeto necesita un passwordEncoder, por lo que la contraseña en DB son encriptadas, ya que la contraseña del frontEnd viene sin encriptar
        //El passwordEncoder tiene un metodo match para saber si una contraseña coinside con otra contraseña, pero la segunda contraseña encriptada
        //metodo match: boolean matches(CharSequence rewPassword, String encoderPassword);
        //metodo match boolean matches(CharSequence contraseñaEnBruto(o sin encriptar), String contraseñaEncriptadaEnBaseDeDatos);
        DaoAuthenticationProvider authenticationStrategy = new DaoAuthenticationProvider();
        authenticationStrategy.setPasswordEncoder(passwordEncoder()); //llamo el metodo cuando se lo seteamos al DaoAutheticationProvider
        authenticationStrategy.setUserDetailsService(userDetailsService());
        
        return authenticationStrategy;
    }
    
    //Creo el PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        
        return new BCryptPasswordEncoder();
    }  
    
    //Nota: como UserDetailsService solo tiene un solo metodo, la idea no es crear una clase que implemente ese metodo del servicio UserDetailsService
    //      la solucionn que una expresuion landa se se implementa la interface UserDeailsService
    @Bean
    public UserDetailsService userDetailsService(){
        //el return recibe un String username para retornar el metodo
        return (username -> { //retorno el username desde la base de datos
            return userRepository.findByUsername(username) //retorna un Optional
                    .orElseThrow(() -> new ObjectNotFoundException("User not found with username " + username));
                    //Creo una excepcion  ObjectNotFoundException (creado anteriormente)con una expresion landa 
        });
    } 
}

