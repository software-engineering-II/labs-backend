package co.edu.unal.software_engineering.labs.repository;

import co.edu.unal.software_engineering.labs.model.Association;
import co.edu.unal.software_engineering.labs.model.Role;
import co.edu.unal.software_engineering.labs.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Integer>{

  @Query( "SELECT a FROM Association a JOIN a.userRole ur" +
          " WHERE ur.userRolePK.user = :user AND ur.userRolePK.role IN :roles")
  List<Association> findByUserAndRoles(User user, List<Role> roles);
  
}
