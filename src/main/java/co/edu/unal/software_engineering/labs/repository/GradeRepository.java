package co.edu.unal.software_engineering.labs.repository;

import co.edu.unal.software_engineering.labs.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer>{

}
