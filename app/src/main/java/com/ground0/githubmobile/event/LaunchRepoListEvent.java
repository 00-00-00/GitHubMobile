package com.ground0.githubmobile.event;

import com.ground0.githubmobile.core.Event;

/**
 * Created by zer0 on 14/11/16.
 */

public class LaunchRepoListEvent implements Event<String> {

  String userName;

  public LaunchRepoListEvent(String userName) {
    this.userName = userName;
  }

  @Override public String data() {
    return userName;
  }

  @Override public int eventType() {
    return LAUNCH_REPO_LIST;
  }
}
