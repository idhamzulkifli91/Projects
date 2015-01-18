
package introsde.assignment.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for readPersonMeasurementResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="readPersonMeasurementResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="personMeasurment" type="{http://soap.assignment.introsde/}lifeStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "readPersonMeasurementResponse", propOrder = {
    "personMeasurment"
})
public class ReadPersonMeasurementResponse {

    protected LifeStatus personMeasurment;

    /**
     * Gets the value of the personMeasurment property.
     * 
     * @return
     *     possible object is
     *     {@link LifeStatus }
     *     
     */
    public LifeStatus getPersonMeasurment() {
        return personMeasurment;
    }

    /**
     * Sets the value of the personMeasurment property.
     * 
     * @param value
     *     allowed object is
     *     {@link LifeStatus }
     *     
     */
    public void setPersonMeasurment(LifeStatus value) {
        this.personMeasurment = value;
    }

}
