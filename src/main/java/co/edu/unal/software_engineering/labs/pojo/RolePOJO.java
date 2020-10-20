package co.edu.unal.software_engineering.labs.pojo;

import co.edu.unal.software_engineering.labs.model.Role;

public class RolePOJO{

    private final Integer id;
    private final String roleName;


    public RolePOJO( Role role ){
        if( role == null ){
            throw new NullPointerException("Role cannot be null");
        }
        this.id = role.getId( );
        this.roleName = role.getRoleName( );
    }


    public Integer getId( ){
        return id;
    }

    public String getRoleName( ){
        return roleName;
    }
}
