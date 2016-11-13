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

  @Retention(RetentionPolicy.SOURCE) @IntDef({ OPEN_PROJECT_DETAIL }) public @interface EventType {
  }

  public static final int OPEN_PROJECT_DETAIL = 0;
}
