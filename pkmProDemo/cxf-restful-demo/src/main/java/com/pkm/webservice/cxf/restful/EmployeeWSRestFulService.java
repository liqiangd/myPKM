package com.pkm.webservice.cxf.restful;

import java.util.*;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
/**
 * 
 * @author bigtree
 * restful URI 匹配原理，它是咋构成的? access the below page
 *http://blog.csdn.net/bigtree_3721/article/details/51158758
 *
 */
@Path("/")
public interface EmployeeWSRestFulService {
	/**
	 * JSON提交
	 * url:
	 * http://localhost:8080/MyWebserviceRestfulSpringServer/WSRest/userws/addUser
	 * Json format:{"user":{"id":123,"name":"newBook"}}
	 * @param book
	 * @return
	 */
	@Path("/addUser/")
	@POST
	@Consumes({"application/xml"})
	Response addUser(User user);
	  
	@Path("/delUser/{id}/")
	@DELETE
	@Consumes({"application/json","application/xml"}) 
	Response delUser(@PathParam("id") String id);
	  
	@Path("/updateUser/")
	@PUT
	@Consumes({"application/json","application/xml"})   
	Response updateUser(User user);
	 
	  /* http://localhost:8080/MyWebserviceRestfulSpringServer/WSRest/userws/getUserById/1/
	   * JSON: response content as below
	   * 
	   * HTTP/1.1 200 OK
	   * {"user":{"id":1,"name":"bigtree"}}
	   */
	/*
	 * XML response content as below
	 * 
	 * HTTP/1.1 200 OK
      <?xml version="1.0" encoding="UTF-8" standalone="yes"?><user><id>1</id><name>bigtree</name></user>
	 * 
	 */
	
	@Path("/getUserById/{id}/")
	@GET
	@Produces({"application/json","application/xml"}) //json is high priority
	User getUserById(@PathParam("id") String id);

	  /* http://localhost:8080/MyWebserviceRestfulSpringServer/WSRest/userws/
	   * response:
	   * HTTP/1.1 200 OK
	   * {"user":[{"id":1,"name":"bigtree"},{"id":2,"name":"jackie"}]}
	   * 
	   */
	@Path("/")
	@GET
	//json is high priority, default is application/xml
	@Produces({"application/json","application/xml"}) 
	List<User> findAllUsers();
}
