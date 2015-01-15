package introsde.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import introsde.rest.dao.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlTransformation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * The persistent class for the "Person" database table.
 * 
 */
@Entity
@Table(name="\"Person\"")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="\"DateOfBirth\"", nullable=false, length=2000000000)
	private String dateOfBirth;
	
	@Column(name="\"FirstName\"", nullable=false, length=2000000000)
	private String firstName;
	
	@Column(name="\"LastName\"", nullable=false, length=2000000000)
	private String lastName;

	@Id
	@GeneratedValue(generator="sqlite_person")
	@TableGenerator(name="sqlite_person", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Person")
	@Column(name="\"PersonID\"", nullable=false)
	
	private int personID;

	//bi-directional one-to-one association to HealthProfile
	
	@OneToOne(fetch=FetchType.LAZY,cascade= CascadeType.ALL)	
	@JoinColumn(name="HPID", referencedColumnName="ID", nullable=false)
	private HealthProfile healthProfile;

	//bi-directional many-to-one association to Measure
	@JsonIgnore
	@XmlTransformation
	@OneToMany(mappedBy="person")
	private List<Measure> measures;

	public Person() {
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPersonID() {
		return this.personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public HealthProfile getHealthProfile() {
		return this.healthProfile;
	}

	public void setHealthProfile(HealthProfile healthProfile) {
		this.healthProfile = healthProfile;
		//healthProfile.setPerson(this);
	}
	@JsonIgnore
	@XmlTransient
	public List<Measure> getMeasures() {
		return this.measures;
	}

	public void setMeasures(List<Measure> measures) {
		if(this.measures==null)
			this.measures = measures;
		else
			this.measures.addAll(measures);
	}
	public void addMeasure(Measure measure) {
		if(this.measures==null)
			this.measures=new ArrayList<Measure>();
		if(!this.measures.contains(measure))
			this.measures.add(measure);
		
      /*  if (measure.getPerson() != this) {
        	measure.setPerson(this);
        }*/
        
	}

	

	public Measure removeMeasure(Measure measure) {
		getMeasures().remove(measure);
		measure.setPerson(null);

		return measure;
	}
	
	public static Person getPersonById(int personId) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        Person p = em.find(Person.class, personId);
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }
	
    public static List<Person> getAll() {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        List<Person> list = em.createNamedQuery("Person.findAll", Person.class)
            .getResultList();
        LifeCoachDao.instance.closeConnections(em);
        return list;
    }

    public static Person savePerson(Person p) {    	
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(p);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
        return p;
    } 

    public static Person updatePerson(Person p) {
    	//HealthProfile.updateHealthProfile(p.getHealthProfile());
        EntityManager em = LifeCoachDao.instance.createEntityManager(); 
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p=em.merge(p);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }

    public static void removePerson(Person p) {
    	if(p.getHealthProfile()!=null)
    		HealthProfile.removeHealthProfile(p.getHealthProfile());
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p=em.merge(p);
        em.remove(p);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
    }
    


}
