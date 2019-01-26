package com.example.maplist.di.module;

import com.example.maplist.api.Api;
import com.example.maplist.api.ApiConstants;
import com.example.maplist.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

  @AppScope
  @Provides
  Retrofit provideRetrofit() {
    return new Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @AppScope
  @Provides
  Api provideRetrofitApi(Retrofit retrofit) {
    return retrofit.create(Api.class);
  }
}
