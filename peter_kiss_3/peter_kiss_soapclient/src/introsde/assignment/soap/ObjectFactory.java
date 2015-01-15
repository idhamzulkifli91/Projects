
package introsde.assignment.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the introsde.assignment.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReadPersonList_QNAME = new QName("http://soap.assignment.introsde/", "readPersonList");
    private final static QName _MeasureDefaultRange_QNAME = new QName("http://soap.assignment.introsde/", "measureDefaultRange");
    private final static QName _ReadPersonMeasureByDates_QNAME = new QName("http://soap.assignment.introsde/", "readPersonMeasureByDates");
    private final static QName _SavePersonMeasurement_QNAME = new QName("http://soap.assignment.introsde/", "savePersonMeasurement");
    private final static QName _ReadMeasureDefinitionResponse_QNAME = new QName("http://soap.assignment.introsde/", "readMeasureDefinitionResponse");
    private final static QName _ReadMeasureTypes_QNAME = new QName("http://soap.assignment.introsde/", "readMeasureTypes");
    private final static QName _CreatePerson_QNAME = new QName("http://soap.assignment.introsde/", "createPerson");
    private final static QName _ReadPersonListByMeasurement_QNAME = new QName("http://soap.assignment.introsde/", "readPersonListByMeasurement");
    private final static QName _ReadPersonHistory_QNAME = new QName("http://soap.assignment.introsde/", "readPersonHistory");
    private final static QName _CreatePersonResponse_QNAME = new QName("http://soap.assignment.introsde/", "createPersonResponse");
    private final static QName _UpdatePersonResponse_QNAME = new QName("http://soap.assignment.introsde/", "updatePersonResponse");
    private final static QName _ReadPersonHistoryResponse_QNAME = new QName("http://soap.assignment.introsde/", "readPersonHistoryResponse");
    private final static QName _ReadPersonMeasurement_QNAME = new QName("http://soap.assignment.introsde/", "readPersonMeasurement");
    private final static QName _ReadMeasureDefinition_QNAME = new QName("http://soap.assignment.introsde/", "readMeasureDefinition");
    private final static QName _ReadPerson_QNAME = new QName("http://soap.assignment.introsde/", "readPerson");
    private final static QName _UpdatePersonHealthProfile_QNAME = new QName("http://soap.assignment.introsde/", "updatePersonHealthProfile");
    private final static QName _UpdatePerson_QNAME = new QName("http://soap.assignment.introsde/", "updatePerson");
    private final static QName _UpdatePersonHealthProfileResponse_QNAME = new QName("http://soap.assignment.introsde/", "updatePersonHealthProfileResponse");
    private final static QName _ReadPersonResponse_QNAME = new QName("http://soap.assignment.introsde/", "readPersonResponse");
    private final static QName _UpdatePersonMeasurement_QNAME = new QName("http://soap.assignment.introsde/", "updatePersonMeasurement");
    private final static QName _DeletePerson_QNAME = new QName("http://soap.assignment.introsde/", "deletePerson");
    private final static QName _ReadPersonMeasurementResponse_QNAME = new QName("http://soap.assignment.introsde/", "readPersonMeasurementResponse");
    private final static QName _ReadMeasureTypesResponse_QNAME = new QName("http://soap.assignment.introsde/", "readMeasureTypesResponse");
    private final static QName _UpdatePersonMeasurementResponse_QNAME = new QName("http://soap.assignment.introsde/", "updatePersonMeasurementResponse");
    private final static QName _ReadPersonMeasureByDatesResponse_QNAME = new QName("http://soap.assignment.introsde/", "readPersonMeasureByDatesResponse");
    private final static QName _ReadPersonListResponse_QNAME = new QName("http://soap.assignment.introsde/", "readPersonListResponse");
    private final static QName _DeletePersonResponse_QNAME = new QName("http://soap.assignment.introsde/", "deletePersonResponse");
    private final static QName _ReadPersonListByMeasurementResponse_QNAME = new QName("http://soap.assignment.introsde/", "readPersonListByMeasurementResponse");
    private final static QName _SavePersonMeasurementResponse_QNAME = new QName("http://soap.assignment.introsde/", "savePersonMeasurementResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: introsde.assignment.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReadMeasureDefinitionResponse }
     * 
     */
    public ReadMeasureDefinitionResponse createReadMeasureDefinitionResponse() {
        return new ReadMeasureDefinitionResponse();
    }

    /**
     * Create an instance of {@link SavePersonMeasurement }
     * 
     */
    public SavePersonMeasurement createSavePersonMeasurement() {
        return new SavePersonMeasurement();
    }

    /**
     * Create an instance of {@link MeasureDefaultRange }
     * 
     */
    public MeasureDefaultRange createMeasureDefaultRange() {
        return new MeasureDefaultRange();
    }

    /**
     * Create an instance of {@link ReadPersonMeasureByDates }
     * 
     */
    public ReadPersonMeasureByDates createReadPersonMeasureByDates() {
        return new ReadPersonMeasureByDates();
    }

    /**
     * Create an instance of {@link ReadPersonList }
     * 
     */
    public ReadPersonList createReadPersonList() {
        return new ReadPersonList();
    }

    /**
     * Create an instance of {@link ReadMeasureDefinition }
     * 
     */
    public ReadMeasureDefinition createReadMeasureDefinition() {
        return new ReadMeasureDefinition();
    }

    /**
     * Create an instance of {@link UpdatePersonHealthProfile }
     * 
     */
    public UpdatePersonHealthProfile createUpdatePersonHealthProfile() {
        return new UpdatePersonHealthProfile();
    }

    /**
     * Create an instance of {@link ReadPerson }
     * 
     */
    public ReadPerson createReadPerson() {
        return new ReadPerson();
    }

    /**
     * Create an instance of {@link ReadPersonMeasurement }
     * 
     */
    public ReadPersonMeasurement createReadPersonMeasurement() {
        return new ReadPersonMeasurement();
    }

    /**
     * Create an instance of {@link UpdatePersonResponse }
     * 
     */
    public UpdatePersonResponse createUpdatePersonResponse() {
        return new UpdatePersonResponse();
    }

    /**
     * Create an instance of {@link ReadPersonHistoryResponse }
     * 
     */
    public ReadPersonHistoryResponse createReadPersonHistoryResponse() {
        return new ReadPersonHistoryResponse();
    }

    /**
     * Create an instance of {@link CreatePerson }
     * 
     */
    public CreatePerson createCreatePerson() {
        return new CreatePerson();
    }

    /**
     * Create an instance of {@link ReadPersonListByMeasurement }
     * 
     */
    public ReadPersonListByMeasurement createReadPersonListByMeasurement() {
        return new ReadPersonListByMeasurement();
    }

    /**
     * Create an instance of {@link ReadPersonHistory }
     * 
     */
    public ReadPersonHistory createReadPersonHistory() {
        return new ReadPersonHistory();
    }

    /**
     * Create an instance of {@link CreatePersonResponse }
     * 
     */
    public CreatePersonResponse createCreatePersonResponse() {
        return new CreatePersonResponse();
    }

    /**
     * Create an instance of {@link ReadMeasureTypes }
     * 
     */
    public ReadMeasureTypes createReadMeasureTypes() {
        return new ReadMeasureTypes();
    }

    /**
     * Create an instance of {@link DeletePerson }
     * 
     */
    public DeletePerson createDeletePerson() {
        return new DeletePerson();
    }

    /**
     * Create an instance of {@link ReadPersonMeasurementResponse }
     * 
     */
    public ReadPersonMeasurementResponse createReadPersonMeasurementResponse() {
        return new ReadPersonMeasurementResponse();
    }

    /**
     * Create an instance of {@link UpdatePersonMeasurement }
     * 
     */
    public UpdatePersonMeasurement createUpdatePersonMeasurement() {
        return new UpdatePersonMeasurement();
    }

    /**
     * Create an instance of {@link ReadPersonResponse }
     * 
     */
    public ReadPersonResponse createReadPersonResponse() {
        return new ReadPersonResponse();
    }

    /**
     * Create an instance of {@link UpdatePersonHealthProfileResponse }
     * 
     */
    public UpdatePersonHealthProfileResponse createUpdatePersonHealthProfileResponse() {
        return new UpdatePersonHealthProfileResponse();
    }

    /**
     * Create an instance of {@link UpdatePerson }
     * 
     */
    public UpdatePerson createUpdatePerson() {
        return new UpdatePerson();
    }

    /**
     * Create an instance of {@link SavePersonMeasurementResponse }
     * 
     */
    public SavePersonMeasurementResponse createSavePersonMeasurementResponse() {
        return new SavePersonMeasurementResponse();
    }

    /**
     * Create an instance of {@link ReadPersonListByMeasurementResponse }
     * 
     */
    public ReadPersonListByMeasurementResponse createReadPersonListByMeasurementResponse() {
        return new ReadPersonListByMeasurementResponse();
    }

    /**
     * Create an instance of {@link DeletePersonResponse }
     * 
     */
    public DeletePersonResponse createDeletePersonResponse() {
        return new DeletePersonResponse();
    }

    /**
     * Create an instance of {@link ReadPersonListResponse }
     * 
     */
    public ReadPersonListResponse createReadPersonListResponse() {
        return new ReadPersonListResponse();
    }

    /**
     * Create an instance of {@link ReadPersonMeasureByDatesResponse }
     * 
     */
    public ReadPersonMeasureByDatesResponse createReadPersonMeasureByDatesResponse() {
        return new ReadPersonMeasureByDatesResponse();
    }

    /**
     * Create an instance of {@link UpdatePersonMeasurementResponse }
     * 
     */
    public UpdatePersonMeasurementResponse createUpdatePersonMeasurementResponse() {
        return new UpdatePersonMeasurementResponse();
    }

    /**
     * Create an instance of {@link ReadMeasureTypesResponse }
     * 
     */
    public ReadMeasureTypesResponse createReadMeasureTypesResponse() {
        return new ReadMeasureTypesResponse();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link LifeStatus }
     * 
     */
    public LifeStatus createLifeStatus() {
        return new LifeStatus();
    }

    /**
     * Create an instance of {@link HealthProfile }
     * 
     */
    public HealthProfile createHealthProfile() {
        return new HealthProfile();
    }

    /**
     * Create an instance of {@link MeasureDefinition }
     * 
     */
    public MeasureDefinition createMeasureDefinition() {
        return new MeasureDefinition();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPersonList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readPersonList")
    public JAXBElement<ReadPersonList> createReadPersonList(ReadPersonList value) {
        return new JAXBElement<ReadPersonList>(_ReadPersonList_QNAME, ReadPersonList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MeasureDefaultRange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "measureDefaultRange")
    public JAXBElement<MeasureDefaultRange> createMeasureDefaultRange(MeasureDefaultRange value) {
        return new JAXBElement<MeasureDefaultRange>(_MeasureDefaultRange_QNAME, MeasureDefaultRange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPersonMeasureByDates }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readPersonMeasureByDates")
    public JAXBElement<ReadPersonMeasureByDates> createReadPersonMeasureByDates(ReadPersonMeasureByDates value) {
        return new JAXBElement<ReadPersonMeasureByDates>(_ReadPersonMeasureByDates_QNAME, ReadPersonMeasureByDates.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SavePersonMeasurement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "savePersonMeasurement")
    public JAXBElement<SavePersonMeasurement> createSavePersonMeasurement(SavePersonMeasurement value) {
        return new JAXBElement<SavePersonMeasurement>(_SavePersonMeasurement_QNAME, SavePersonMeasurement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadMeasureDefinitionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readMeasureDefinitionResponse")
    public JAXBElement<ReadMeasureDefinitionResponse> createReadMeasureDefinitionResponse(ReadMeasureDefinitionResponse value) {
        return new JAXBElement<ReadMeasureDefinitionResponse>(_ReadMeasureDefinitionResponse_QNAME, ReadMeasureDefinitionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadMeasureTypes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readMeasureTypes")
    public JAXBElement<ReadMeasureTypes> createReadMeasureTypes(ReadMeasureTypes value) {
        return new JAXBElement<ReadMeasureTypes>(_ReadMeasureTypes_QNAME, ReadMeasureTypes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "createPerson")
    public JAXBElement<CreatePerson> createCreatePerson(CreatePerson value) {
        return new JAXBElement<CreatePerson>(_CreatePerson_QNAME, CreatePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPersonListByMeasurement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readPersonListByMeasurement")
    public JAXBElement<ReadPersonListByMeasurement> createReadPersonListByMeasurement(ReadPersonListByMeasurement value) {
        return new JAXBElement<ReadPersonListByMeasurement>(_ReadPersonListByMeasurement_QNAME, ReadPersonListByMeasurement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPersonHistory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readPersonHistory")
    public JAXBElement<ReadPersonHistory> createReadPersonHistory(ReadPersonHistory value) {
        return new JAXBElement<ReadPersonHistory>(_ReadPersonHistory_QNAME, ReadPersonHistory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "createPersonResponse")
    public JAXBElement<CreatePersonResponse> createCreatePersonResponse(CreatePersonResponse value) {
        return new JAXBElement<CreatePersonResponse>(_CreatePersonResponse_QNAME, CreatePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "updatePersonResponse")
    public JAXBElement<UpdatePersonResponse> createUpdatePersonResponse(UpdatePersonResponse value) {
        return new JAXBElement<UpdatePersonResponse>(_UpdatePersonResponse_QNAME, UpdatePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPersonHistoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readPersonHistoryResponse")
    public JAXBElement<ReadPersonHistoryResponse> createReadPersonHistoryResponse(ReadPersonHistoryResponse value) {
        return new JAXBElement<ReadPersonHistoryResponse>(_ReadPersonHistoryResponse_QNAME, ReadPersonHistoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPersonMeasurement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readPersonMeasurement")
    public JAXBElement<ReadPersonMeasurement> createReadPersonMeasurement(ReadPersonMeasurement value) {
        return new JAXBElement<ReadPersonMeasurement>(_ReadPersonMeasurement_QNAME, ReadPersonMeasurement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadMeasureDefinition }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readMeasureDefinition")
    public JAXBElement<ReadMeasureDefinition> createReadMeasureDefinition(ReadMeasureDefinition value) {
        return new JAXBElement<ReadMeasureDefinition>(_ReadMeasureDefinition_QNAME, ReadMeasureDefinition.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readPerson")
    public JAXBElement<ReadPerson> createReadPerson(ReadPerson value) {
        return new JAXBElement<ReadPerson>(_ReadPerson_QNAME, ReadPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonHealthProfile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "updatePersonHealthProfile")
    public JAXBElement<UpdatePersonHealthProfile> createUpdatePersonHealthProfile(UpdatePersonHealthProfile value) {
        return new JAXBElement<UpdatePersonHealthProfile>(_UpdatePersonHealthProfile_QNAME, UpdatePersonHealthProfile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "updatePerson")
    public JAXBElement<UpdatePerson> createUpdatePerson(UpdatePerson value) {
        return new JAXBElement<UpdatePerson>(_UpdatePerson_QNAME, UpdatePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonHealthProfileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "updatePersonHealthProfileResponse")
    public JAXBElement<UpdatePersonHealthProfileResponse> createUpdatePersonHealthProfileResponse(UpdatePersonHealthProfileResponse value) {
        return new JAXBElement<UpdatePersonHealthProfileResponse>(_UpdatePersonHealthProfileResponse_QNAME, UpdatePersonHealthProfileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readPersonResponse")
    public JAXBElement<ReadPersonResponse> createReadPersonResponse(ReadPersonResponse value) {
        return new JAXBElement<ReadPersonResponse>(_ReadPersonResponse_QNAME, ReadPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonMeasurement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "updatePersonMeasurement")
    public JAXBElement<UpdatePersonMeasurement> createUpdatePersonMeasurement(UpdatePersonMeasurement value) {
        return new JAXBElement<UpdatePersonMeasurement>(_UpdatePersonMeasurement_QNAME, UpdatePersonMeasurement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "deletePerson")
    public JAXBElement<DeletePerson> createDeletePerson(DeletePerson value) {
        return new JAXBElement<DeletePerson>(_DeletePerson_QNAME, DeletePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPersonMeasurementResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readPersonMeasurementResponse")
    public JAXBElement<ReadPersonMeasurementResponse> createReadPersonMeasurementResponse(ReadPersonMeasurementResponse value) {
        return new JAXBElement<ReadPersonMeasurementResponse>(_ReadPersonMeasurementResponse_QNAME, ReadPersonMeasurementResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadMeasureTypesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readMeasureTypesResponse")
    public JAXBElement<ReadMeasureTypesResponse> createReadMeasureTypesResponse(ReadMeasureTypesResponse value) {
        return new JAXBElement<ReadMeasureTypesResponse>(_ReadMeasureTypesResponse_QNAME, ReadMeasureTypesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonMeasurementResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "updatePersonMeasurementResponse")
    public JAXBElement<UpdatePersonMeasurementResponse> createUpdatePersonMeasurementResponse(UpdatePersonMeasurementResponse value) {
        return new JAXBElement<UpdatePersonMeasurementResponse>(_UpdatePersonMeasurementResponse_QNAME, UpdatePersonMeasurementResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPersonMeasureByDatesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readPersonMeasureByDatesResponse")
    public JAXBElement<ReadPersonMeasureByDatesResponse> createReadPersonMeasureByDatesResponse(ReadPersonMeasureByDatesResponse value) {
        return new JAXBElement<ReadPersonMeasureByDatesResponse>(_ReadPersonMeasureByDatesResponse_QNAME, ReadPersonMeasureByDatesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPersonListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readPersonListResponse")
    public JAXBElement<ReadPersonListResponse> createReadPersonListResponse(ReadPersonListResponse value) {
        return new JAXBElement<ReadPersonListResponse>(_ReadPersonListResponse_QNAME, ReadPersonListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "deletePersonResponse")
    public JAXBElement<DeletePersonResponse> createDeletePersonResponse(DeletePersonResponse value) {
        return new JAXBElement<DeletePersonResponse>(_DeletePersonResponse_QNAME, DeletePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPersonListByMeasurementResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "readPersonListByMeasurementResponse")
    public JAXBElement<ReadPersonListByMeasurementResponse> createReadPersonListByMeasurementResponse(ReadPersonListByMeasurementResponse value) {
        return new JAXBElement<ReadPersonListByMeasurementResponse>(_ReadPersonListByMeasurementResponse_QNAME, ReadPersonListByMeasurementResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SavePersonMeasurementResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.assignment.introsde/", name = "savePersonMeasurementResponse")
    public JAXBElement<SavePersonMeasurementResponse> createSavePersonMeasurementResponse(SavePersonMeasurementResponse value) {
        return new JAXBElement<SavePersonMeasurementResponse>(_SavePersonMeasurementResponse_QNAME, SavePersonMeasurementResponse.class, null, value);
    }

}
