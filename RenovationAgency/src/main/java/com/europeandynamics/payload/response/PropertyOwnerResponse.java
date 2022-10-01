package com.europeandynamics.payload.response;

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
import lombok.ToString;

@Data
@Builder
public class PropertyOwnerResponse {
	
	private String name;
	private String surname;
	private String address;
	private String phoneNumber;
	private String email;
	private String username;

	private Role role;

	private Set<Property> properties = new LinkedHashSet<>();

	private Set<PropertyRepair> propertyRepairs = new LinkedHashSet<>();
	
	public PropertyOwnerResponse() {
	}

	public PropertyOwnerResponse(String name, String surname, String address, String phoneNumber, String email,
			String username, Role role, Set<Property> properties,
			Set<PropertyRepair> propertyRepairs) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.username = username;
		this.role = role;
		this.properties = properties;
		this.propertyRepairs = propertyRepairs;
	}

}
