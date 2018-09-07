package com.redhat.proksch.demo.datagridaccess;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Warehouse {

	public Warehouse() {}
	private String zip;
	private String city;
        private String street;
	private String name;
	private String state;
	private String id;

	public void   setZip(String zip) { this.zip = zip; }
	public String getZip() { return this.zip; }

	public void   setCity(String city) { this.city = city; }
	public String getCity() { return this.city; }

	public void   setStreet(String street) { this.street = street; }
	public String getStreet() { return this.street; }

	public void   setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void   setState(String state) { this.state = state; }
	public String getState() { return this.state; }

	public void   setId(String id) { this.id = id; }
	public String getId() { return this.id; }
}
