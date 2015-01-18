
package introsde.assignment.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for readMeasureDefinition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="readMeasureDefinition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="measurDefinitionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "readMeasureDefinition", propOrder = {
    "measurDefinitionId"
})
public class ReadMeasureDefinition {

    protected int measurDefinitionId;

    /**
     * Gets the value of the measurDefinitionId property.
     * 
     */
    public int getMeasurDefinitionId() {
        return measurDefinitionId;
    }

    /**
     * Sets the value of the measurDefinitionId property.
     * 
     */
    public void setMeasurDefinitionId(int value) {
        this.measurDefinitionId = value;
    }

}
