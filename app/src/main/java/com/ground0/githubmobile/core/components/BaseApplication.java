package com.ground0.githubmobile.core.components;

import android.app.Application;
import com.ground0.githubmobile.core.Event;
import com.ground0.githubmobile.core.di.component.ApplicationComponent;
import com.ground0.githubmobile.core.di.component.DaggerApplicationComponent;
import com.ground0.githubmobile.core.di.module.BaseApplicationModule;
import com.ground0.githubmobile.core.di.module.BaseModule;
import javax.inject.Inject;
import rx.subjects.BehaviorSubject;

/**
 * Created by zer0 on 9/10/16.
 */

public class BaseApplication extends Application {

  @Inject protected BehaviorSubject<Event> appBehaviourBus;
  ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    initDaggerInjection();
  }

  private void initDaggerInjection() {
    applicationComponent = DaggerApplicationComponent.builder()
        .baseApplicationModule(new BaseApplicationModule(this))
        .baseModule(new BaseModule(this))
        .build();
    applicationComponent.inject(this);
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  public BehaviorSubject<Event> getAppBehaviourBus() {
    return appBehaviourBus;
  }
}
