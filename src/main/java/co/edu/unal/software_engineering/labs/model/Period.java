package co.edu.unal.software_engineering.labs.model;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the period database table.
 */
@Entity
@Table( name = "period", schema = "public" )
public class Period implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * Attributes
     */

    @Id
    @SequenceGenerator( name = "PERIOD_PERIODID_GENERATOR",
            sequenceName = "public.period_period_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PERIOD_PERIODID_GENERATOR" )
    @Column( name = "period_id" )
    private Integer id;

    @Column( name = "period_name" )
    private String periodName;

    //bi-directional many-to-one association to Association
    @OneToMany( mappedBy = "period" )
    private List<Association> associations;

    /**
     * Contructors
     */

    public Period( ){
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

    public String getPeriodName( ){
        return this.periodName;
    }

    public void setPeriodName( String periodName ){
        this.periodName = periodName;
    }

    public List<Association> getAssociations( ){
        return this.associations;
    }

    public void setAssociations( List<Association> associations ){
        this.associations = associations;
    }

    /**
     * Methods
     */

    @Override
    public boolean equals( Object object ){
        if( !(object instanceof Period) ) return false;
        return id.equals( ((Period) object).getId( ) );
    }

    @Override
    public int hashCode( ){
        return id;
    }

}