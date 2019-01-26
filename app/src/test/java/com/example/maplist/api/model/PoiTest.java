package com.example.maplist.api.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PoiTest {

  private final String type = "Taxi";
  private final Double latitude = 20.0;
  private Coordinate coordinateObj = new Coordinate(20.0, 15.0);

  @Mock
  Poi poi;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    Mockito.when(poi.getFleetType()).thenReturn(type);
    Mockito.when(poi.getCoordinate()).thenReturn(coordinateObj);
  }

  @Test
  public void testPoiType() {
    Mockito.when(poi.getFleetType()).thenReturn(type);
    Assert.assertEquals("Taxi", poi.getFleetType());
  }

  @Test
  public void testPoiCordinateLatitude() {
    Mockito.when(poi.getCoordinate()).thenReturn(coordinateObj);
    Assert.assertEquals(20.0, poi.getCoordinate().getLatitude(), 0);
  }

  @Test
  public void testPoiCordinateLongitude() {
    Mockito.when(poi.getCoordinate()).thenReturn(coordinateObj);
    Assert.assertEquals(15.0, poi.getCoordinate().getLongitude(), 0);
  }

  @After
  public void clearResource() {
    poi = null;
  }
}
