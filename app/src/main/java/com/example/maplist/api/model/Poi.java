package com.example.maplist.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Poi {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("coordinate")
	@Expose
	private Coordinate coordinate;
	@SerializedName("fleetType")
	@Expose
	private String fleetType;
	@SerializedName("heading")
	@Expose
	private Double heading;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public String getFleetType() {
		return fleetType;
	}

	public void setFleetType(String fleetType) {
		this.fleetType = fleetType;
	}

	public Double getHeading() {
		return heading;
	}

	public void setHeading(Double heading) {
		this.heading = heading;
	}

}
