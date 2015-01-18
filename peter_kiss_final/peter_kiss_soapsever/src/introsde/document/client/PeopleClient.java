package introsde.document.client;

import introsde.assignment.soap.People;
import introsde.document.model.HealthProfile;
import introsde.document.model.LifeStatus;
import introsde.document.model.MeasureDefinition;
import introsde.document.model.Person;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class PeopleClient{
	static PrintWriter writer = null;
	public static void println(String s) throws FileNotFoundException, UnsupportedEncodingException
	{
//		if( writer==null)
//			writer = new PrintWriter("log.txt", "UTF-8");
//		println(s);
		System.out.println(s);
	}
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
        //writer.close();
	}

	private static void method12(People people, List<LifeStatus> lsl)
			throws JAXBException, PropertyException, FileNotFoundException, UnsupportedEncodingException {
		String measureType = "weight";
        String minValue = "50";
        String maxValue = "90";
        
        println("");
		println("=========Method #12=========");
		println("People who's "+measureType+" +is between "+minValue.toString() +" and "+maxValue);
		println("");
        
        List<Person> pl = people.readPersonListByMeasurement(measureType, maxValue, minValue);
        PersonList mh = new PersonList();
        mh.setPeople(pl);
        
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonList.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);       
	    jaxbMarshaller.marshal(mh, System.out);
	}

	private static List<LifeStatus> method11(People people)
			throws JAXBException, PropertyException, FileNotFoundException, UnsupportedEncodingException {
		Long personId = 1L;
        String measureType = "weight";
        String before = "2008-01-01";
        String after = "2000-01-01";
        
        println("");
		println("=========Method #11=========");
		println("Measures of the person with id "+personId+" +type of "+measureType +" before "+before+" and after "+after);
		println("");
        
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
			PropertyException, FileNotFoundException, UnsupportedEncodingException {
		int personId = 1;
		String measureType = "weight";
		int measureId = 1;
		
		println("");
		println("=========Method #10=========");
		println("Update measure with id "+measureId +" and type of "+measureType +" of the person with id "+personId+". Setting value to 66.6.");
		println("");
		
		LifeStatus ls = people.readPersonMeasurement(personId, measureType, measureId);
        ls.setValue("66.6");
        ls = people.updatePersonMeasurement(1, ls);
        println("");
		
		println("Modified : ");
		println("");
        
        JAXBContext jaxbContext = JAXBContext.newInstance(LifeStatus.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);       
	    jaxbMarshaller.marshal(ls, System.out);
	}

	private static void method8(People people) throws JAXBException,
			PropertyException, FileNotFoundException, UnsupportedEncodingException {
		int personId = 1;
		
		
		
		println("");
		println("=========Method #8=========");
		println("Add new measure to person with id "+personId);
		println("");
		
		JAXBContext jaxbContext = JAXBContext.newInstance(LifeStatus.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);       
		
		LifeStatus newheightStatus = new LifeStatus();
        MeasureDefinition md = people.readMeasureDefinition(2); 
        newheightStatus.setMeasureDefinition(md);
        newheightStatus.setValue("190.0");
        jaxbMarshaller.marshal(newheightStatus, System.out);
        println("");
		
		println("Adding : ");
		println("");
		jaxbMarshaller.marshal(newheightStatus, System.out);
        
        newheightStatus = people.savePersonMeasurement(1,newheightStatus);
        println("");
		
		println("Added : ");
		println("");
		
	    jaxbMarshaller.marshal(newheightStatus, System.out);
	}

	private static void method9(People people) throws JAXBException, FileNotFoundException, UnsupportedEncodingException {
		println("");
		println("=========Method #9=========");
		println("MeasureTypes");
		println("");
		
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
			PropertyException, FileNotFoundException, UnsupportedEncodingException {
		int personId = 1;
		String measureType = "weight";
		int mid = 1;
		
		
		println("");
		println("=========Method #7=========");
		println("Read measure: Person: "+personId+" MeasureType: "+ measureType+" MID: "+mid);
		println("");
		LifeStatus status = people.readPersonMeasurement(personId,measureType,mid);
        JAXBContext jaxbContext = JAXBContext.newInstance(LifeStatus.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);         
        //Marshal the employees list in console
        jaxbMarshaller.marshal(status, System.out);
	}

	private static void method6(People people) throws JAXBException,
			PropertyException, FileNotFoundException, UnsupportedEncodingException {
		int personId = 1;
		String measureType = "weight";		
		
		println("");
		println("=========Method #6=========");
		println("Read measures: Person: "+personId+" MeasureType: "+ measureType);
		println("");
		
		List<LifeStatus> lf = people.readPersonHistory(personId,measureType);
        MeasuresHistory mh = new MeasuresHistory();
        mh.setMeasures(lf);
        JAXBContext jaxbContext = JAXBContext.newInstance(MeasuresHistory.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);         
        //Marshal the employees list in console
        jaxbMarshaller.marshal(mh, System.out);
	}

	private static void method5(People people, Person createdPerson) throws FileNotFoundException, UnsupportedEncodingException {
		int idToDel = createdPerson.getIdPerson();
		
		println("");
		println("=========Method #5=========");
		println("Delete Person: "+idToDel);
		println("");
        
        people.deletePerson(createdPerson.getIdPerson());
        createdPerson = people.readPerson(idToDel);
        if(createdPerson == null)
        	println("deleted id : " +idToDel );
        else
        	println("Not succeded delete. ID:" +idToDel );
	}

	private static Person method4(People people) throws JAXBException,
			PropertyException, FileNotFoundException, UnsupportedEncodingException {
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
        newPerson.setHealthProfile(new HealthProfile());
        newPerson.getHealthProfile().setMeasures(new ArrayList<LifeStatus>());
        newPerson.getHealthProfile().addMeasure(heightStatus);
        newPerson.getHealthProfile().addMeasure(weightStatus);
        
        println("");
		println("=========Method #4=========");
		println("Creating person : ");
		println("");
		JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);       
	    jaxbMarshaller.marshal(newPerson, System.out);
        Person createdPerson = people.createPerson(newPerson);
      
        println("");		
		println("Created person : ");
		println(""); 
        //Marshal the employees list in console
        jaxbMarshaller.marshal(createdPerson, System.out);
		return createdPerson;
	}

	private static void method3(People people, Person p) throws JAXBException,
			PropertyException, FileNotFoundException, UnsupportedEncodingException {
		String backupName = p.getName();
		String newName = "HELLO";
        p.setName(newName);
		println("");
		println("=========Method #3=========");
		println("Updating person : Name-->"+backupName+"-->"+newName);
		println("");
		
        Person updatedPerson = people.updatePerson(p);
        JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);         
        //Marshal the employees list in console
        println("");
		
		println("Updated person :");
		println("");
        jaxbMarshaller.marshal(updatedPerson, System.out);
	}

	private static Person method2(People people) throws JAXBException,
			PropertyException, FileNotFoundException, UnsupportedEncodingException {
		int pid = 1;
		
		println("");
		println("=========Method #2=========");
		println("Get person : ID: "+pid);
		println("");
		
		Person p = people.readPerson(1);
        JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);         
        //Marshal the employees list in console
        println("");		
		println("Person with ID: "+pid);
		println("");
        jaxbMarshaller.marshal(p, System.out);
		return p;
	}

	private static List<Person> method1(People people) throws JAXBException,
			PropertyException, FileNotFoundException, UnsupportedEncodingException {
		
		println("");
		println("=========Method #1=========");
		println("Get all people ");
		println("");
		
		List<Person> pList = people.readPersonList();
        PersonList pl = new PersonList();
        pl.setPeople(pList);
        JAXBContext jaxbContext = JAXBContext.newInstance(PersonList.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();     
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);         
        //Marshal the employees list in console
        println("");		
		println("People:");
		println("");
        
        jaxbMarshaller.marshal(pl, System.out);
		return pList;
	}
}