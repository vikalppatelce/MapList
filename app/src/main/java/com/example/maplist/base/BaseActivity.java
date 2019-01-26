package com.example.maplist.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.maplist.util.LifecycleComponent;
import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

abstract public class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity
    implements HasSupportFragmentInjector {

  protected B binding;

  @Inject
  DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(
        this,
        getLayoutId(),
        new LifecycleComponent(getLifecycle())
    );
  }

  /**
   * Get activity layout resource id which will be inflated as activity view.
   *
   * @return layout resource id
   */
  @LayoutRes protected abstract int getLayoutId();

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentDispatchingAndroidInjector;
  }

  public void replaceFragment(int container, Fragment fragment, String tag) {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(container, fragment, tag)
        .commitAllowingStateLoss();
  }

  public void addFragment(int container, Fragment fragment, String tag) {
    getSupportFragmentManager()
        .beginTransaction()
        .add(container, fragment, tag)
        .commitAllowingStateLoss();
  }

  public void showToast(Context context, String message) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }
}
