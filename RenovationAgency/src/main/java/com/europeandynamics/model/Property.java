package com.europeandynamics.model;

import com.europeandynamics.model.enums.Type;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "property")
@AttributeOverride(name = "Id", column = @Column(name = "property_id"))
@Data
public class Property extends BaseEntity {

	private String address;

	private LocalDate yearOfConstruction;

	@Enumerated(EnumType.STRING)
	private Type type;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vat_number")
	private PropertyOwner propertyOwner;

	@OneToMany(mappedBy = "property", cascade = CascadeType.PERSIST)
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
