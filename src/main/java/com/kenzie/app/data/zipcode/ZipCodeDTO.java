package com.kenzie.app.data.zipcode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipCodeDTO {
    private String state;
    private List<Place> places;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
