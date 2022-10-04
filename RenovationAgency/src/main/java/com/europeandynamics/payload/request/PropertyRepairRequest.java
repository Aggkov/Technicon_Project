package com.europeandynamics.payload.request;

import com.europeandynamics.model.enums.RepairStatus;
import com.europeandynamics.model.enums.RepairType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyRepairRequest {

    @NotBlank
    private String propertyRepairId;

    @NotBlank
    private LocalDateTime dateTimeOfRepair;

    @NotBlank
    private String shortDescription;

    @NotBlank
    private RepairType repairType;

    @NotBlank
    private RepairStatus repairStatus;

    @NotBlank
    private BigDecimal costOfRepair;

    @NotBlank
    private String longDescription;

    @NotBlank
    private String propertyOwnerId;

    @NotBlank
    private String propertyId;

}
