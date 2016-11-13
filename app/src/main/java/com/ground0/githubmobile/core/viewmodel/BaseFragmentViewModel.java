package com.ground0.githubmobile.core.viewmodel;

import com.ground0.githubmobile.core.components.BaseApplication;
import com.ground0.githubmobile.core.components.BaseFragment;
import com.ground0.githubmobile.core.rx.SubscriptionBuilder;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zer0 on 9/10/16.
 */

public class BaseFragmentViewModel<T extends BaseFragment> implements ViewModel {

  WeakReference<T> fragment;
  @Inject public SubscriptionBuilder subscriptionBuilder;

  public void registerFragment(T fragment) {
    this.fragment = new WeakReference<T>(fragment);
    afterRegister();
  }

  public T getActualFragment() {
    return fragment.get();
  }

  public BaseApplication getBaseApplication() {
    return getActualFragment().getActualActivity().getBaseApplication();
  }

  public CompositeSubscription getCompositeSubscription() {
    return getActualFragment().getActualActivity().getCompositeSubscription();
  }

  public void afterRegister() {

  }
}
