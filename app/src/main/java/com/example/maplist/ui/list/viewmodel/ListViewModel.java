package com.example.maplist.ui.list.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.maplist.api.ApiClient;
import com.example.maplist.api.RxSingleSchedulers;
import com.example.maplist.api.model.PoiList;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

public class ListViewModel extends ViewModel {

  private CompositeDisposable disposable;
  private final ApiClient apiClient;

  private final MutableLiveData<PoiList> apiPoiData = new MutableLiveData<>();
  private final MutableLiveData<PoiList> apiMapPoiData = new MutableLiveData<>();
  private final MutableLiveData<Throwable> apiError = new MutableLiveData<>();
  private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

  @Inject
  public ListViewModel(ApiClient apiClient) {
    this.apiClient = apiClient;
    disposable = new CompositeDisposable();
  }

  public LiveData<PoiList> apiListData() {
    return apiPoiData;
  }

  public LiveData<PoiList> apiMapListData() {
    return apiMapPoiData;
  }

  public LiveData<Throwable> apiError() {
    return apiError;
  }

  public LiveData<Boolean> loading() {
    return loading;
  }

  public void fetchList(String p1Lat, String p1Lon, String p2Lat, String p2Lon) {
    loading.postValue(true);
    disposable.add(apiClient
        .getList(p1Lat, p1Lon, p2Lat, p2Lon)
        .doOnEvent((not, used) -> loading.postValue(false))
        .compose(RxSingleSchedulers.DEFAULT.applySchedulers())
        .subscribe(
            apiPoiData::postValue,
            apiError::postValue
        )
    );
  }

  public void notifyMap(PoiList list) {
    apiMapPoiData.postValue(list);
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    if (disposable != null) {
      disposable.clear();
      disposable = null;
    }
  }
}
