package introsde.assignment.soap;

import introsde.document.model.HealthProfile;
import introsde.document.model.LifeStatus;
import introsde.document.model.MeasureDefinition;
import introsde.document.model.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

//Service Implementation
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE) 
@WebService(endpointInterface = "introsde.assignment.soap.People",
	serviceName="PeopleService")
public class PeopleImpl implements People {

	@Override
	public Person readPerson(int id) {
		System.out.println("---> Reading Person by id = "+id);
		Person p = Person.getPersonById(id);
		if (p!=null) {
			System.out.println("---> Found Person by id = "+id+" => "+p.getName());
		} else {
			System.out.println("---> Didn't find any Person with  id = "+id);
		}
		return p;
	}

	@Override
	public List<Person> readPersonList() {
		return Person.getAll();
	}

	@Override
	public Person createPerson(Person person) {
		Person.savePerson(person);
		return person;
	}

	@Override
	public Person updatePerson(Person person) {
//		Person existing = Person.getPersonById(person.getIdPerson());
//		List<LifeStatus> ls = existing.getLifeStatus();
//		List<LifeStatus> hp = existing.getHealthProfile();
//		//HealthProfile hp = existing.getHealthProfile();
//		person.setLifeStatus(ls);
//		person.setHealthProfile(hp);
//		//person.setHealthProfile(hp);
//		Person.updatePerson(person);
//		return person;
		
		Person existing = Person.getPersonById(person.getIdPerson());
		List<LifeStatus> ls = existing.getLifeStatus();
		HealthProfile hp = existing.getHealthProfile();
		//HealthProfile hp = existing.getHealthProfile();
		person.setLifeStatus(ls);
		person.setHealthProfile(hp);
		//person.setHealthProfile(hp);
		Person.updatePerson(person);
		return person;
	}

	@Override
	public int deletePerson(long id) {
		Person p = Person.getPersonById((int)id);
		if (p!=null) {
			Person.removePerson(p);
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public int updatePersonHP(int id, LifeStatus hp) {
		LifeStatus ls = LifeStatus.getLifeStatusById(hp.getIdMeasure());
		
		if (ls.getPerson().getIdPerson() == id) {
			LifeStatus.updateLifeStatus(hp);
			return hp.getIdMeasure();
		} else {
			return -1;
		}
	}
	
	@Override
	public MeasureDefinition readMeasureDefinition(int id)
	{
		return MeasureDefinition.getMeasureDefinitionById(id);
	}
	@Override
	public List<LifeStatus> readPersonHistory(int id,String measureType)
	{
		Person p = Person.getPersonById(id);
		List<LifeStatus> list =  p.getLifeStatus();
		List<LifeStatus>  result= new ArrayList<LifeStatus>();
		for(LifeStatus ls : list)
		{
			if(ls.getMeasureDefinition().getMeasureName().equals(measureType))
					result.add(ls);
		}
		return result;
	}
	@Override
	 public LifeStatus readPersonMeasurement(@WebParam(name="personId") int id,@WebParam(name="measurType")String measureType,@WebParam(name="mid")int mid)
	 {
		 LifeStatus ls = LifeStatus.getLifeStatusById(mid);
		 if(ls==null)
			 return null;
		 return ls;
	 }
	@Override
	 public List<String> readMeasureTypes()
	 {
		 List<MeasureDefinition> mdl =  MeasureDefinition.getAll();
		 List<String> result = new ArrayList<String>();
		 for(MeasureDefinition md : mdl)
			 result.add(md.getMeasureName());
		 return result;
	 }
	@Override
	public LifeStatus savePersonMeasurement(@WebParam(name="personId") int id,@WebParam(name="measure")LifeStatus newStatus)
	{
//		Person p = Person.getPersonById(id);
//		p.getHealthProfile().add(newStatus);
//		p.getLifeStatus().add(newStatus);
//		Person.updatePerson(p);
//		List<LifeStatus> lsl= p.getHealthProfile();
//		LifeStatus lfToRemove=null;
//		for(LifeStatus ls: lsl)
//			if(ls.getMeasureDefinition().getMeasureName().equals(newStatus.getMeasureDefinition().getMeasureName()))
//				lfToRemove = ls;
//		p.getHealthProfile().remove(lfToRemove);
//		for(LifeStatus ls: lsl)
//			if(ls.getMeasureDefinition().getMeasureName().equals(newStatus.getMeasureDefinition().getMeasureName()))
//				return ls;
//		return null;
		
		Person p = Person.getPersonById(id);
		p.getHealthProfile().addMeasure(newStatus);
		p.getLifeStatus().add(newStatus);
		Person.updatePerson(p);
		List<LifeStatus> lsl= p.getHealthProfile().getMeasures();
	
		
		for(LifeStatus ls: lsl)
			if(ls.getMeasureDefinition().getMeasureName().equals(newStatus.getMeasureDefinition().getMeasureName()))
				return ls;
		return null;
		
	}
	
	@Override
	public LifeStatus updatePersonMeasurement(@WebParam(name="personId") int id,@WebParam(name="measure")LifeStatus newStatus)
	{
		LifeStatus statToMod = LifeStatus.getLifeStatusById(newStatus.getIdMeasure());
		newStatus = LifeStatus.updateLifeStatus(newStatus);
		return newStatus;
	}
	@Override	
	public List<LifeStatus> readPersonMeasureByDates(@WebParam(name="personId")Long id,@WebParam(name="measureType") String measureType,@WebParam(name="before") String before,@WebParam(name="after") String after)
	{
		int intId=(int)(long)id;
		Person person = Person.getPersonById(intId);
		
		if (person == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		List<LifeStatus> allMeasure = person.getLifeStatus() ;
		
		List<LifeStatus> result = new ArrayList<LifeStatus>();
		Date from;
		try {
			from = new SimpleDateFormat("yyyy-MM-dd").parse(after);
			System.out.println("From : "+from);
			Date to = new SimpleDateFormat("yyyy-MM-dd").parse(before);
			System.out.println("To : "+to);
			for(LifeStatus m : allMeasure ){
				if(m.getMeasureDefinition().getMeasureName().equals(measureType)){
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
	
	@Override
	public List<Person>  readPersonListByMeasurement(@WebParam(name="measureType") String measureType,@WebParam(name="maxValue")  String maxValue, @WebParam(name="minValue") String minValue)
	{
//		List<Person> result = new ArrayList<Person>();
//		
//		List<Person> allPerson = Person.getAll();
//		
//		
//			for(Person p : allPerson ){
//				if(p.getHealthProfile()!=null)
//					for(LifeStatus m: p.getHealthProfile())
//						if(m.getMeasureDefinition().getMeasureName().equals(measureType)&&Double.parseDouble(m.getValue())>Double.parseDouble(minValue)&&Double.parseDouble(m.getValue())<Double.parseDouble(maxValue))
//							result.add(p);
//			}
//	
//		return result;
	List<Person> result = new ArrayList<Person>();
		
		List<Person> allPerson = Person.getAll();
		
		
			for(Person p : allPerson ){
				if(p.getHealthProfile()!=null)
					for(LifeStatus m: p.getHealthProfile().getMeasures())
						if(m.getMeasureDefinition().getMeasureName().equals(measureType)&&Double.parseDouble(m.getValue())>Double.parseDouble(minValue)&&Double.parseDouble(m.getValue())<Double.parseDouble(maxValue))
							result.add(p);
			}
	
		return result;
	}
}
