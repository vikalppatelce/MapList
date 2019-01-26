package com.example.maplist.ui.activity.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.example.maplist.base.BaseViewPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class NavigationViewPagerAdapter extends BaseViewPagerAdapter {
  private final List<Fragment> fragments = new ArrayList<>();

  public NavigationViewPagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  public void addFragments(Fragment fragment) {
    fragments.add(fragment);
  }

  @Override
  public Fragment getItem(int position) {
    return fragments.get(position);
  }

  @Override
  public int getCount() {
    return fragments.size();
  }
}
