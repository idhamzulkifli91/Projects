package introsde.document.model;

//import introsde.rest.model.Measure;
//import introsde.rest.model.Person;

//import introsde.rest.model.Measure;
import introsde.document.model.Person;
import introsde.document.dao.LifeCoachDao;



import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

//import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the "HealthProfile" database table.
 * 
 */
@Entity
@Table(name="\"HealthProfile\"")
@NamedQuery(name="HealthProfile.findAll", query="SELECT h FROM HealthProfile h")
//@XmlRootElement
public class HealthProfile implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="sqlite_healthprofile")
	@TableGenerator(name="sqlite_healthprofile", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="HealthProfile")
	@Column(name="\"Id\"")
	private int id;

	//private int pid;
	
	
	
	@OneToMany(mappedBy="healthProfile",cascade= CascadeType.ALL)
	@XmlElement(name = "measure")
	private List<LifeStatus> measureList;
	
	public List<LifeStatus> getMeasures() {
	return measureList;
	}

	public void setMeasures(List<LifeStatus> measures) {
		this.measureList = measures;
	}
	
	public void addMeasure(LifeStatus newm) {
		if(newm ==null)
			return;
		if(this.measureList.size()!=0){	
			LifeStatus toDelete = null;
			for(LifeStatus m : this.measureList){
				if(m.getMeasureDefinition().getMeasureName().equals(newm.getMeasureDefinition().getMeasureName()))
				{
					toDelete = m;
					//System.out.println("****** insetring to HP ********");
				
					//System.out.println("****** added ********");
					
				}
			}
			if(toDelete!= null){
				toDelete.setHealthProfile(null);
				this.measureList.remove(toDelete);
			}
		}
	
		this.measureList.add(newm);	
		newm.setHealthProfile(this);
		newm.setPerson(this.getPerson());
			
		
	}

	public HealthProfile() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@OneToOne
	@XmlTransient
	@PrimaryKeyJoinColumn
	private Person person;
	
	
	public void setPerson(Person person) {
		this.person = person;
		List<LifeStatus> ml = this.getMeasures();
		for(LifeStatus m : ml)
			m.setPerson(this.getPerson());
		
	}
	@XmlTransient
	//@JsonIgnore
	public Person getPerson( ) {
		 return this.person ;
		
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
	

}