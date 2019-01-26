package com.example.maplist.ui.viewmodel;

import com.example.maplist.api.ApiClient;
import com.example.maplist.api.model.PoiList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subscribers.TestSubscriber;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ListViewModelTest {

  @Mock
  ApiClient apiClient;

  private String demoResponse =
      "{ \"poiList\": [ { \"id\": 975666, \"coordinate\": { \"latitude\": 53.43196474812142, \"longitude\": 10.06555671476672 }, \"fleetType\": \"POOLING\", \"heading\": 353.21489557797486 }, { \"id\": 232307, \"coordinate\": { \"latitude\": 53.61041654032077, \"longitude\": 9.94110807375019 }, \"fleetType\": \"POOLING\", \"heading\": 209.23043288110117 }, { \"id\": 581095, \"coordinate\": { \"latitude\": 53.54410978745926, \"longitude\": 9.781694491767212 }, \"fleetType\": \"POOLING\", \"heading\": 306.08280902773106 }, { \"id\": 292491, \"coordinate\": { \"latitude\": 53.44239552970885, \"longitude\": 9.99088607091103 }, \"fleetType\": \"POOLING\", \"heading\": 143.2585600499125 }, { \"id\": 213128, \"coordinate\": { \"latitude\": 53.61291304746374, \"longitude\": 9.816805586204712 }, \"fleetType\": \"POOLING\", \"heading\": 94.31612546051822 }, { \"id\": 813906, \"coordinate\": { \"latitude\": 53.55775523725956, \"longitude\": 9.972060030981023 }, \"fleetType\": \"TAXI\", \"heading\": 267.5570909384844 }, { \"id\": 442418, \"coordinate\": { \"latitude\": 53.40380706412192, \"longitude\": 9.980126539911103 }, \"fleetType\": \"TAXI\", \"heading\": 219.62656110042704 }, { \"id\": 770243, \"coordinate\": { \"latitude\": 53.507861710374385, \"longitude\": 9.877467987726904 }, \"fleetType\": \"TAXI\", \"heading\": 18.721534219999945 }, { \"id\": 709229, \"coordinate\": { \"latitude\": 53.66067536412748, \"longitude\": 10.062486215265661 }, \"fleetType\": \"TAXI\", \"heading\": 346.3007370574046 }, { \"id\": 180694, \"coordinate\": { \"latitude\": 53.47623984518888, \"longitude\": 9.82724813866731 }, \"fleetType\": \"POOLING\", \"heading\": 89.4358684676814 }, { \"id\": 716905, \"coordinate\": { \"latitude\": 53.68051353902997, \"longitude\": 10.09459009646773 }, \"fleetType\": \"POOLING\", \"heading\": 267.82984040966676 }, { \"id\": 710735, \"coordinate\": { \"latitude\": 53.61387457592695, \"longitude\": 9.94610349596142 }, \"fleetType\": \"TAXI\", \"heading\": 147.03720726412146 }, { \"id\": 443548, \"coordinate\": { \"latitude\": 53.517244322859604, \"longitude\": 9.76069930725647 }, \"fleetType\": \"TAXI\", \"heading\": 76.7781123712341 }, { \"id\": 346425, \"coordinate\": { \"latitude\": 53.60686208044, \"longitude\": 9.918715137634342 }, \"fleetType\": \"POOLING\", \"heading\": 4.586184016914025 }, { \"id\": 625491, \"coordinate\": { \"latitude\": 53.639553858485584, \"longitude\": 10.01865919763809 }, \"fleetType\": \"POOLING\", \"heading\": 194.5518026471353 }, { \"id\": 882498, \"coordinate\": { \"latitude\": 53.53139447992286, \"longitude\": 10.016991791447508 }, \"fleetType\": \"TAXI\", \"heading\": 243.61172323671948 }, { \"id\": 839606, \"coordinate\": { \"latitude\": 53.52341883797173, \"longitude\": 9.906170205372876 }, \"fleetType\": \"POOLING\", \"heading\": 26.947762083739885 }, { \"id\": 456521, \"coordinate\": { \"latitude\": 53.568243272372044, \"longitude\": 10.019281731860275 }, \"fleetType\": \"POOLING\", \"heading\": 72.00798696071064 }, { \"id\": 746117, \"coordinate\": { \"latitude\": 53.544252390745406, \"longitude\": 9.998986354268458 }, \"fleetType\": \"TAXI\", \"heading\": 124.2592035315601 }, { \"id\": 880091, \"coordinate\": { \"latitude\": 53.55491267208498, \"longitude\": 9.879396018036978 }, \"fleetType\": \"POOLING\", \"heading\": 118.74269864160246 }, { \"id\": 105102, \"coordinate\": { \"latitude\": 53.495624671847594, \"longitude\": 9.772958828399943 }, \"fleetType\": \"TAXI\", \"heading\": 160.11204983959615 }, { \"id\": 314007, \"coordinate\": { \"latitude\": 53.61183241475308, \"longitude\": 9.955975492540077 }, \"fleetType\": \"POOLING\", \"heading\": 91.06332892858624 }, { \"id\": 316389, \"coordinate\": { \"latitude\": 53.588463988194526, \"longitude\": 9.951154427946875 }, \"fleetType\": \"TAXI\", \"heading\": 109.61658368360102 }, { \"id\": 638069, \"coordinate\": { \"latitude\": 53.60271587353919, \"longitude\": 9.97241685823874 }, \"fleetType\": \"TAXI\", \"heading\": 143.62074388811027 }, { \"id\": 934949, \"coordinate\": { \"latitude\": 53.666377469367454, \"longitude\": 9.903928826149876 }, \"fleetType\": \"POOLING\", \"heading\": 321.95107697945656 }, { \"id\": 619483, \"coordinate\": { \"latitude\": 53.67666844946375, \"longitude\": 10.051293156864565 }, \"fleetType\": \"POOLING\", \"heading\": 297.58596060765706 }, { \"id\": 547690, \"coordinate\": { \"latitude\": 53.51563966682856, \"longitude\": 9.817679306024612 }, \"fleetType\": \"TAXI\", \"heading\": 0.4256210762601156 }, { \"id\": 986337, \"coordinate\": { \"latitude\": 53.66034045665849, \"longitude\": 9.846920786297677 }, \"fleetType\": \"TAXI\", \"heading\": 221.1474209643714 }, { \"id\": 910939, \"coordinate\": { \"latitude\": 53.599186955678476, \"longitude\": 9.795139752274245 }, \"fleetType\": \"POOLING\", \"heading\": 166.71492842956363 }, { \"id\": 691679, \"coordinate\": { \"latitude\": 53.64870417138077, \"longitude\": 9.97834419874922 }, \"fleetType\": \"POOLING\", \"heading\": 294.30228198967546 } ] }";

  @Before
  public void setUp() {
    Mockito
        .when(apiClient.getList("53.694865", "9.757589", "53.394655", "10.099891"))
        .thenReturn(testDataObservable());
  }

  @After
  public void tearDown() {
    apiClient = null;
  }

  @Test
  public void fetchList() {
    Mockito
        .when(apiClient.getList("53.694865", "9.757589", "53.394655", "10.099891"))
        .thenReturn(testDataObservable());

    Assert.assertEquals(
        demoResponse,
        apiClient.getList("53.694865", "9.757589", "53.394655", "10.099891")
    );
  }

  private Single<PoiList> testDataObservable() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return Single.fromObservable(Observable.just(gson.fromJson(demoResponse, PoiList.class)));
  }

  @Test
  public void testAvailability() throws Exception {
    URLConnection connection = new URL(
        "https://fake-poi-api.mytaxi.com/?p1Lat=53.694865&p1Lon=9.757589&p2Lat=53.394655&p2Lon=10.099891")
        .openConnection();
    InputStream response = connection.getInputStream();

    StringBuffer buffer = new StringBuffer();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(
        response,
        Charset.defaultCharset()
    ))) {
      for (String line; (line = reader.readLine()) != null; ) {
        buffer.append(line);
      }
    }

    assert buffer.length() > 0;
  }
}