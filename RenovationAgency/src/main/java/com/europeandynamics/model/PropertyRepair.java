package com.europeandynamics.model;

import com.europeandynamics.model.enums.RepairStatus;
import com.europeandynamics.model.enums.RepairType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "property_repair")
@AttributeOverride(name = "Id", column = @Column(name = "property_repair_id"))
@Data
@ToString(exclude = {"propertyOwner", "property"})
public class PropertyRepair extends BaseEntity {
	
	@NotBlank
	private LocalDateTime dateTimeOfRepair;
	
	@NotBlank
	private String shortDescription;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private RepairType repairType;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private RepairStatus repairStatus;
	
	@NotBlank
	private BigDecimal costOfRepair;
	
//	@NotBlank
	@Lob
	private String longDescription;

	@ManyToOne()
	@JoinColumn(name = "vat_code")
	private PropertyOwner propertyOwner;

	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;

	public PropertyRepair() {
	}

	public PropertyRepair(String Id, LocalDateTime dateTimeOfRepair, String shortDescription, String longDescription,
			RepairType repairType, RepairStatus repairStatus, BigDecimal costOfRepair) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		PropertyRepair that = (PropertyRepair) o;
		return Objects.equals(dateTimeOfRepair, that.dateTimeOfRepair) && Objects.equals(shortDescription, that.shortDescription)
				&& repairType == that.repairType && repairStatus == that.repairStatus
				&& Objects.equals(costOfRepair, that.costOfRepair) && Objects.equals(longDescription, that.longDescription)
				&& Objects.equals(propertyOwner, that.propertyOwner) && Objects.equals(property, that.property);
	}
}
