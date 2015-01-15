package introsde.rest.model;

import introsde.rest.dao.LifeCoachDao;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlTransformation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the "MeasureType" database table.
 * 
 */
@Entity
@Table(name="\"MeasureType\"")
@NamedQuery(name="MeasureType.findAll", query="SELECT m FROM MeasureType m")
@XmlRootElement
public class MeasureType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="sqlite_measuretype")
	@TableGenerator(name="sqlite_measuretype", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="MeasureType")
	@Column(name="\"ID\"",nullable=false)
	
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="\"Description\"", nullable=false, length=2000000000)
	private String description;

	//bi-directional many-to-one association to Measure
	
	@XmlTransient
	@OneToMany(mappedBy="measureType",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Measure> measures;

	public MeasureType() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@XmlTransient
	@JsonIgnore
	public List<Measure> getMeasures() {
		return this.measures;
	}
	public static MeasureType getMeasureTypeById(int measureId) {
        EntityManager em = LifeCoachDao.instance.createEntityManager();
        MeasureType p = em.find(MeasureType.class, measureId);
        LifeCoachDao.instance.closeConnections(em);
        return p;
    }
	 public static MeasureType saveMeasureType(MeasureType m) {
	    
	        EntityManager em = LifeCoachDao.instance.createEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.persist(m);
	        tx.commit();
	        LifeCoachDao.instance.closeConnections(em);
	        return m;
	    } 

	public void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}
	public void addMeasure(Measure measure) {
		if(measure == null )return;
		if(this.measures==null)
			this.measures=new ArrayList<Measure>();
		this.measures.add(measure);
       
	}

	 public static List<MeasureType> getAll() {
	        EntityManager em = LifeCoachDao.instance.createEntityManager();
	        List<MeasureType> list = em.createNamedQuery("MeasureType.findAll", MeasureType.class)
	            .getResultList();
	        LifeCoachDao.instance.closeConnections(em);
	        return list;
	    }
	 public static MeasureType updateMeasureType(MeasureType m) {    	
	        EntityManager em = LifeCoachDao.instance.createEntityManager(); 
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        m=em.merge(m);
	        tx.commit();
	        LifeCoachDao.instance.closeConnections(em);
	        return m;
	    }
}