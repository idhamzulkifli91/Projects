package introsde.document.client;
import introsde.assignment.soap.Person;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "people")
@XmlAccessorType (XmlAccessType.FIELD)
public class PersonList
{
    @XmlElement(name = "person")
    private List<Person> people = null;
 
    public List<Person> getEmployees() {
        return people;
    }
 
    public void setPeople(List<Person> people) {
        this.people = people;
    }
}