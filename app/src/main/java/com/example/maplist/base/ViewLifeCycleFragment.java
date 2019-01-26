package com.example.maplist.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public class ViewLifeCycleFragment extends Fragment {

  static class ViewLifecycleOwner implements LifecycleOwner {
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
      return lifecycleRegistry;
    }
  }

  @Nullable
  private ViewLifecycleOwner viewLifecycleOwner;

  /**
   * @return the Lifecycle owner of the current view hierarchy,
   * or null if there is no current view hierarchy.
   */
  @Nullable
  public LifecycleOwner getViewLifeCycleOwner() {
    return viewLifecycleOwner;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewLifecycleOwner = new ViewLifecycleOwner();
    viewLifecycleOwner.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
  }

  @Override
  public void onStart() {
    super.onStart();
    if (viewLifecycleOwner != null) {
      viewLifecycleOwner.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_START);
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if (viewLifecycleOwner != null) {
      viewLifecycleOwner.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }
  }

  @Override
  public void onPause() {
    if (viewLifecycleOwner != null) {
      viewLifecycleOwner.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }
    super.onPause();
  }

  @Override
  public void onStop() {
    if (viewLifecycleOwner != null) {
      viewLifecycleOwner.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }
    super.onStop();
  }

  @Override
  public void onDestroyView() {
    if (viewLifecycleOwner != null) {
      viewLifecycleOwner.getLifecycle().handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
      viewLifecycleOwner = null;
    }
    super.onDestroyView();
  }
}
