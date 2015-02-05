package rest.ehealth.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import rest.ehealth.dao.LifeCoachDao;

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

	@Column(name="\"date\"")
	private String date;


	@Id
	@GeneratedValue(generator="sqlite_measure")
	@TableGenerator(name="sqlite_measure", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Measure")
	@Column(name="\"measureId\"", nullable=false)	
	private int measureId;

	@Column(name="\"value\"")
	private double value;

	//bi-directional many-to-one association to Person
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="personId")
	private Person person;

	//bi-directional many-to-one association to MeasureType
	@ManyToOne
	@JoinColumn(name="measureTypeId")
	private MeasureType measureType;

	public Measure() {
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMeasureId() {
		return this.measureId;
	}

	public void setMeasureId(int measureId) {
		this.measureId = measureId;
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	@XmlTransient
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public MeasureType getMeasureType() {
		return this.measureType;
	}

	public void setMeasureType(MeasureType measureType) {
		this.measureType = measureType;
	}
	
	public static Measure getMeasureById(int personId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Measure p = em.find(Measure.class, personId);
		LifeCoachDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<Measure> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Measure> list = em.createNamedQuery("Measure.findAll", Measure.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Measure saveMeasure(Measure p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static Measure updateMeasure(Measure p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeMeasure(Measure p) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}

}