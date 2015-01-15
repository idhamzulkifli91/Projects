package introsde.rest.resources;

import introsde.rest.model.MeasureType;
import introsde.rest.model.Person;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Stateless
@LocalBean//Will map the resource to the URL /ehealth/v2
@Path("/measureTypes")
public class MeasureTypeResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	@GET
	@Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	public List<MeasureType> getMeasureBrowser() {
		System.out.println("Getting list of measures...");
	    List<MeasureType> mtl = MeasureType.getAll();
		return mtl;
	}

}
