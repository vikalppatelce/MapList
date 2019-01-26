package com.example.maplist.di.module;

import com.example.maplist.ui.activity.NavigationActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = { FragmentBindingModule.class})
    abstract NavigationActivity bindNavigationActivity();
}
