package com.example.maplist.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.example.maplist.api.ViewModelFactory;
import com.example.maplist.di.scope.ViewModelKey;
import com.example.maplist.ui.list.viewmodel.ListViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(ListViewModel.class)
  abstract ViewModel bindListViewModel(ListViewModel ListViewModel);

  @Binds
  abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
