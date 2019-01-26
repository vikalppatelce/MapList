package com.example.maplist.api;

import com.example.maplist.api.model.PoiList;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

  @GET(ApiConstants.LIST_URL)
  Single<PoiList> getList(
      @Query("p1Lat") String p1Lat,
      @Query("p1Lon") String p1Lon,
      @Query("p2Lat") String p2Lat,
      @Query("p2Lon") String p2Lon);
}
