package com.ground0.githubmobile.core.di.component;

import android.content.Context;
import com.ground0.githubmobile.activity.LandingActivity;
import com.ground0.githubmobile.activity.RepoListActivity;
import com.ground0.githubmobile.core.Event;
import com.ground0.githubmobile.core.components.BaseActivity;
import com.ground0.githubmobile.core.components.BaseApplication;
import com.ground0.githubmobile.core.di.module.BaseApplicationModule;
import com.ground0.githubmobile.core.di.module.BaseModule;
import com.ground0.githubmobile.core.rx.SubscriptionBuilder;
import com.ground0.repository.repository.UserRepository;
import dagger.Component;
import javax.inject.Singleton;
import rx.subjects.BehaviorSubject;

/**
 * Created by zer0 on 9/10/16.
 */

@Singleton @Component(modules = { BaseApplicationModule.class, BaseModule.class })
public interface ApplicationComponent {

  void inject(BaseApplication baseApplication);

  void inject(BaseActivity baseActivity);

  void inject(LandingActivity landingActivity);

  void inject(RepoListActivity repoListActivity);

  //Exposed to sub-graphs.
  Context context();

  // ThreadExecutor threadExecutor();
  // PostExecutionThread postExecutionThread();

  BaseApplication baseApplication();

  BehaviorSubject<Event> systemBehaviorSubject();

  SubscriptionBuilder defaultActivitySubscription();

  UserRepository userRepository();
}
