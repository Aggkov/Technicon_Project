package com.europeandynamics.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PropertyOwnerRequest {
	
	@NotBlank
    private String address;
	
	@NotBlank
    private String email;
	
	@NotBlank
    private String password;

    public PropertyOwnerRequest(String address, String email, String password) {
        this.address = address;
        this.email = email;
        this.password = password;
    }

   
}
