package br.puc.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/Usuario")
public class webservice {

	@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/post")
	public String GravarUsuario(){
		
		System.out.println("teste funcionouss");
		
		return "ok";
	}
	

	@GET
    @Path("/get")
	@Produces("application/json")
	public String listar(){
		return "<p> Java Web Service </p>";   
		
	}
	
}
