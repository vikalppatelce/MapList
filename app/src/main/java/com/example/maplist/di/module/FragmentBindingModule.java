package com.example.maplist.di.module;

import com.example.maplist.ui.list.view.ListFragment;
import com.example.maplist.ui.map.view.MapFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

    @ContributesAndroidInjector
    abstract MapFragment provideMapFragment();
}
