package introsde.document.client;

import introsde.assignment.soap.People;
import introsde.assignment.soap.LifeStatus;
import introsde.assignment.soap.MeasureDefinition;
import introsde.assignment.soap.Person;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;

public class PeopleClient{
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:443/ws/people?wsdl");
        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://soap.assignment.introsde/", "PeopleService");
        Service service = Service.create(url, qname);

        People people = service.getPort(People.class);
       
        
        //#1
        List<Person> pList = method1(people);
        
        //#2
        Person p = method2(people);
        
        
        
        //#3
        if(p !=null)
        	method3(people, p);
        
        //#4
        Person createdPerson = method4(people);
        
        //#5
        method5(people, createdPerson);
        
        //#6
        method6(people);
        
        //#7
        method7(people);
        
       
        //#8
        
        method8(people);
        
        
        //#9
        method9(people);
        
        //#10
        method10(people);
        
        //#11
        List<LifeStatus> lsl = method11(people);
	    
	    //#12
        method12(people, lsl);
	    
	}

	private static void method12(People people, List<LifeStatus> lsl)
			throws JAXBException, PropertyException {
		String measureType = "weight";
        String minValue = "50";
        String maxValue = "90";
        
        System.out.println("");
		System.out.println("=========Method #12=========");
		System.out.println("People whos "+measureType+" +is between "+minValue +" and "+maxValue);
		System.out.println("");
        
        List<Person> pl = people.readPersonListByMeasurement(measureType, maxValue, minValue);
        PersonList mh = new PersonList();
        mh.setPeople(pl);
        
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonList.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);       
	    jaxbMarshaller.marshal(mh, System.out);
	}

	private static List<LifeStatus> method11(People people)
			throws JAXBException, PropertyException {
		Long personId = 1L;
        String measureType = "weight";
        String before = "2008-01-01";
        String after = "2000-01-01";
        
        System.out.println("");
		System.out.println("=========Method #11=========");
		System.out.println("Measures of the person with id "+personId+" +type of "+measureType +" before "+before+" and after "+after);
		System.out.println("");
        
        List<LifeStatus> lsl = people.readPersonMeasureByDates(personId, measureType, before, after);
        MeasuresHistory mh = new MeasuresHistory();
        mh.setMeasures(lsl);
        
        JAXBContext jaxbContext = JAXBContext.newInstance(MeasuresHistory.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);       
	    jaxbMarshaller.marshal(mh, System.out);
		return lsl;
	}

	private static void method10(People people) throws JAXBException,
			PropertyException {
		int personId = 1;
		String measureType = "weight";
		int measureId = 1;
		
		System.out.println("");
		System.out.println("=========Method #10=========");
		System.out.println("Update measure with id "+measureId +" and type of "+measureType +" of the person with id "+personId+". Setting value to 66.6.");
		System.out.println("");
		
		LifeStatus ls = people.readPersonMeasurement(personId, measureType, measureId);
        ls.setValue("66.6");
        ls = people.updatePersonMeasurement(1, ls);
        System.out.println("");
		
		System.out.println("Modified : ");
		System.out.println("");
        
        JAXBContext jaxbContext = JAXBContext.newInstance(LifeStatus.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);       
	    jaxbMarshaller.marshal(ls, System.out);
	}

	private static void method8(People people) throws JAXBException,
			PropertyException {
		int personId = 1;
		
		
		
		System.out.println("");
		System.out.println("=========Method #8=========");
		System.out.println("Add new measure to person with id "+personId);
		System.out.println("");
		
		JAXBContext jaxbContext = JAXBContext.newInstance(LifeStatus.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);       
		
		LifeStatus newheightStatus = new LifeStatus();
        MeasureDefinition md = people.readMeasureDefinition(2); 
        newheightStatus.setMeasureDefinition(md);
        newheightStatus.setValue("190.0");
        jaxbMarshaller.marshal(newheightStatus, System.out);
        System.out.println("");
		
		System.out.println("Adding : ");
		System.out.println("");
		jaxbMarshaller.marshal(newheightStatus, System.out);
        
        newheightStatus = people.savePersonMeasurement(1,newheightStatus);
        System.out.println("");
		
		System.out.println("Added : ");
		System.out.println("");
		
	    jaxbMarshaller.marshal(newheightStatus, System.out);
	}

	private static void method9(People people) throws JAXBException {
		System.out.println("");
		System.out.println("=========Method #9=========");
		System.out.println("MeasureTypes");
		System.out.println("");
		
		List<String> measuretypes = people.readMeasureTypes();
        MeasureTypeList mtl = new MeasureTypeList();
        mtl.setMeasures(measuretypes);
        
        JAXBContext jaxbContext = JAXBContext.newInstance(MeasureTypeList.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);         
        //Marshal the employees list in console
        jaxbMarshaller.marshal(mtl, System.out);
	}

	private static void method7(People people) throws JAXBException,
			PropertyException {
		int personId = 1;
		String measureType = "weight";
		int mid = 1;
		
		
		System.out.println("");
		System.out.println("=========Method #7=========");
		System.out.println("Read measure: Person: "+personId+" MeasureType: "+ measureType+" MID: "+mid);
		System.out.println("");
		LifeStatus status = people.readPersonMeasurement(personId,measureType,mid);
        JAXBContext jaxbContext = JAXBContext.newInstance(LifeStatus.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);         
        //Marshal the employees list in console
        jaxbMarshaller.marshal(status, System.out);
	}

	private static void method6(People people) throws JAXBException,
			PropertyException {
		int personId = 1;
		String measureType = "weight";		
		
		System.out.println("");
		System.out.println("=========Method #6=========");
		System.out.println("Read measures: Person: "+personId+" MeasureType: "+ measureType);
		System.out.println("");
		
		List<LifeStatus> lf = people.readPersonHistory(personId,measureType);
        MeasuresHistory mh = new MeasuresHistory();
        mh.setMeasures(lf);
        JAXBContext jaxbContext = JAXBContext.newInstance(MeasuresHistory.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);         
        //Marshal the employees list in console
        jaxbMarshaller.marshal(mh, System.out);
	}

	private static void method5(People people, Person createdPerson) {
		int idToDel = createdPerson.getIdPerson();
		
		System.out.println("");
		System.out.println("=========Method #5=========");
		System.out.println("Delete Person: "+idToDel);
		System.out.println("");
        
        people.deletePerson(createdPerson.getIdPerson());
        createdPerson = people.readPerson(idToDel);
        if(createdPerson == null)
        	System.out.println("deleted id : " +idToDel );
        else
        	System.out.println("Not succeded delete. ID:" +idToDel );
	}

	private static Person method4(People people) throws JAXBException,
			PropertyException {
		Person newPerson = new Person();
        newPerson.setName("newPerson");        
        newPerson.setBirthdate("1984-05-27");
       
        LifeStatus weightStatus = new LifeStatus();
        MeasureDefinition md = people.readMeasureDefinition(1);   
        weightStatus.setMeasureDefinition(md);
        weightStatus.setValue("85.0");
        
        LifeStatus heightStatus = new LifeStatus();
        md = people.readMeasureDefinition(2); 
        heightStatus.setMeasureDefinition(md);
        heightStatus.setValue("190.0");
      //  newPerson.getHealthProfile()(new ArrayList<LifeStatus>());
//        newPerson.getHealthProfile().getMeasure().add(weightStatus);
//        newPerson.getHealthProfile().getMeasure().add(heightStatus);
        
        System.out.println("");
		System.out.println("=========Method #4=========");
		System.out.println("Creating person : ");
		System.out.println("");
		JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);       
	    jaxbMarshaller.marshal(newPerson, System.out);
	    /////////////////////////
	    
	    Holder<Person> h = new Holder<Person>(newPerson);
	    people.createPerson(h);
	    Person createdPerson = h.value;
	    
	    /////////////////////////
        //Person createdPerson = people.createPerson(newPerson);
        ////////////////////////
        System.out.println("");		
		System.out.println("Created person : ");
		System.out.println(""); 
        //Marshal the employees list in console
        jaxbMarshaller.marshal(createdPerson, System.out);
		return createdPerson;
	}

	private static void method3(People people, Person p) throws JAXBException,
			PropertyException {
		String backupName = p.getName();
		String newName = "HELLO";
        p.setName(newName);
		System.out.println("");
		System.out.println("=========Method #3=========");
		System.out.println("Updating person : Name-->"+backupName+"-->"+newName);
		System.out.println("");
		///////////////////////////////////////
		Holder<Person> h = new Holder<Person>(p);
        people.updatePerson(h);
        Person updatedPerson = (Person)h.value;
        ///////////////////////////////////////
        //Person updatedPerson = people.updatePerson(p);
        ///////////////////////////////////////
        
        JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);         
        //Marshal the employees list in console
        System.out.println("");
		
		System.out.println("Updated person :");
		System.out.println("");
        jaxbMarshaller.marshal(updatedPerson, System.out);
	}

	private static Person method2(People people) throws JAXBException,
			PropertyException {
		int pid = 1;
		
		System.out.println("");
		System.out.println("=========Method #2=========");
		System.out.println("Get person : ID: "+pid);
		System.out.println("");
		
		Person p = people.readPerson(1);
        JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);         
        //Marshal the employees list in console
        System.out.println("");		
		System.out.println("Person with ID: "+pid);
		System.out.println("");
		if(p==null)
			return null;
		jaxbMarshaller.marshal(p, System.out);
		return p;
	}

	private static List<Person> method1(People people) throws JAXBException,
			PropertyException {
		
		System.out.println("");
		System.out.println("=========Method #1=========");
		System.out.println("Get all people ");
		System.out.println("");
		
		List<Person> pList = people.readPersonList();
        PersonList pl = new PersonList();
        pl.setPeople(pList);
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonList.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);         
        //Marshal the employees list in console
        System.out.println("");		
		System.out.println("People:");
		System.out.println("");
        
        jaxbMarshaller.marshal(pl, System.out);
		return pList;
	}
}