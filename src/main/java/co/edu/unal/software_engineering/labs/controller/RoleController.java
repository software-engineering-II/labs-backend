package co.edu.unal.software_engineering.labs.controller;

import co.edu.unal.software_engineering.labs.model.Role;
import co.edu.unal.software_engineering.labs.pojo.RolePOJO;
import co.edu.unal.software_engineering.labs.service.RoleService;
import co.edu.unal.software_engineering.labs.service.UserService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleController{

    private RoleService roleService;
    private UserService userService;


    public RoleController( RoleService roleService, UserService userService ){
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping( value = { "/roles" } )
    public List<Role> getAllRoles( ){
        return roleService.getAll( );
    }

    @GetMapping( "/mis-roles" )
    public List<RolePOJO> getUserRoles( ){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName(); // Guardo el username del usuario por medio del token
        List<RolePOJO> misRoles = new ArrayList<>( ); //Creo el arreglo en donde se guardaran la info de los roles
        RolePOJO POJO; //Creo un objeto de tipo pollo que me almacena la información del role antes de añadirla al arreglo MisRoles
        List<Role> rolesTemp = new ArrayList<>( ); //Creo que un arreglo de Roles que me guardara la información que sale del usuario  ya que esta es tiipo Role y no RolePOJO 
        rolesTemp = userService.findByUsername(username).getRoles(); //Aqui le asigno la info del usuario apartir de su username que se obtuvo con el token
        //El metodo getRoles() retorna una lista copn todos los roles asociados al usuario
        for(int i = 0; i < rolesTemp.size() ; i++){ // En este for voy a reccorer todo el arreglo rolesTemp, creo un RolePojo a partir de cada elemento y lo agrego al arreglo final
            POJO = new RolePOJO( rolesTemp.get(i));
            misRoles.add(POJO);
        }
        return misRoles;
}
}
