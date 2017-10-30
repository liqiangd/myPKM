package com.pkm.webservice.cxf.restful;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlAccessorType(XmlAccessType.FIELD) 
@XmlRootElement(name = "User")
public class User {

	private String id;
	private String name;

//	@XmlElement
	private Class classes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class getClasses() {
		return classes;
	}

	public void setClasses(Class classes) {
		this.classes = classes;
	}

}
