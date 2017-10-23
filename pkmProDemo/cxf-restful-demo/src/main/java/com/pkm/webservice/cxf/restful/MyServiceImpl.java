package com.pkm.webservice.cxf.restful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.*;
public class MyServiceImpl implements EmployeeWSRestFulService {
	
	private void init(){
		   User user = new User();
		   user.setId("1");
		   user.setName("bigtree");
//		   users.put(user.getId(), user);
		   user = new User();
		   user.setId("2");
		   user.setName("jackie");
//		   users.put(user.getId(), user);
		  }
		 
		 private HashMap<String, User> users = new HashMap<String,User>();
		 public MyServiceImpl(){
		   init();
		 }
		 public Response addUser(User user) {
		   users.put(user.getId(), user);
		   System.out.println("User entity to add: user id= "+user.getId()+" name="+user.getName());
		   System.out.println("adding user succeed");
		   System.out.println("total users in pool="+users.size());
		   return Response.ok().build();
		 }
		 public Response delUser(String id) {
		   User muser=users.remove(id);
		   if(muser == null) { 
		    // this user with this id not exist
		    System.out.println("delUser(): no user entry found to delete");
		    return Response.status(Status.BAD_REQUEST).build();
		    }
		   else  
		      return Response.ok().build();
		 }
		 public Response updateUser(User user) {
		   users.put(user.getId(), user);
		   System.out.println(users.get("1").getName());
		   return Response.ok().build();
		 }
		 public User getUserById(String id) {
		 User muser=users.get(id);
		 if(muser==null)
		 System.out.println("getUserById(): no user entry found");
		 return muser;
		 }
		  public List<User> findAllUsers() {
		   List<User> userlist = new ArrayList<User>();
		   Iterator userit =  users.keySet().iterator();
		   while(userit.hasNext()){
		    userlist.add(users.get(userit.next()));
		   }
		   return userlist;
		    }
}
