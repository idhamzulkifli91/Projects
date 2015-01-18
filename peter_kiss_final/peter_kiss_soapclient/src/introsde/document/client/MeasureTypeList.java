package introsde.document.client;
import introsde.assignment.soap.LifeStatus;
import introsde.assignment.soap.Person;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "measure-types")
@XmlAccessorType (XmlAccessType.FIELD)
public class MeasureTypeList
{
    @XmlElement(name = "type")
    private List<String> types = null;
 
    public List<String> getMeasures() {
        return types;
    }
 
    public void setMeasures(List<String> types) {
        this.types = types;
    }
}