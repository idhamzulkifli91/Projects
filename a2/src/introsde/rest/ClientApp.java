package introsde.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;    
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import introsde.rest.model.*;
public class ClientApp {
	static FileWriter outFile;
	static PrintWriter out;
	static Boolean myDB;
	static Boolean debug=true;
	public static void main(String[] args) throws IOException {
		 
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseURI());
		outFile = new FileWriter("log.txt");
		out = new PrintWriter(outFile);

		
		Scanner terminalInput = new Scanner(System.in);
		// // GET BASEURL/rest/salutation
		// // Accept: text/plain



		//Step 3.1. 
		println("Step 3.1");
		println("");
		int first_person_id=-1,last_person_id=-1;

		WebTarget service1 = service.path("/person");
		Response response = service1.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		//Response response = service.path("/person").request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		println("REQUEST: GET " + service1.getUri().toString());
		String entity = response.readEntity(String.class);

		if (response.getStatus() == Response.Status.OK.getStatusCode()){
			println("RESULT: OK ");
			println("HTTP Status:"+response.getStatus());
			println(service.path("/person").request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get().readEntity(String.class));
			if(myDB){
				ObjectMapper mapper = new ObjectMapper();
				mapper.setSerializationInclusion(Include.NON_NULL);
				List<Person> personList;
				try {
					personList = mapper.readValue(entity, mapper.getTypeFactory().constructCollectionType(List.class, Person.class));
					first_person_id = personList.get(0).getPersonID();
					last_person_id = personList.get(personList.size()-1).getPersonID();
					println("first ID: "+first_person_id);
					println("last ID : "+last_person_id);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("Please give me the first ID!");
				String answer = terminalInput.nextLine();
				first_person_id = Integer.parseInt(answer);
				System.out.println("Please give me the last ID!");
				answer = terminalInput.nextLine();
				last_person_id = Integer.parseInt(answer);
			}

		}
		else{
			println("RESULT: ERROR" );
			println("HTTP Status:"+response.getStatus());
		}
		println("");
		println("----------------------------------------" );
		println("Step 3.2");
		println("");

		//Step 3.2. 
		WebTarget service2 = service.path("/person/"+first_person_id);
		response = service2.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		//response = service.path("/person/"+first_person_id).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		println("REQUEST: GET " +service2.getUri().toString());
		entity = response.readEntity(String.class);

		if (response.getStatus() == Response.Status.OK.getStatusCode() || response.getStatus() == 202){
			println("RESULT: OK ");
			println("HTTP Status:"+response.getStatus());
			println(entity);
		}
		else{
			println("RESULT: ERROR" );
			println("HTTP Status:"+response.getStatus());
		}
		println("");
		println("----------------------------------------" );
		println("Step 3.3");
		println("");

		//Step 3.3. 
		Person p = new Person();
		ObjectMapper mapper = new ObjectMapper();
		if(myDB){
			
			mapper.setSerializationInclusion(Include.NON_NULL);
	
			
			try {
				p =  mapper.readValue(entity, mapper.getTypeFactory().constructType(Person.class));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String newname = "Chuck"/*p.getFirstName()+"123"*/;
			p.setFirstName(newname);
			println(newname);
		
			WebTarget service3 = service.path("/person/"+first_person_id);
			response = service3.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).put(Entity.entity(p, MediaType.APPLICATION_JSON));
			println(service3.getUri().toString());
			//response = service.path("/person/"+first_person_id).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).put(Entity.entity(p, MediaType.APPLICATION_JSON));
			try {	
				
				p =  mapper.readValue(entity, mapper.getTypeFactory().constructType(Person.class));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(p.getFirstName().equals(newname)){
				println("RESULT: OK ");  
				println("HTTP Status:"+response.getStatus());
				println(entity);
			}
			else{
				println("RESULT: ERROR" );
				println("HTTP Status:"+response.getStatus());
			}
		}
		else
		{
			System.out.println("Please give me an ID for modifying!");
			String idString = terminalInput.nextLine();
			int idToModify = Integer.parseInt(idString);
			String updated = "{ \"firstname\"     : \"Modified\",\"lastname\"      : \"Person\", \"bithdate\"     : \"1945-01-01\"}";
			//String updated = "{ \"firstName\"     : \"Modified\",\"lastName\"      : \"Person\", \"dateOfBirth\"     : \"1945-01-01\"}";
			WebTarget service3 = service.path("/person/"+idToModify);
			response = service3.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).put(Entity.entity(updated, MediaType.APPLICATION_JSON));
			//response = service.path("/person/"+idToModify).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).put(Entity.entity(updated, MediaType.APPLICATION_JSON));
			println("REQUEST: PUT " +service3.getUri().toString() +" BODY: "+ updated);
			entity = response.readEntity(String.class);
			
			if(response.getStatus() == 200 || response.getStatus() == 201 ||response.getStatus() == 202){
				//response = service.path("/person/"+first_person_id).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
				
				
				println("RESULT: OK" );
				println("HTTP Status:"+response.getStatus());
				println(entity);
				
			}
			else{
				println("RESULT: ERROR" );
				println("HTTP Status:"+response.getStatus());
			}
		}		
		
			
	
		
		
		println("");
		println("----------------------------------------" );
		println("Step 3.4");
		println("");
		
		if(myDB){
			File file = new File("chuckstring.txt");
		    FileInputStream fis = new FileInputStream(file);
		    byte[] data = new byte[(int)file.length()];
		    fis.read(data);
		    fis.close();
		    //
		     new String(data, "UTF-8");
			String chuckString =  new String(data, "UTF-8");
			p =  mapper.readValue(chuckString, mapper.getTypeFactory().constructType(Person.class));
			WebTarget service4 = service.path("/person");
			
			response = service4.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).post(Entity.entity(p, MediaType.APPLICATION_JSON));
			//response = service.path("/person").request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).post(Entity.entity(p, MediaType.APPLICATION_JSON));
			println("REQUEST: POST " +service4.getUri().toString() +" BODY: "+ chuckString);
		} 
		else
		{
			String chuckString =  " {\"firstname\"     : \"Chuck\", \"lastname\"      : \"Norris\", \"birthdate\"     : \"1945-01-01\" \"healthProfile\" : { \"weight\"  : 78.9, \"height\"  : 172 }}";
			//String chuckString =  " {\"firstName\"     : \"Chuck\", \"lastName\"      : \"Norris\", \"dateOfBirth\"     : \"1945-01-01\" }";
			WebTarget service4 = service.path("/person");
			response = service4.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).post(Entity.entity(chuckString, MediaType.APPLICATION_JSON));
			//response = service.path("/person").request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).post(Entity.entity(chuckString, MediaType.APPLICATION_JSON));
			println("REQUEST: POST " +service4.getUri().toString() +" BODY: "+chuckString);
		}
		
		entity = response.readEntity(String.class);
		if(response.getStatus()==200 || response.getStatus()==201||response.getStatus()==202)
		{
			println("RESULT: OK ");  
			println("HTTP Status:"+response.getStatus());
			println(entity);
		}else{
			println("RESULT: ERROR" );
			println("HTTP Status:"+response.getStatus());
		}
		int id = -1;
		if(myDB){
			try{
				p =  mapper.readValue(entity, mapper.getTypeFactory().constructType(Person.class));
				id = p.getPersonID();
				println("newID = " + id);
			}catch(Exception e){e.printStackTrace();}
		}
		else
		{
			System.out.println("Please give me an ID for delete!");
			String uriString = terminalInput.nextLine();
			id = Integer.parseInt(uriString);
		}
		
		println("");
		println("----------------------------------------" );
		println("Step 3.5");
		println("");
		if(id!=-1){
			WebTarget service5 = service.path("/person/"+ id);
			response = service5.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).delete();
			//response = service.path("/person/"+ id).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).delete();
			println("REQUEST: DELETE " +service5.getUri().toString());
			int responseAfterDelete =response.getStatus();
			println("HTTP response after DELETE: "+ responseAfterDelete);
			WebTarget service5_2 = service.path("/person/"+ id);
			response = service5_2.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
			//response = service.path("/person/"+ id).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
			println("REQUEST: GET " +service5_2.getUri().toString());
			if(response.getStatus()==404 || responseAfterDelete==204)
			{
				println("RESULT: OK ");  
				println("HTTP Status:"+response.getStatus());
				if(response.getStatus()!=404)
				println("[I suppose it is ok, because after succesfully created, DELETE returns with 204 and the object does not exist anymore.. ]");
				
			}else{
				println("RESULT: ERROR" );
				println("HTTP Status:"+response.getStatus());
			}
		}else{
			println("RESULT: ERROR" );
			println("HTTP Status: unsuccessful post before..");
		}
		
		println("");
		println("----------------------------------------" );
		println("Step 3.6");
		println("");
		
		WebTarget service6 = service.path("/measureTypes");
		response = service6.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		//response = service.path("/measureTypes").request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		println("REQUEST: GET " +service6.getUri().toString());
		if(response.getStatus()==200)
		{
			println("RESULT: OK ");  
			println("HTTP Status:"+response.getStatus());
			entity =response.readEntity(String.class);
			println(entity);
		}else{
			println("RESULT: ERROR" );
			println("HTTP Status:"+response.getStatus());
		}
		
		List<MeasureType> mtList;
		List<String> mtStrings = new ArrayList<String>();
		if(myDB){
			try {
				mtList = mapper.readValue(entity, mapper.getTypeFactory().constructCollectionType(List.class, MeasureType.class));
				
				for(MeasureType mt: mtList)
					mtStrings.add(mt.getDescription());
				}			
			catch(Exception e){e.printStackTrace();}
		}
		println("");
		println("----------------------------------------" );
		println("Step 3.7");
		println("");
		
		String measureTypeString="";
		int  measureId=-1;
		
		int succes=0;
		if(myDB){
			String mh="";
			for(String s : mtStrings){
				WebTarget service7 = service.path("/person/"+last_person_id +"/"+ s);
				response = service7.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
				//response = service.path("/person/"+last_person_id +"/"+ s).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
				println("REQUEST: GET " +service7.getUri().toString());
				mh = response.readEntity(String.class);
				if(response.getStatus()==200)
				{
					succes++;
					measureTypeString = s;
					entity = mh;
					List<Measure> ml = mapper.readValue(entity, mapper.getTypeFactory().constructCollectionType(List.class, Measure.class));
					if(ml.size()>0)
					measureId = ml.get(0).getId();
//					println("HTTP Status:"+200);
//					println(entity);
				}
			}
			//response = service.path("/person/"+last_person_id +"/"+ s).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
			if(response.getStatus()==200){			
				println("RESULT: OK ");
				println(mh);
			}
			else{
				println("RESULT: ERROR" );
				println("HTTP Status:"+response.getStatus());
			}
		}
		else{
			measureTypeString = "height";
			WebTarget service7 = service.path("/person/"+last_person_id +"/"+ measureTypeString);
			response = service7.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
			println("REQUEST: GET " +service7.getUri().toString());
			//response = service.path("/person/"+last_person_id +"/"+ measureTypeString).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
			if(response.getStatus()==200){			
				println("RESULT: OK ");
				println(response.readEntity(String.class));
			}		
			else{
				println("RESULT: ERROR" );
				println("HTTP Status:"+response.getStatus());
			}
		}
		
		println("");
		println("----------------------------------------" );
		println("Step 3.8");
		println("");
		int mid = -1;
		if(myDB)
			mid=measureId;
		else{
			System.out.println("Give me please a MeasureId of the Person with id" + first_person_id );
			String answer = terminalInput.nextLine();
			mid = Integer.parseInt(answer);
		}
		
		WebTarget service8 = service.path("/person/"+last_person_id +"/"+ measureTypeString+"/"+measureId);
		response = service8.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		println("REQUEST: GET " +service8.getUri().toString());
		//response = service.path("/person/"+last_person_id +"/"+ measureTypeString+"/"+measureId).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		entity = response.readEntity(String.class);
		//Measure m =  mapper.readValue(entity, mapper.getTypeFactory().constructType(Measure.class));	
		
		
		if(response.getStatus()==200)
		{
			println("RESULT: OK" );
			println("HTTP Status:"+response.getStatus());
			println(entity);
		} 			
		else{
			println("RESULT: ERROR" );
			println("HTTP Status:"+response.getStatus());
		}
		
		println("");
		println("----------------------------------------" );
		println("Step 3.9");
		println("");
		
		WebTarget service9 = service.path("/person/"+first_person_id +"/"+ measureTypeString);
		response = service9.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		println("REQUEST: GET " +service9.getUri().toString());
		//response = service.path("/person/"+first_person_id +"/"+ measureTypeString).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		
		entity = response.readEntity(String.class);
		List<Measure> ml = mapper.readValue(entity, mapper.getTypeFactory().constructCollectionType(List.class, Measure.class));
		int count = ml.size();
		
		String Mstring = "{ \"date\": \"2011-12-09\",\"value\": 72.0}";
		println(Mstring);
		Measure newm = mapper.readValue(Mstring, mapper.getTypeFactory().constructType( Measure.class));
		WebTarget service9_2 =  service.path("/person/"+first_person_id +"/"+ measureTypeString);
		response = service9_2.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).post(Entity.entity(Mstring, MediaType.APPLICATION_JSON));
		//response = service.path("/person/"+first_person_id +"/"+ measureTypeString).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).post(Entity.entity(Mstring, MediaType.APPLICATION_JSON));
		println("REQUEST: POST " +service9_2.getUri().toString()+" BODY: "+Mstring);
		entity = response.readEntity(String.class);
		println(entity);
		Measure createdMeasure = mapper.readValue(entity, mapper.getTypeFactory().constructType( Measure.class));
		println("HTTP status after post: "+response.getStatus());
		WebTarget service9_3 = service.path("/person/"+first_person_id +"/"+ measureTypeString);
		response = service9_3.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		println("REQUEST: GET " +service9_2.getUri().toString());
		//response = service.path("/person/"+first_person_id +"/"+ measureTypeString).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		entity = response.readEntity(String.class);
		ml = mapper.readValue(entity, mapper.getTypeFactory().constructCollectionType(List.class, Measure.class));
		if(ml.size()>count)
		{
			println("RESULT: OK" );
			println("HTTP Status:"+response.getStatus());
			println(entity);
		} 			
		else{
			println("RESULT: ERROR" );
			println("HTTP Status:"+response.getStatus());
		}
		
		println("");
		println("----------------------------------------" );
		println("Step 3.10");
		println("");
		
		double newValue = 90.0;
		Mstring = "{ \"date\": \"2011-12-09\",\"value\": "+newValue+"}";
		//println(Mstring);
		//println("/person/"+first_person_id +"/"+ measureTypeString +"/"+createdMeasure.getId());
		WebTarget service10 = service.path("/person/"+first_person_id +"/"+ measureTypeString +"/"+createdMeasure.getId());
		response = service10.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).put(Entity.entity(Mstring, MediaType.APPLICATION_JSON));
		println("REQUEST: PUT " +service9_2.getUri().toString()+" BODY: "+Mstring);
		//response = service.path("/person/"+first_person_id +"/"+ measureTypeString +"/"+createdMeasure.getId()).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).put(Entity.entity(Mstring, MediaType.APPLICATION_JSON));
		entity = response.readEntity(String.class);
		//System.out.println(entity);
		if(myDB){
			Measure updatedMeasure = mapper.readValue(entity, mapper.getTypeFactory().constructType( Measure.class));
			
			if(updatedMeasure.getValue() == newValue)
			{
				println("RESULT: OK" );
				println("HTTP Status:"+response.getStatus());
				println(entity);
			} 			
			else{
				println("RESULT: ERROR" );
				println("HTTP Status:"+response.getStatus());
			}
		}else
		{
			if(response.getStatus() == 200 || response.getStatus()==201|| response.getStatus()==202)
			{
				println("RESULT: OK" );
				println("HTTP Status:"+response.getStatus());
				println(entity);
			} 			
			else{
				println("RESULT: ERROR" );
				println("HTTP Status:"+response.getStatus());
			}
		}
		println("");
		println("----------------------------------------" );
		println("Step 3.11");
		println("");		
		
		String before = "2010-05-05";
		String after = "2000-01-01";
		
		//response = service.path("/person/"+first_person_id +"/"+ measureTypeString +"?before="+before+"&after="+after).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		WebTarget service11 = service.path("person/"+first_person_id+"/height");
		response = service11.queryParam("before", before).queryParam("after", after).request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		println("REQUEST: GET " +service11.getUri().toString());
		entity = response.readEntity(String.class);
		
		
		
		if(response.getStatus() == 200)
		{
			println("RESULT: OK" );
			println("HTTP Status:"+response.getStatus());
			println(entity);
		} 			
		else{
			println("RESULT: ERROR" );
			println("HTTP Status:"+response.getStatus());
		}
		
		println("");
		println("----------------------------------------" );
		println("Step 3.12");
		println("");		
		
		String mt = "height";
		String min = "150";
		String max = "170";
		WebTarget ser = service.path("person").queryParam("measureType", mt).queryParam("max", max).queryParam("min", min);
		println("REQUEST: GET " +ser.getUri().toString());
		response = ser.request().accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML).get();
		entity = response.readEntity(String.class);
		
		
		
		if(response.getStatus() == 200)
		{
			println("RESULT: OK" );
			println("HTTP Status:"+response.getStatus());
			println(entity);
		} 			
		else{
			println("RESULT: ERROR" );
			println("HTTP Status:"+response.getStatus());
		}
		
		
		
		
		
		out.close();


	}

	private static URI getBaseURI() {
		Scanner terminalInput = new Scanner(System.in);
		System.out.println("Please give the BASE_URL!");
		String uriString = terminalInput.nextLine();
		if(uriString.equals(""))
		{
			uriString = "http://localhost:443/sdelab";
			myDB = true;
			//uriString = "http://localhost:443/sdelab_kp";
		}
		else 
			myDB = false;
		return UriBuilder.fromUri(      		
				uriString).build();
	}
	private static void println(String s) {
		System.out.println(s);
		//out.println(s);

	}
}