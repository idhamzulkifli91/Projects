package introsde.rest.model;

import introsde.rest.dao.LifeCoachDao;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the "Measure" database table.
 * 
 */
@Entity
@Table(name="\"Measure\"")
@NamedQuery(name="Measure.findAll", query="SELECT m FROM Measure m")
@XmlRootElement
public class Measure implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="\"Date\"", nullable=false, length=2000000000)
	private String date;

	@Id
	@GeneratedValue(generator="sqlite_measure")
	@TableGenerator(name="sqlite_measure", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Measure")
	@Column(name="\"ID\"",nullable=false)
	
	private int id;
	
	
	@Column(name="\"Value\"", nullable=false, precision=10, scale=10)
	private double value;
	
	
	@XmlTransient
	@ManyToOne(fetch=FetchType.LAZY,cascade= CascadeType.ALL)
	@JoinColumn(name="PID")
	@JsonIgnore
	private Person person;
	
	//@JsonIgnore
	public MeasureType getMeasureType() {
		return measureType;
	}	

	
	@XmlElement
	@ManyToOne(fetch=FetchType.LAZY,cascade= CascadeType.ALL)
	@JoinColumn(name="MT")	
	private MeasureType measureType;
	
	@XmlTransient
	@ManyToOne(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="HPID")
	@JsonIgnore
	private HealthProfile healthProfile;
	
	
	
	@XmlTransient
	@JsonIgnore
	public HealthProfile getHealthProfile() {
		return healthProfile;
	}
	public void setHealthProfile(HealthProfile healthProfile) {		
			this.healthProfile = healthProfile;			
	}
	public Measure() {
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	
	public void setPerson(Person person) {
		this.person = person;
		/*setHealthProfile(person.getHealthProfile());
		person.addMeasure(this);	*/	
	}

	public void setMeasureType(MeasureType measureType) {
		this.measureType = measureType;
        //measureType.addMeasure(this);
		
	}
	@JsonIgnore
	public Person getPerson() {
		// TODO Auto-generated method stub
		return this.person;
	}
	public static Measure getMeasureById(int measureId) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        Measure p = em.find(Measure.class, measureId);
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }
	
    public static List<Measure> getAll() {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        List<Measure> list = em.createNamedQuery("Measure.findAll", Measure.class)
            .getResultList();
        LifeCoachDao.instance.closeConnections(em);
        return list;
    }

    public static Measure saveMeasure(Measure m) {    	
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(m);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
        return m;
    } 

    public static Measure updateMeasure(Measure m) {    	
        EntityManager em = LifeCoachDao.instance.createEntityManager(); 
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        m=em.merge(m);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
        return m;
    }

    public static void removeMeasure(Measure m) {
    
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        m=em.merge(m);
        em.remove(m);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
    }
}