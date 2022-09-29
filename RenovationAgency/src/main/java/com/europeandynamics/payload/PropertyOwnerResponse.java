package com.europeandynamics.payload;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.model.enums.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyOwnerResponse {
	
	private String name;
	private String surname;
	private String address;
	private String phoneNumber;
	private String email;
	private String username;
	private String password;

	private Role role;

	private Set<Property> properties = new LinkedHashSet<>();

	private Set<PropertyRepair> propertyRepairs = new LinkedHashSet<>();

	public PropertyOwnerResponse(String name, String surname, String address, String phoneNumber, String email,
			String username, String password, Role role, Set<Property> properties,
			Set<PropertyRepair> propertyRepairs) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.properties = properties;
		this.propertyRepairs = propertyRepairs;
	}
	

	
}