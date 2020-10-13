package co.edu.unal.software_engineering.labs.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the grade database table.
 */
@Entity
@Table( name = "grade", schema = "public" )
public class Grade implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * Attributes
     */

    @Id
    @SequenceGenerator( name = "GRADE_GRADEID_GENERATOR",
            sequenceName = "public.grade_grade_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "GRADE_GRADEID_GENERATOR" )
    @Column( name = "grade_id" )
    private Integer id;

    private Boolean approved;

    private Float note;

    //bi-directional many-to-one association to Association
    @OneToOne
    @JoinColumn( name = "association_id" )
    private Association association;

    /**
     * Attributes
     */

    public Grade( ){
        // Default constructor is required
    }

    /**
     * Getters and Setters
     */

    public Integer getId( ){
        return this.id;
    }

    public void setId( Integer id ){
        this.id = id;
    }

    public Boolean getApproved( ){
        return this.approved;
    }

    public void setApproved( Boolean approved ){
        this.approved = approved;
    }

    public Float getNote( ){
        return this.note;
    }

    public void setNote( Float note ){
        this.note = note;
    }

    public Association getAssociation( ){
        return this.association;
    }

    public void setAssociation( Association association ){
        this.association = association;
    }

    /**
     * Attributes
     */

    @Override
    public boolean equals( Object object ){
        if( !(object instanceof Grade) ) return false;
        return id.equals( ((Grade) object).getId( ) );
    }

    @Override
    public int hashCode( ){
        return id;
    }
}