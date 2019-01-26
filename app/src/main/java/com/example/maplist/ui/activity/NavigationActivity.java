package com.example.maplist.ui.activity;

import android.os.Bundle;
import com.example.maplist.R;
import com.example.maplist.base.BaseActivity;
import com.example.maplist.databinding.ActivityNavigationBinding;
import com.example.maplist.ui.activity.viewpager.NavigationViewPagerAdapter;
import com.example.maplist.ui.list.view.ListFragment;
import com.example.maplist.ui.map.view.MapFragment;

public class NavigationActivity extends BaseActivity<ActivityNavigationBinding> {

  ListFragment listFragment;
  MapFragment mapFragment;

  private NavigationViewPagerAdapter viewPagerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getSupportActionBar().setTitle(R.string.appname);

    listFragment = new ListFragment();
    mapFragment = new MapFragment();

    setUpViewPager();
  }

  private void setUpViewPager() {
    viewPagerAdapter = new NavigationViewPagerAdapter(getSupportFragmentManager());

    viewPagerAdapter.addFragments(listFragment);
    viewPagerAdapter.addFragments(mapFragment);

    binding.viewPager.setAdapter(viewPagerAdapter);

    binding.navigation.setOnNavigationItemSelectedListener(menuItem -> {
      switch (menuItem.getItemId()) {
        case R.id.navigation_home:
          binding.viewPager.setCurrentItem(0);
          break;
        case R.id.navigation_dashboard:
          binding.viewPager.setCurrentItem(1);
          break;
      }
      return true;
    });
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_navigation;
  }
}
