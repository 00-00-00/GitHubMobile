package com.ground0.githubmobile.core;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zer0 on 9/10/16.
 */

public interface Event<T> {

  T data();

  @EventType int eventType();

  @Retention(RetentionPolicy.SOURCE) @IntDef({ LAUNCH_REPO_LIST, LAUNCH_REPO_DETAIL }) public @interface EventType {
  }

  public static final int LAUNCH_REPO_LIST = 0;
  public static final int LAUNCH_REPO_DETAIL = 1;

}
