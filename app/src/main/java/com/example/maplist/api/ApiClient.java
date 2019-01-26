package com.example.maplist.api;

import com.example.maplist.api.model.PoiList;
import io.reactivex.Single;
import javax.inject.Inject;

public class ApiClient {

  private final Api api;

  @Inject
  public ApiClient(Api api) {
    this.api = api;
  }

  public Single<PoiList> getList(String p1Lat, String p1Lon, String p2Lat, String p2Lon) {
    return api.getList(p1Lat, p1Lon, p2Lat, p2Lon);
  }
}
