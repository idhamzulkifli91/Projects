package introsde.rest.resources;

import introsde.rest.dao.LifeCoachDao;
import introsde.rest.model.HealthProfile;
import introsde.rest.model.MeasureType;
import introsde.rest.model.Person;
import introsde.rest.model.Measure;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/*
 * TODO 
 * - There is a problem with the EntityManager injection through @PersistenceUnit or @PersistenceContext
 * - will look into it later
 */

@Stateless
@LocalBean//Will map the resource to the URL /ehealth/v2
@Path("/person")
public class PersonCollectionResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// THIS IS NOT WORKING
	@PersistenceUnit(unitName="introsde-jpa")
	EntityManager entityManager;
	
	// THIS IS NOT WORKING
    @PersistenceContext(unitName = "introsde-jpa",type=PersistenceContextType.TRANSACTION)
    private EntityManagerFactory entityManagerFactory;

	// Return the list of people to the user in the browser
	@GET
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	public List<Person> getPersonsBrowser(@Context UriInfo info) {
		
		
			 
			String measureType = info.getQueryParameters().getFirst("measureType");
			String max = info.getQueryParameters().getFirst("max");
			String min = info.getQueryParameters().getFirst("min");
			
			if(min!=null){
				List<Person> result = new ArrayList<Person>();
				double maxValue = Double.parseDouble(max);
				double minValue = Double.parseDouble(min);
				List<Person> allPerson = Person.getAll();
				
				
					for(Person p : allPerson ){
						if(p.getHealthProfile()!=null)
							for(Measure m: p.getHealthProfile().getMeasures())
								if(m.getMeasureType().getDescription().equals(measureType)&&m.getValue()>minValue&&m.getValue()<maxValue)
									result.add(p);
					}
			
				return result;
				
			}
		
		
		System.out.println("Getting list of people...");
	    List<Person> people = Person.getAll();
		return people;
	}


	// retuns the number of people
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		System.out.println("Getting count...");
	    //List<Person> list = entityManager.createNamedQuery("Person.findAll", Person.class).getResultList();
	    List<Person> people = Person.getAll();
		int count = people.size();
		return String.valueOf(count);
	}
	
	@GET
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Path("{personId}/{measureType}")
	public List<Measure> getPersonMeasures(@PathParam("personId") int id,@PathParam("measureType") String measureType,@Context UriInfo info) {
		
		//String measureType = info.getQueryParameters().getFirst("measureType");
		String after = info.getQueryParameters().getFirst("after");
		String before = info.getQueryParameters().getFirst("before");
		if(after!=null)
		{
			Person person = Person.getPersonById(id);
			System.out.println(person.getFirstName() +" "+person.getLastName());
			if (person == null)
				throw new RuntimeException("Get: Person with " + id + " not found");
			List<Measure> allMeasure = person.getMeasures() ;
			
			List<Measure> result = new ArrayList<Measure>();
			Date from;
			try {
				from = new SimpleDateFormat("yyyy-MM-dd").parse(after);
				System.out.println("From : "+from);
				Date to = new SimpleDateFormat("yyyy-MM-dd").parse(before);
				System.out.println("To : "+to);
				for(Measure m : allMeasure ){
					if(m.getMeasureType().getDescription().equals(measureType)){
						Date d = new SimpleDateFormat("yyyy-MM-dd").parse(m.getDate());
						System.out.println(d);
						if(d.compareTo(from)>=0&&d.compareTo(to)<=0)
							result.add(m);
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//return null;
			}
			
			return result;
		}
		
		
		System.out.println("getPersonMeasures");
		Person person = Person.getPersonById(id);
		System.out.println(person.getFirstName() +" "+person.getLastName());
		if (person == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		List<Measure> allMeasure = person.getMeasures() ;
		
		List<Measure> result = new ArrayList<Measure>();
		for(Measure m : allMeasure )
			if(m.getMeasureType().getDescription().equals(measureType))
				result.add(m);
		
		return result;
		
	}
	
//	@GET
//	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
//	@Path("{personId}/{measureType}")
//	public List<Measure> getPersonMeasuresByDate(@PathParam("personId") int id,@PathParam("measureType") String measureType,@QueryParam("before")String before, @QueryParam("after")String after) {
//		System.out.println("getPersonMeasures");
//		Person person = Person.getPersonById(id);
//		System.out.println(person.getFirstName() +" "+person.getLastName());
//		if (person == null)
//			throw new RuntimeException("Get: Person with " + id + " not found");
//		List<Measure> allMeasure = person.getMeasures() ;
//		
//		List<Measure> result = new ArrayList<Measure>();
//		Date from;
//		try {
//			from = new SimpleDateFormat("yyyy-MM-dd").parse(after);
//		
//			Date to = new SimpleDateFormat("yyyy-MM-dd").parse(before);
//			for(Measure m : allMeasure ){
//				if(m.getMeasureType().getDescription().equals(measureType)){
//					Date d = new SimpleDateFormat("yyyy-MM-dd").parse(m.getDate());
//					if(d.compareTo(from)>=0&&d.compareTo(to)<=0)
//						result.add(m);
//				}
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			//return null;
//		}
//		
//		return result;
//		
//	}
	
//	@GET
//	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
//	
//	public List<Person> getPersonMeasuresByValues( @QueryParam("measureType")String measureType,@QueryParam("max")String max, @QueryParam("min")String min) {
//		System.out.println("getPersonMeasures");	
//		List<Person> result = new ArrayList<Person>();
//		double maxValue = Double.parseDouble(max);
//		double minValue = Double.parseDouble(min);
//		List<Person> allPerson = Person.getAll();
//		
//		
//			for(Person p : allPerson ){
//				for(Measure m: p.getHealthProfile().getMeasures())
//					if(m.getMeasureType().getDescription().equals(measureType)&&m.getValue()>minValue&&m.getValue()<maxValue)
//						result.add(p);
//			}
//	
//		return result;
//		
//	}
	
	@POST
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes({MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML})
	@Path("{personId}/{measureType}")
	public Measure addNewMeasure(Measure m,@PathParam("personId") int id,@PathParam("measureType") String measureType) {
		System.out.println("addNewMeasure");
		System.out.println("value: "+ m.getValue());
		System.out.println("value: "+ m.getDate());
		Person person = Person.getPersonById(id);
		System.out.println(person.getFirstName() +" "+person.getLastName());
		if (person == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		
		List<MeasureType> mtl = MeasureType.getAll();
		MeasureType type = null;
		for(MeasureType mt :mtl)
			if(mt.getDescription().equals(measureType)){
				m.setMeasureType(mt);
				type = mt;
			}

		type.addMeasure(m);	
		person.addMeasure(m);
		m.setPerson(person);		
		HealthProfile hp =person.getHealthProfile();
		hp.addMeasure(m);
		m.setHealthProfile(hp);
		Person.updatePerson(person);
		for(Measure m1:  person/*.getHealthProfile()*/.getMeasures())
			if(m1.getDate().equals(m.getDate())&&m1.getMeasureType().getDescription().equals(m.getMeasureType().getDescription())&&m1.getValue()==m.getValue())
				return m1;
		
		
		return m;

	}
	
	
	@GET
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Path("{personId}/{measureType}/{mid}")
	public Measure getPersonMeasureById(@PathParam("personId") int id, @PathParam("measureType") String measureType, @PathParam("mid") int mid) {
		Person person = Person.getPersonById(id);
		if (person == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		List<Measure> allMeasure = person.getMeasures() ;
		List<Measure> result = new ArrayList<Measure>();
		for(Measure m : allMeasure )
			if(m.getId() == mid && m.getMeasureType().getDescription().equals(measureType))
				return m;
		throw new RuntimeException("Get: Measure type of "+ measureType  +" +of the person with " + id + "with the measureId of "+ mid+" not found");
		
	}
	
	@PUT
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Path("{personId}/{measureType}/{mid}")
	public Measure updatePersonMeasureById(Measure m, @PathParam("personId") int id, @PathParam("measureType") String measureType, @PathParam("mid") int mid) {
		Measure existing = Measure.getMeasureById(mid);		
		existing.setValue(m.getValue());
		Measure.updateMeasure(existing);
		System.out.println("************** NEWVALUE: "+existing.getValue()+"*******************");
		return existing;
	}

//	// let's create this service for responding a submission form
	/*@POST
	@Produces({MediaType.TEXT_HTML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes(  MediaType.APPLICATION_JSON )
	public void newPerson(@FormParam("personID") int id,
			@FormParam("firstName") String firstname,
			@FormParam("lastName") String lastname,
			@FormParam("dateOfBirth") String date,
			//@FormParam("healthProfile") HealthProfile hp,
			@Context HttpServletResponse servletResponse) throws IOException {
		Person p = new Person();
		p.setPersonID(id);
		p.setFirstName(firstname);
		p.setLastName(lastname);
		p.setDateOfBirth(date);
		Person.savePerson(p);
		servletResponse.sendRedirect("../NewPerson.html");
	}*/
	@POST
    @Produces({MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML})
    public Person newPerson(Person person) throws IOException {

        System.out.println("Creating new person...");  
       /* HealthProfile hp = person.getHealthProfile();
        if(hp != null){
        	//System.out.println("****** Before: Id : "+hp.getId()+"Height : "+hp.getHeight()+"Height : "+hp.getWeight());
        	hp = HealthProfile.saveHealthProfile(hp);
        	//System.out.println("Id : "+hp.getId()+"Height : "+hp.getHeight()+"Height : "+hp.getWeight());
        	person.setHealthProfile(hp);      	
        	
        }*/
        return Person.savePerson(person);

    }
	
	
	// let's create this service for responding a submission form
	// 
	/*@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Person newPerson(Person person) throws IOException {
		System.out.println("Creating new person...");
		//EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityManager entityManager = PersonDao.instance.createEntityManager();
//        try {
//    		entityManager.persist(person);
//    		entityManager.refresh(person);
//    		return person;
//        } finally {
//            entityManager.close();
//        }
		
		return Person.savePerson(person);
	}*/
	

	// Defines that the next path parameter after the base url is
	// treated as a parameter and passed to the PersonResources
	// Allows to type http://localhost:599/base_url/1
	// 1 will be treaded as parameter todo and passed to PersonResource
	@Path("{personId}")
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public PersonResource getPerson(@PathParam("personId") int id) {
		System.out.println("getPerson");
//		//EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityManager em = PersonDao.instance.createEntityManager();
//        try {
//    		System.out.println("Person by id..."+id);
//    		return new PersonResource(uriInfo, request, id, em);
//        } finally {
//            em.close();
//        }
		return new PersonResource(uriInfo, request, id);
		//return Person.getPersonById(id);
	}
	
}
