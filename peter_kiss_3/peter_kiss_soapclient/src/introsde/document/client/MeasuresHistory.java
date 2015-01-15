package introsde.document.client;
import introsde.assignment.soap.LifeStatus;
import introsde.assignment.soap.Person;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "measure-History")
@XmlAccessorType (XmlAccessType.FIELD)
public class MeasuresHistory
{
    @XmlElement(name = "measure")
    private List<LifeStatus> measures = null;
 
    public List<LifeStatus> getMeasures() {
        return measures;
    }
 
    public void setMeasures(List<LifeStatus> measures) {
        this.measures = measures;
    }
}