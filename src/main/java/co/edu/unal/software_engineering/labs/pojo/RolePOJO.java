package co.edu.unal.software_engineering.labs.pojo;

import java.util.Objects;

import co.edu.unal.software_engineering.labs.model.Role;

public class RolePOJO {
  private final Integer id;
  private final String name;




  public RolePOJO(Role role) {
    if(role == null){
      throw new NullPointerException("Role cannot be null");
    }
    this.id = role.getId();
    this.name = role.getRoleName();
  }

  public RolePOJO(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return this.id;
  }


  public String getName() {
    return this.name;
  }


  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RolePOJO)) {
            return false;
        }
        RolePOJO rolePOJO = (RolePOJO) o;
        return Objects.equals(id, rolePOJO.id) && Objects.equals(name, rolePOJO.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      "}";
  }

}
