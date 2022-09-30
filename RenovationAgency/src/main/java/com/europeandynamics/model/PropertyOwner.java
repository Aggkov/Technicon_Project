package com.europeandynamics.model;

import com.europeandynamics.model.enums.Role;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "property_owner")
@AttributeOverride(name = "Id", column = @Column(name = "vat_number"))
@Data
//@ToString(exclude = {"properties", "propertyRepairs"})
@NamedQuery(name = "PropertyOwner.findAll",
query = "SELECT po FROM PropertyOwner po")
public class PropertyOwner extends BaseEntity {


	private String name;
	private String surname;
	private String address;
	private String phoneNumber;
	private String email;
	private String username;
	private String password;

	@Enumerated(value = EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "propertyOwner", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Property> properties = new LinkedHashSet<>();

	@OneToMany(mappedBy = "propertyOwner", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<PropertyRepair> propertyRepairs = new LinkedHashSet<>();

	public PropertyOwner() {
	}

	public PropertyOwner(String Id, String name, String surname, String address, String phoneNumber, String email,
			String username, String password, Role role) {
		super(Id);
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.properties = new LinkedHashSet<>();
	}

	public void addProperty(Property property) {
		this.properties.add(property);
		property.setPropertyOwner(this);
	}

	public void addPropertyRepair(PropertyRepair propertyRepair) {
		this.propertyRepairs.add(propertyRepair);
		propertyRepair.setPropertyOwner(this);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(this.Id, address, email, name, password, phoneNumber, surname, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyOwner other = (PropertyOwner) obj;
		return Objects.equals(this.Id, other.Id) && Objects.equals(address, other.address)
				&& Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(surname, other.surname) && Objects.equals(username, other.username);
	}


}
