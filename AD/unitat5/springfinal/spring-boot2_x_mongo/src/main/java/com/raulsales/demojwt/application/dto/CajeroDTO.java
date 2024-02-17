package com.raulsales.demojwt.application.dto;

import com.raulsales.demojwt.domain.entity.Address;
import com.raulsales.demojwt.domain.entity.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CajeroDTO {

    private String _id;
    private Location location;
    private List<Address> addresses;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
