package br.puc.resources;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import br.puc.dao.UsuarioDao;
import br.puc.entidades.Usuario;

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
	@Produces("application/xml")
	public List<Usuario> listar(){
		
		List<Usuario> list = UsuarioDao.listarUsuarios();
		
		return list;
	}
	
}
