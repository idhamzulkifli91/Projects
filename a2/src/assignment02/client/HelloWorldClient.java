package assignment02.client;

import introsde.rest.model.HealthProfile;
import introsde.rest.model.Measure;
import introsde.rest.model.MeasureType;
import introsde.rest.model.Person;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloWorldClient {

	static int first_person_id;
	static int last_person_id;
	static int newID;
	static String name;
	static String[] measure_types;
	static int measure_id;
	static String measureType;
	static Measure measure;

	public static void main(String[] args) throws Exception {

		ClientConfig config = new ClientConfig();

		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getBaseURI());

		System.out.println("==========Step 3.1==========");
		getRequestOne(target);

		System.out.println("==========Step 3.2==========");
		Person p = getRequestTwo(target, first_person_id);

		// Person p = new Person();
		// p.setBirthdate("1950-01-01");
		name = p.getFirstName();
		p.setFirstName("Ana");
		// p.setLastname("Jasim");

		System.out.println("==========Step 3.3==========");
		getRequestThree(target, first_person_id, p);

		p = new Person();
		p.setDateOfBirth("1950-01-01");
		p.setFirstName("Anna");
		p.setLastName("Jasim");
		// p.sethProfile(hp);

		System.out.println("==========Step 3.4==========");
		newID = getRequestFour(target, p);

		System.out.println("==========Step 3.5==========");
		getRequestFive(target, newID);

		System.out.println("==========Step 3.6==========");
		getRequestSix(target);

		System.out.println("==========Step 3.7==========");
		getRequestSeven(target);

		System.out.println("==========Step 3.8==========");
		getRequestEight(target, 1);

		System.out.println("==========Step 3.9==========");
		getRequestNine(target);

		System.out.println("==========Step 3.10==========");
		getRequestTen(target);

		System.out.println("==========Step 3.11==========");
		getRequestEleven(target, 1);

		System.out.println("==========Step 3.12==========");
		getRequestTwelve(target);
	}

	private static URI getBaseURI() {

		return UriBuilder.fromUri("http://localhost:443/sdelab/")
				.build();
	}

	// Step 3.1. Send R#1 (GET BASE_URL/person). Calculate how many people
	// are in the response. If more than 2, result is OK, else is ERROR
	// (less than 3 persons). Save into a variable id of the first person
	// (first_person_id) and of the last person (last_person_id)
	public static void getRequestOne(WebTarget target) {
		WebTarget personPath = target.path("person");

		Response res = personPath.request().accept(MediaType.APPLICATION_XML)
				.get();

		List<Person> list = (List<Person>) res
				.readEntity(new GenericType<List<Person>>() {
				});
//		if (list.size() > 2) {
//			getResponseTemplateCustom(1, res, "GET", personPath.getUri(), "OK");
//		} else {
//			getResponseTemplateCustom(1, res, "GET", personPath.getUri(),
//					"ERROR");
//		}

		System.out.println(list.toString() + "\n");

		// if
		// (res.getMediaType().toString().equalsIgnoreCase("application/json"))
		// {
		// objToJSON(list);
		// } else if (res.getMediaType().toString()
		// .equalsIgnoreCase("application/xml")) {
		// objToXML(list);
		// }

		first_person_id = list.get(0).getPersonID();
		last_person_id = list.get(list.size() - 1).getPersonID();

	}

	// Step 3.2. Send R#2 for first_person_id. If the responses for this is
	// 200 or 202, the result is OK.
	public static Person getRequestTwo(WebTarget target, int id) {

		WebTarget personPath = target.path("person").path(Integer.toString(id));

		Builder req = personPath.request().accept(MediaType.APPLICATION_XML);

		Response res = req.get(Response.class);

		if (res.getStatus() == 200 || res.getStatus() == 202) {
			Person p = res.readEntity(Person.class);
			getResponseTemplate(2, res, "GET", personPath.getUri());
			System.out.println(p.toString() + "\n");

			return p;
		}

		return null;
	}

	// Step 3.3. Send R#3 for first_person_id changing the firstname. If the
	// responses has the name changed, the result is OK.

	public static void getRequestThree(WebTarget target, int id, Person p) {

		WebTarget personPath = target.path("person").path(Integer.toString(id));

		Builder req = personPath.request().accept(MediaType.APPLICATION_XML);

		Entity<Person> xml = Entity.xml(p);
		Response res = req.put(xml);

		Person person = res.readEntity(Person.class);

		if ((res.getStatus() == 200 || res.getStatus() == 201)
				&& name != person.getFirstName()) {
			getResponseTemplate(3, res, "PUT", personPath.getUri());
			System.out.println(person.toString() + "\n");
		}
	}

	// Step 3.4. Send R#4 to create the following person (first using JSON
	// and then using XML). Store the id of the new person. If the answer is
	// 201 (200 or 202 are also applicable) with a person in the body who
	// has an ID, the result is OK.

	public static int getRequestFour(WebTarget target, Person p) {

		WebTarget personPath = target.path("person");

		Builder req = personPath.request().accept(MediaType.APPLICATION_JSON);

		Response res = req.post(Entity.json(p));

		// TODO

		// Person person = res.readEntity(Person.class);
		// int personId = person.getPersonId();

		getResponseTemplate(4, res, "POST", personPath.getUri());
		if ((res.getStatus() == 200 || res.getStatus() == 201 || res
				.getStatus() == 202)) { // && personId != 0
			// System.out.println(person.toString() + "\n");
		}

		WebTarget path = target.path("person");

		Builder req1 = path.request().accept(MediaType.APPLICATION_XML);

		Person pNew = req1.post(Entity.xml(p), Person.class);

		Response res1 = req1.post(Entity.xml(p));

		// Person person = res.readEntity(Person.class);

		int d = pNew.getPersonID();

		if ((res1.getStatus() == 200 || res1.getStatus() == 201 || res1
				.getStatus() == 202) && d != 0) {
			getResponseTemplate(4, res1, "POST", path.getUri());
			System.out.println(res1.readEntity(String.class) + "\n");
		}

		return d;
	}

	// Step 3.5. Send R#5 for the person you have just created. Then send
	// R#1 with the id of that person. If the answer is 404, your result
	// must be OK.

	public static void getRequestFive(WebTarget target, int id) {

		WebTarget personPath = target.path("person").path(Integer.toString(id));

		Builder req = personPath.request().accept(MediaType.APPLICATION_XML);

		Response res = req.delete(Response.class);

		if (res.getStatus() == 200 || res.getStatus() == 202) {

			getResponseTemplateCustom(5, res, "DELETE", personPath.getUri(),
					"OK");

			res = req.get(Response.class);

			if (res.getStatus() == 404) {
				getResponseTemplateCustom(1, res, "GET", personPath.getUri(),
						"OK");

				String resultString = res.readEntity(String.class);
				System.out.println(resultString + "\n");
			}
		}
	}

	// Step 3.6. Follow now with the R#9 (GET BASE_URL/measureTypes). If
	// response contains more than 2 measureTypes - result is OK, else is
	// ERROR (less than 3 measureTypes). Save all measureTypes into array
	// (measure_types)
	public static void getRequestSix(WebTarget target) {

		WebTarget path = target.path("measureTypes");

		Builder req = path.request().accept(MediaType.APPLICATION_XML);

		Response res = req.get(Response.class);

		// List<MeasureType> list = res.readEntity(new
		// GenericType<List<MeasureType>>() {});

		// TODO

		MeasureType list = res.readEntity(MeasureType.class);
/*
		if (list.get().size() > 2) {
			getResponseTemplateCustom(9, res, "GET", path.getUri(), "OK");
		} else {
			getResponseTemplateCustom(9, res, "GET", path.getUri(), "ERROR");
		}
		measure_types = new String[list.get().size()];
		for (int i = 0; i < list.get().size(); i++) {
			measure_types[i] = list.get().get(i);
		}
*/
		System.out.println(list.toString() + "\n");
	}

	// Step 3.7. Send R#6 (GET BASE_URL/person/{id}/{measureType}) for the
	// first person you obtained at the beginning and the last person, and
	// for each measure types from measure_types. If no response has at
	// least one measure - result is ERROR (no data at all) else result is
	// OK. Store one measure_id and one measureType.
	public static void getRequestSeven(WebTarget target) {

		boolean hasMeasure = false;
		Response res = null;

		WebTarget personPath = target.path("person").path(
				Integer.toString(first_person_id));
		for (int i = 0; i < measure_types.length; i++) {
			Builder req = personPath.path(measure_types[i]).request()
					.accept(MediaType.APPLICATION_XML);

			res = req.get(Response.class);

			List<Measure> list = res
					.readEntity(new GenericType<List<Measure>>() {
					});
			if (list.size() > 0) {
				hasMeasure = true;
			}
			if (i == 0) {
				measure_id = list.get(0).getId();
				measureType = measure_types[0];
			}
		}

		personPath = target.path("person").path(
				Integer.toString(last_person_id));
		for (int i = 0; i < measure_types.length; i++) {
			Builder req = personPath.path(measure_types[i]).request()
					.accept(MediaType.APPLICATION_XML);

			res = req.get(Response.class);

			List<Measure> list = res
					.readEntity(new GenericType<List<Measure>>() {
					});
			if (list.size() > 0) {
				hasMeasure = true;
			}
		}

		if (res != null) {
			if (hasMeasure) {
				getResponseTemplateCustom(6, res, "GET", personPath.getUri(),
						"OK");
			} else {
				getResponseTemplateCustom(6, res, "GET", personPath.getUri(),
						"ERROR");
			}
		}

		System.out.println("\n");
	}

	// Step 3.8. Send R#7 (R#6 written in assignment web page) (GET
	// BASE_URL/person/{id}/{measureType}/{mid}) for
	// the stored measure_id and measureType. If the response is 200, result
	// is OK, else is ERROR.

	public static void getRequestEight(WebTarget target, int id) {

		WebTarget personPath = target.path("person").path(Integer.toString(id))
				.path(measureType).path(Integer.toString(measure_id));

		Builder req = personPath.request().accept(MediaType.APPLICATION_XML);

		Response res = req.get(Response.class);

		getResponseTemplate(7, res, "GET", personPath.getUri());

		String resultString = res.readEntity(String.class);
		System.out.println(resultString + "\n");
	}

	// Step 3.9. Choose a measureType from measure_types and send the
	// request R#6 (GET BASE_URL/person/{first_person_id}/{measureType}) and
	// save count value (e.g. 5 measurements). Then send R#8 (POST
	// BASE_URL/person/{first_person_id}/{measureTypes}) with the
	// measurement specified below. Follow up with another R#6 as the first
	// to check the new count value. If it is 1 time more - print OK, else
	// print ERROR. Remember, first with JSON and then with XML as
	// content-types

	public static void getRequestNine(WebTarget target) {

		// measureType[0] ==> weight
		WebTarget personPath = target.path("person")
				.path(Integer.toString(first_person_id)).path(measure_types[0]);
		Builder req = personPath.request().accept(MediaType.APPLICATION_XML);

		Response res = req.get(Response.class);
		List<Measure> list = res.readEntity(new GenericType<List<Measure>>() {
		});
		getResponseTemplate(6, res, "GET", personPath.getUri());
		// String resultString = res.readEntity(String.class);
		System.out.println(list.toString() + "\n");

		int count = list.size();

		// for (int i = 0; i < measureTypes.length; i++) {
		personPath = target.path("person")
				.path(Integer.toString(first_person_id)).path(measure_types[0]);
		req = personPath.request().accept(MediaType.APPLICATION_XML);

		Measure m = new Measure();
		m.setDate("2011-12-09");
		m.setValue(72);

		Response res1 = req.post(Entity.xml(m));
		measure = res1.readEntity(Measure.class);
		getResponseTemplate(8, res1, "POST", personPath.getUri());
		System.out.println(measure.toString() + "\n");
		// }

		personPath = target.path("person")
				.path(Integer.toString(first_person_id)).path(measure_types[0]);
		req = personPath.request().accept(MediaType.APPLICATION_XML);

		Response res2 = req.get(Response.class);
		list = res2.readEntity(new GenericType<List<Measure>>() {
		});
		getResponseTemplate(6, res2, "GET", personPath.getUri());
		System.out.println(list.toString() + "\n");

		if (list.size() == count + 1) {
			System.out
					.println("========================OK============================");
		} else {
			System.out
					.println("========================ERROR==========================");
		}

		System.out.println("\n");
	}

	// Step 3.10. Send R#10 using the {mid} or the measure created in the
	// previous step and updating the value at will. Follow up with at R#6
	// to check that the value was updated. If it was, result is OK, else is
	// ERROR.

	public static void getRequestTen(WebTarget target) {

		// WebTarget personPath = target.path("person")
		// .path(Integer.toString(measure.getPerson().getPersonId()))
		// .path(measure.getMeasureType().getMeasureName())
		// .path(Integer.toString(measure.getMeasureId()));

		WebTarget personPath = target.path("person")
				.path(Integer.toString(first_person_id)).path(measure_types[0])
				.path(Integer.toString(measure.getId()));

		Builder req = personPath.request().accept(MediaType.APPLICATION_XML);

		Measure m = new Measure();
		m.setValue(71);
		m.setDate("2011-12-09");

		Response res = req.put(Entity.xml(m));
		// Measure mNew = res.readEntity(Measure.class);
		getResponseTemplate(10, res, "PUT", personPath.getUri());

		personPath = target.path("person")
				.path(Integer.toString(first_person_id)).path(measure_types[0])
				.path(Integer.toString(measure.getId()));

		req = personPath.request().accept(MediaType.APPLICATION_XML);

		Response res1 = req.get(Response.class);
		Measure mDB = res1.readEntity(Measure.class);
		getResponseTemplate(6, res1, "GET", personPath.getUri());
		System.out.println(mDB.toString() + "\n");

		System.out.println("");
		if (measure.getValue() != mDB.getValue()) {
			System.out
					.println("========================OK============================");
		} else {
			System.out
					.println("========================ERROR==========================");
		}
		System.out.println("\n");
	}

	// Step 3.11. Send R#11 for a measureType, before and after dates given
	// by your fellow student (who implemented the server). If status is 200
	// and there is at least one measure in the body, result is OK, else is
	// ERROR

	public static void getRequestEleven(WebTarget target, int id) {

		// MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		// params.add("before", beforeDate);
		// params.add("after", afterDate);

		String beforeDate = "2011-12-09";
		String afterDate = "2007-12-09";

		WebTarget path = target.path("person").path(Integer.toString(id))
				.path(measureType).queryParam("before", beforeDate)
				.queryParam("after", afterDate);

		Builder req = path.request().accept(MediaType.APPLICATION_XML);

		Response res = req.get(Response.class);

		System.out.println(res.toString());

		List<Measure> list = null;
		if (res.getStatus() == 200) {
			list = res.readEntity(new GenericType<List<Measure>>() {
			});
			if (list.size() > 0) {
				getResponseTemplateCustom(11, res, "GET", path.getUri(), "OK");
			} else {
				getResponseTemplateCustom(11, res, "GET", path.getUri(),
						"ERROR");
			}
		}
		System.out.println(list.toString() + "\n");
	}

	// Step 3.12. Send R#12 using the same parameters as the previous
	// steps. If status is 200 and there is at least one person in the body,
	// result is OK, else is ERROR

	public static void getRequestTwelve(WebTarget target) {

		String max = "86";
		String min = "71";	

		WebTarget path = target.path("person")
				.queryParam("measureType", measureType).queryParam("max", max)
				.queryParam("min", min);

		Builder req = path.request().accept(MediaType.APPLICATION_XML);

		Response res = req.get(Response.class);

		System.out.println(res.toString());

		if (res.getStatus() == 200) {
			List<Person> list = res.readEntity(new GenericType<List<Person>>() {
			});			
			if (list.size() > 0) {
				getResponseTemplateCustom(11, res, "GET", path.getUri(), "OK");
				System.out.println(list.toString() + "\n");
			} else {
				getResponseTemplateCustom(11, res, "GET", path.getUri(),
						"ERROR");
			}
		}
	}

	public static void getResponseTemplate(int id, Response res, String method,
			URI path) {

		// Request #7: GET /person/5/weight/899 Accept: APPLICATION/XML
		// Content-Type: APPLICATION/XML
		// => Result: OK
		// => HTTP Status: 200

		String content_type = null;
		for (Entry<String, List<String>> header : res.getStringHeaders()
				.entrySet()) {
			if (header.getKey().equalsIgnoreCase("Content-type")) {
				content_type = header.getValue().toString();
			}
		}

		String request = "Request #" + id + ": " + method + " " + path
				+ " Accept: " + res.getMediaType() + " Content-Type: "
				+ content_type;
		String result = "=> Result: " + res.getStatusInfo();
		String status = "=> HTTP Status: " + res.getStatus();

		System.out.println(request);
		System.out.println(result);
		System.out.println(status);
	}

	public static void getResponseTemplateCustom(int id, Response res,
			String method, URI path, String result) {

		String content_type = null;
		for (Entry<String, List<String>> header : res.getStringHeaders()
				.entrySet()) {
			if (header.getKey().equalsIgnoreCase("Content-type")) {
				content_type = header.getValue().toString();
			}
		}

		String request = "Request #" + id + ": " + method + " " + path
				+ " Accept: " + res.getMediaType() + " Content-Type: "
				+ content_type;
		String resultString = "=> Result: " + result;
		String status = "=> HTTP Status: " + res.getStatus();

		System.out.println(request);
		System.out.println(resultString);
		System.out.println(status);
	}

	public static void objToXML(List<Person> people) {
		try {

			PersonList list = new PersonList();
			list.set(people);

			JAXBContext jaxbContext = JAXBContext.newInstance(PersonList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			jaxbMarshaller.marshal(list, System.out);

		} catch (JAXBException e) {
			// some exception occured
			System.out.println(e);
		}
	}

	public static void objToJSON(List<Person> people) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writeValue(System.out, people);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@XmlRootElement(name = "people")
	static class PersonList {

		@XmlElement(name = "person")
		private List<Person> list;

		public PersonList() {
			list = new ArrayList<Person>();
		}

		public void add(Person p) {
			list.add(p);
		}

		public void set(List<Person> list) {
			this.list = list;
		}
	}
}