package com.ground0.githubmobile.core.components;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.ground0.githubmobile.core.di.component.ActivityComponent;
import com.ground0.githubmobile.core.di.component.DaggerActivityComponent;
import com.ground0.githubmobile.core.di.module.BaseActivityModule;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zer0 on 9/10/16.
 */

public abstract class BaseFragment<T extends BaseActivity> extends Fragment {

  CompositeSubscription compositeSubscription;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    compositeSubscription = new CompositeSubscription();
    injectDependencies();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    compositeSubscription.unsubscribe();
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    registerFragmentWithViewModel();
  }

  protected abstract void registerFragmentWithViewModel();

  protected abstract void injectDependencies();

  public T getActualActivity() {
    return (T) getActivity();
  }

  protected ActivityComponent getActivityComponent() {
    return DaggerActivityComponent.builder()
        .applicationComponent(
            ((BaseApplication) getActivity().getApplication()).getApplicationComponent())
        .baseActivityModule(new BaseActivityModule((AppCompatActivity) getActivity()))
        .build();
  }
}
