package com.example.maplist.api;

import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public interface RxSingleSchedulers {
  RxSingleSchedulers DEFAULT = new RxSingleSchedulers() {
    @Override
    public <T> SingleTransformer<T, T> applySchedulers() {
      return single -> single
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
    }
  };


  RxSingleSchedulers TEST = new RxSingleSchedulers() {
    @Override
    public <T> SingleTransformer<T, T> applySchedulers() {
      return single -> single;
    }
  };
  <T> SingleTransformer<T, T> applySchedulers();
}
