package com.ground0.githubmobile.core.di.module;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import com.ground0.githubmobile.core.di.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zer0 on 9/10/16.
 */

@Module public class BaseActivityModule {
  private final Activity activity;

  public BaseActivityModule(AppCompatActivity activity) {
    this.activity = activity;
  }

  /**
   * Expose the activity to dependents in the graph.
   */
  @Provides @PerActivity Activity activity() {
    return this.activity;
  }



 /* @Provides LoginViewModelPatient provideLoginViewModel(){
    return new LoginViewModelPatient();
  }*/
}