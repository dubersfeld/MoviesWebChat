package com.dub.entities;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "actor")
public class Actor implements Serializable
{
	private static final long serialVersionUID = 1L;

	protected String firstName;
	
    protected String lastName;
	
	protected long id;
	
	protected Date birthDate;
	
    protected Set<Movie> movie;
    
    
    public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actorId")
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }


    @Column(name = "firstName")
    public String getFirstName()
    {
        return firstName;
    }
    
  
    @Column(name = "lastName")
    public String getLastName()
    {
        return lastName;
    }

    
    @Temporal(TemporalType.DATE)
	@Column(name = "birthDate")
    public Date getBirthDate()
    {
        return birthDate;
    }


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "actorFilm",
    		joinColumns = {@JoinColumn(name = "actorId", 
    				referencedColumnName = "actorId")},
    		inverseJoinColumns = {@JoinColumn(name="filmId",
    				referencedColumnName = "movieId")})
	public Set<Movie> getMovie() {
		return movie;
	}

	public void setMovie(Set<Movie> movie) {
		this.movie = movie;
	}
}
