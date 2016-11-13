package com.ground0.githubmobile.core.di.module;

import android.content.Context;
import com.ground0.githubmobile.core.Event;
import com.ground0.githubmobile.core.components.BaseApplication;
import com.ground0.repository.UserRepositoryImpl;
import com.ground0.repository.repository.UserRepository;
import com.ground0.repository.store.CloudDataStore;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.subjects.BehaviorSubject;

/**
 * Created by zer0 on 9/10/16.
 */

@Module public class BaseModule {

  private final BaseApplication application;
  BehaviorSubject<Event> behaviorSubject = BehaviorSubject.create();

  public BaseModule(BaseApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton BehaviorSubject<Event> provideBehaviorSubject() {
    return behaviorSubject;
  }

  @Provides @Singleton UserRepository provideUserRepository(UserRepositoryImpl repository) {
    return repository;
  }

  @Provides @Singleton @Named("cloudStore") UserRepository provideCloudDataStore(
      CloudDataStore store) {
    return store;
  }

}
