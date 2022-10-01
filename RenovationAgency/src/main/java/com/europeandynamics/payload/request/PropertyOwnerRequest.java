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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
