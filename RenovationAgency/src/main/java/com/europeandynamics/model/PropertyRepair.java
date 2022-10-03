package com.europeandynamics.model;

import com.europeandynamics.model.enums.RepairStatus;
import com.europeandynamics.model.enums.RepairType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "property_repair")
@AttributeOverride(name = "Id", column = @Column(name = "property_repair_id"))
@Data
@ToString(exclude = {"propertyOwner", "property"})
public class PropertyRepair extends BaseEntity {

	private LocalDateTime dateTimeOfRepair;

	private String shortDescription;

	@Enumerated(EnumType.STRING)
	private RepairType repairType;

	@Enumerated(EnumType.STRING)
	private RepairStatus repairStatus;

	private double costOfRepair;

	@Lob
	private String longDescription;

	@ManyToOne(
//			cascade = CascadeType.ALL, 
//			fetch = FetchType.LAZY
	)
	@JoinColumn(name = "vat_number")
	private PropertyOwner propertyOwner;

	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;

	public PropertyRepair() {
	}

	public PropertyRepair(String Id, LocalDateTime dateTimeOfRepair, String shortDescription, String longDescription,
			RepairType repairType, RepairStatus repairStatus, double costOfRepair) {
		super(Id);
		this.dateTimeOfRepair = dateTimeOfRepair;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.repairType = repairType;
		this.repairStatus = repairStatus;
		this.costOfRepair = costOfRepair;
	}
	
	

	

	@Override
	public int hashCode() {
		return Objects.hash(costOfRepair, dateTimeOfRepair, longDescription, property, propertyOwner, repairStatus,
				repairType, shortDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyRepair other = (PropertyRepair) obj;
		return Double.doubleToLongBits(costOfRepair) == Double.doubleToLongBits(other.costOfRepair)
				&& Objects.equals(dateTimeOfRepair, other.dateTimeOfRepair)
				&& Objects.equals(longDescription, other.longDescription) && Objects.equals(property, other.property)
				&& Objects.equals(propertyOwner, other.propertyOwner) && repairStatus == other.repairStatus
				&& repairType == other.repairType && Objects.equals(shortDescription, other.shortDescription);
	}


}
