package co.edu.unal.software_engineering.labs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the course database table.
 */
@Entity
@Table( name = "course", schema = "public" )
public class Course implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * Attributes
     */

    @Id
    @SequenceGenerator( name = "COURSE_COURSEID_GENERATOR",
            sequenceName = "public.course_course_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "COURSE_COURSEID_GENERATOR" )
    @Column( name = "course_id" )
    private Integer id;

    @Column( name = "course_name" )
    private String courseName;

    @Column( name = "duration_hours" )
    private Integer durationHours;

    //bi-directional many-to-one association to Association
    @OneToMany( mappedBy = "course" )
    private List<Association> associations;

    /**
     * Constructors
     */

    public Course( ){
        // Default constructor is required
    }

    /**
     * Getters and Setters
     */

    public Integer getId( ){
        return id;
    }

    public void setId( Integer id ){
        this.id = id;
    }

    public String getCourseName( ){
        return courseName;
    }

    public void setCourseName( String courseName ){
        this.courseName = courseName;
    }

    public Integer getDurationHours( ){
        return durationHours;
    }

    public void setDurationHours( Integer durationHours ){
        this.durationHours = durationHours;
    }

    public List<Association> getAssociations( ){
        return associations;
    }

    public void setAssociations( List<Association> associations ){
        this.associations = associations;
    }

    /**
     * Methods
     */

    @Override
    public boolean equals( Object object ){
        if( !(object instanceof Course) ) return false;
        return id.equals( ((Course) object).getId( ) );
    }

    @Override
    public int hashCode( ){
        return id;
    }
}