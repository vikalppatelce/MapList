package com.example.maplist.ui.viewmodel;

import com.example.maplist.api.ApiClient;
import com.example.maplist.api.model.PoiList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ListViewModelTest {

  @Mock
  ApiClient twitterApiClient;

  private TestSubscriber<PoiList> testListSubscriber;
  private String demoResponse = "";

  @Before
  public void setUp() throws Exception {
    testListSubscriber = TestSubscriber.create();
    Mockito.when(twitterApiClient.getList("", "", "1", "")).thenReturn(testDataObservable());
  }

  @After
  public void tearDown() throws Exception {
    twitterApiClient = null;
    testListSubscriber = null;
  }

  @Test
  public void fetchAccessToken() {
  }

  @Test
  public void fetchTweet() {
  }

  private Single<PoiList> testDataObservable() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return Single.fromObservable(Observable.just(gson.fromJson(demoResponse, PoiList.class)));
  }
}