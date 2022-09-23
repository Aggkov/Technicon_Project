package com.europeandynamics.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.europeandynamics.model.enums.Type;

@Entity
@Table(name = "property")
@AttributeOverride(name = "Id", column = @Column(name = "property_id"))
public class Property extends BaseEntity {

//	@Id
////	@GeneratedValue(strategy = GenerationType.AUTO)
//	private String propertyId;

	private String address;

	private LocalDate yearOfConstruction;

	@Enumerated(EnumType.STRING)
	private Type type;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "vat_number")
	private PropertyOwner propertyOwner;

	public Property() {
	}

	public Property(String Id, String address, LocalDate yearOfConstruction, Type type) {
		super(Id);
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

	public PropertyOwner getPropertyOwner() {
		return propertyOwner;
	}

	public void setPropertyOwner(PropertyOwner propertyOwner) {
		this.propertyOwner = propertyOwner;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, this.Id, type, yearOfConstruction);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		return Objects.equals(address, other.address) && Objects.equals(this.Id, other.Id) && type == other.type
				&& Objects.equals(yearOfConstruction, other.yearOfConstruction);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Property [propertyId=");
		builder.append(this.Id);
		builder.append(", address=");
		builder.append(address);
		builder.append(", yearOfConstruction=");
		builder.append(yearOfConstruction);
		builder.append(", type=");
		builder.append(type);
		builder.append(", propertyOwner=");
		builder.append(propertyOwner);
		builder.append("]");
		return builder.toString();
	}

}
