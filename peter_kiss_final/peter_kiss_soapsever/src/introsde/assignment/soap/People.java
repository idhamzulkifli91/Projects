package introsde.assignment.soap;
import introsde.document.model.LifeStatus;
import introsde.document.model.MeasureDefinition;
import introsde.document.model.Person;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface People {
    @WebMethod(operationName="readPerson")
    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="personId") int id);
 
    @WebMethod(operationName="readPersonList")
    @WebResult(name="people") 
    public List<Person> readPersonList();
 
    @WebMethod(operationName="createPerson")
    @WebResult(name="person") 
    public Person createPerson(@WebParam(name="person") Person person);
 
    @WebMethod(operationName="updatePerson")
    @WebResult(name="person") 
    public Person updatePerson(@WebParam(name="person") Person person);
    
    @WebMethod(operationName="deletePerson")
    @WebResult(name="personId") 
    public int deletePerson(@WebParam(name="personId") long id);
    
    @WebMethod(operationName="updatePersonHealthProfile")
    @WebResult(name="hpId") 
    public int updatePersonHP(@WebParam(name="personId") int id, @WebParam(name="healthProfile") LifeStatus hp);
    
    @WebMethod(operationName="readMeasureDefinition")
    @WebResult(name="measurDefinition") 
    public MeasureDefinition readMeasureDefinition(@WebParam(name="measurDefinitionId") int id);
    
    @WebMethod(operationName="readPersonHistory")
    @WebResult(name="personHistory") 
    public List<LifeStatus> readPersonHistory(@WebParam(name="personId") int id,@WebParam(name="measurType")String measureType);
    
    @WebMethod(operationName="readPersonMeasurement")
    @WebResult(name="personMeasurment") 
    public LifeStatus readPersonMeasurement(@WebParam(name="personId") int id,@WebParam(name="measurType")String measureType,@WebParam(name="mid")int mid);
    
    @WebMethod(operationName="readMeasureTypes")
    @WebResult(name="measureTypes") 
    public List<String> readMeasureTypes();
    
    @WebMethod(operationName="savePersonMeasurement")
    @WebResult(name="savePersonMeasurment") 
    public LifeStatus savePersonMeasurement(@WebParam(name="personId") int id,@WebParam(name="measure")LifeStatus newStatus);
    
    @WebMethod(operationName="updatePersonMeasurement")
    @WebResult(name="updatedPersonMeasurement") 
    public LifeStatus updatePersonMeasurement(@WebParam(name="personId") int id,@WebParam(name="measure")LifeStatus newStatus);
    
    @WebMethod(operationName="readPersonMeasureByDates")
    @WebResult(name="measures") 
    public List<LifeStatus> readPersonMeasureByDates(@WebParam(name="personId")Long id,@WebParam(name="measureType") String measureType,@WebParam(name="before") String before,@WebParam(name="after") String after);

    @WebMethod(operationName="readPersonListByMeasurement")
    @WebResult(name="people") 
    public List<Person>  readPersonListByMeasurement(@WebParam(name="measureType") String measureType,@WebParam(name="maxValue")  String maxValue, @WebParam(name="minValue") String minValue);
    
    //    @WebMethod(operationName="readPersonMeasurment")
//    @WebResult(name="hpId") 
//    readPersonMeasurement(@WebParam(name="personId") Long id, String measureType, Long mid) 
//    public int updatePersonHP(@WebParam(name="personId") int id, @WebParam(name="healthProfile") LifeStatus hp);
}