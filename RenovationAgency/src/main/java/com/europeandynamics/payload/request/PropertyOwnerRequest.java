package com.europeandynamics.payload.request;

import lombok.Data;

@Data
public class PropertyOwnerRequest {

    private String address;

    private String email;

    private String password;

    public PropertyOwnerRequest(String address, String email, String password) {
        this.address = address;
        this.email = email;
        this.password = password;
    }

   
}
