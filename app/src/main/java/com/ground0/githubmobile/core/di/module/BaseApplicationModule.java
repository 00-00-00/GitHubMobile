package com.ground0.githubmobile.core.di.module;

import com.ground0.githubmobile.core.components.BaseApplication;
import com.ground0.githubmobile.core.rx.ISubscriptionBuilder;
import com.ground0.githubmobile.core.rx.SubscriptionBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by zer0 on 9/10/16.
 */

@Module public class BaseApplicationModule {
  private final BaseApplication application;

  public BaseApplicationModule(BaseApplication application) {
    this.application = application;
  }

  @Provides @Singleton BaseApplication providePatientApplication() {
    return this.application;
  }

  @Provides @Singleton ISubscriptionBuilder provideDefaultActivitySubscription(
      SubscriptionBuilder subscriptionBuilder) {
    return subscriptionBuilder;
  }
}
