package com.example.maplist.api.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PoiList {

	@SerializedName("poiList")
	@Expose
	private List<Poi> poiList = null;

	public List<Poi> getPoiList() {
		return poiList;
	}

	public void setPoiList(List<Poi> poiList) {
		this.poiList = poiList;
	}
}
