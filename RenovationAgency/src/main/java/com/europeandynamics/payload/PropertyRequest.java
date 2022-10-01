package com.europeandynamics.payload;

import java.time.LocalDate;

import com.europeandynamics.model.enums.Type;

public class PropertyRequest {

	private String address;
	private LocalDate yearOfConstruction;
	private Type type;
	
	public PropertyRequest(String address, LocalDate yearOfConstruction, Type type) {
		super();
		this.address = address;
		this.yearOfConstruction = yearOfConstruction;
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getYearOfConstruction() {
		return yearOfConstruction;
	}

	public void setYearOfConstruction(LocalDate yearOfConstruction) {
		this.yearOfConstruction = yearOfConstruction;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	
	
}
