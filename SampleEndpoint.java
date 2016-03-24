package sn.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import sn.filter.Fishtag;

@Path("/")
@Fishtag
public class SampleEndpoint {
	Logger LOGGER = Logger.getLogger(SampleEndpoint.class);
	
	@GET
	@Path("/hello/{name}")
	@Produces(MediaType.APPLICATION_TEXT)
	public String sayHi(@PathParam("name") String name){
	  LOGGER.info("Get-IN ==> sayHi()");
	  String message = "Hi M. " + name;
	  LOGGER.info("Get-OUT ==> sayHi()");
	  return message;
	}
}
