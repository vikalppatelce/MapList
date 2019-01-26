package com.example.maplist.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

public abstract class BaseViewPagerAdapter extends FragmentStatePagerAdapter {
  // Sparse array to keep track of registered fragments in memory
  private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

  public BaseViewPagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  // Register the fragment when the item is instantiated
  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    Fragment fragment = (Fragment) super.instantiateItem(container, position);
    registeredFragments.put(position, fragment);
    return fragment;
  }

  // Unregister when the item is inactive
  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    registeredFragments.remove(position);
    super.destroyItem(container, position, object);
  }
}
