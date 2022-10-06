package com.europeandynamics.model;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.europeandynamics.model.enums.Type;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "property")
@AttributeOverride(name = "Id", column = @Column(name = "property_id"))
@Data
@ToString(exclude = {"propertyOwner", })
@NamedQuery(name = "Property.findAll",
query = "SELECT p FROM Property p")
public class Property extends BaseEntity {
	
	@NotBlank
	private String address;
	
	@NotBlank
	private LocalDate yearOfConstruction;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private Type type;

	@ManyToOne()
	@JoinColumn(name = "vat_code")
	private PropertyOwner propertyOwner;

	@OneToMany(mappedBy = "property", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<PropertyRepair> propertyRepairs = new LinkedHashSet<>();

	public Property() {}

	public Property(String Id, String address, LocalDate yearOfConstruction, Type type) {
		super(Id);
		this.address = address;
		this.yearOfConstruction = yearOfConstruction;
		this.type = type;
	}

	public void addPropertyRepair(PropertyRepair propertyRepair) {
		this.propertyRepairs.add(propertyRepair);
		propertyRepair.setProperty(this);
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

}
