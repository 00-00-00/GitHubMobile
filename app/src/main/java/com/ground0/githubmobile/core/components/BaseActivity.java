package com.ground0.githubmobile.core.components;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.ground0.githubmobile.core.Event;
import com.ground0.githubmobile.core.di.component.ApplicationComponent;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zer0 on 9/10/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

  CompositeSubscription compositeSubscription = new CompositeSubscription();
  PublishSubject<Event> localPublishBus = PublishSubject.create();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeModuleComponent();
    registerActivityWithViewModel();
  }

  protected abstract void registerActivityWithViewModel();

  protected abstract void initializeModuleComponent();

  public CompositeSubscription getCompositeSubscription() {
    return compositeSubscription;
  }

  public BaseApplication getBaseApplication() {
    return (BaseApplication) getApplication();
  }

  public ApplicationComponent getApplicationComponent() {
    return getBaseApplication().getApplicationComponent();
  }

  public PublishSubject<Event> getLocalPublishBus() {
    return localPublishBus;
  }

  public BehaviorSubject<Event> getSystemBus() {
    return getBaseApplication().appBehaviourBus;
  }

  public void showSnackBar(String content, String action, View.OnClickListener onClickListener) {
    View view = getWindow().getDecorView().getRootView().findViewById(android.R.id.content);
    Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
    snackbar.setText(content);
    snackbar.setAction(action, onClickListener);
    snackbar.show();
  }

  public void showSnackBar(String content) {
    View view = getWindow().getDecorView().getRootView().findViewById(android.R.id.content);
    Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
    snackbar.setText(content);
    snackbar.show();
  }
}
