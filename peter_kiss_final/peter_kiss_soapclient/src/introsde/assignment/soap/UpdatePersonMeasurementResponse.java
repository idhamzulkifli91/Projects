
package introsde.assignment.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updatePersonMeasurementResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePersonMeasurementResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updatedPersonMeasurement" type="{http://soap.assignment.introsde/}lifeStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePersonMeasurementResponse", propOrder = {
    "updatedPersonMeasurement"
})
public class UpdatePersonMeasurementResponse {

    protected LifeStatus updatedPersonMeasurement;

    /**
     * Gets the value of the updatedPersonMeasurement property.
     * 
     * @return
     *     possible object is
     *     {@link LifeStatus }
     *     
     */
    public LifeStatus getUpdatedPersonMeasurement() {
        return updatedPersonMeasurement;
    }

    /**
     * Sets the value of the updatedPersonMeasurement property.
     * 
     * @param value
     *     allowed object is
     *     {@link LifeStatus }
     *     
     */
    public void setUpdatedPersonMeasurement(LifeStatus value) {
        this.updatedPersonMeasurement = value;
    }

}
