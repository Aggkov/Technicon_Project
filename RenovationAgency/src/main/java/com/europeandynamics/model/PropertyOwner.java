package com.europeandynamics.model;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "property_owner")
@AttributeOverride(name = "Id", column = @Column(name = "vat_number"))
//@Data
public class PropertyOwner extends BaseEntity {

//	@Id
////	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "vat_number")
//	private String VatNumber;

	private String name;
	private String surname;
	private String address;
	private String phoneNumber;
	private String email;
	private String username;
	private String password;

	@OneToMany(mappedBy = "propertyOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Property> properties = new LinkedHashSet<>();

	public PropertyOwner() {
	}

	public PropertyOwner(String Id, String name, String surname, String address, String phoneNumber, String email,
			String username, String password) {
		super(Id);
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.username = username;
		this.password = password;
		this.properties = new LinkedHashSet<>();
	}

	public void addProperty(Property property) {
		this.properties.add(property);
		property.setPropertyOwner(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropertyOwner [VAT=");
		builder.append(this.Id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", Address=");
		builder.append(address);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", email=");
		builder.append(email);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append("\n");
		return builder.toString();
	}

}
