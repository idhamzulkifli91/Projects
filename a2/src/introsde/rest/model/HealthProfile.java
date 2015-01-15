package introsde.rest.model;

import introsde.rest.dao.LifeCoachDao;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlTransformation;
import org.joda.time.chrono.ZonedChronology;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the "HealthProfile" database table.
 * 
 */
@Entity
@Table(name="\"HealthProfile\"")
@NamedQuery(name="HealthProfile.findAll", query="SELECT h FROM HealthProfile h")
@XmlRootElement
public class HealthProfile implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="sqlite_healthprofile")
	@TableGenerator(name="sqlite_healthprofile", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Measure")
	@Column(name="\"ID\"",nullable=false)
	
	private int id;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Person person;
	
	@OneToMany(mappedBy="healthProfile",cascade= CascadeType.ALL)
	@XmlElement	
	private List<Measure> measures;

	public List<Measure> getMeasures() {
		return measures;
	}

	public void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}
	
	public void addMeasure(Measure newm) {
		if(this.measures.size()==0){
			//newm.setHealthProfile(this);
			this.measures.add(newm);
		}
		Measure toDelete = null;
		for(Measure m : this.measures){
			if(m.getMeasureType().getDescription().equals(newm.getMeasureType().getDescription()))
			{
				toDelete = m;
				//System.out.println("****** insetring to HP ********");
			
				//System.out.println("****** added ********");
				
			}
		}
		if(toDelete!= null){
			toDelete.setHealthProfile(null);	
			this.measures.remove(toDelete);
		}
	
		this.measures.add(newm);	
			
		
	}
	

	public HealthProfile() {
	}

    public static HealthProfile getHealthProfileById(int hpId) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        HealthProfile p = em.find(HealthProfile.class, hpId);
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }
	
    public static List<HealthProfile> getAll() {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        List<HealthProfile> list = em.createNamedQuery("HealthProfile.findAll", HealthProfile.class)
            .getResultList();
        LifeCoachDao.instance.closeConnections(em);
        return list;
    }

    public static HealthProfile saveHealthProfile(HealthProfile hp) {
    
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
        tx.begin();
        em.persist(hp);
        tx.commit();
        }catch (PersistenceException ex) {
        	ex.printStackTrace();
        	System.out.println("*******************************************");
        }
        LifeCoachDao.instance.closeConnections(em);
        return hp;
    } 

    public static HealthProfile updateHealthProfile(HealthProfile hp) {    	
        EntityManager em = LifeCoachDao.instance.createEntityManager(); 
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        hp=em.merge(hp);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
        return hp;
    }

    public static void removeHealthProfile(HealthProfile hp) {    
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        hp=em.merge(hp);
        em.remove(hp);
        tx.commit();
        LifeCoachDao.instance.closeConnections(em);
    }

	public void setPerson(Person person) {
		this.person = person;
		List<Measure> ml = this.getMeasures();
		for(Measure m : ml)
			m.setPerson(this.getPerson());
		
	}
	@XmlTransient
	@JsonIgnore
	public Person getPerson( ) {
		 return this.person ;
		
	}

    
}