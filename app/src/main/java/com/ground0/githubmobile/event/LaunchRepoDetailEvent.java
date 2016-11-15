package com.ground0.githubmobile.event;

import com.ground0.githubmobile.core.Event;
import com.ground0.model.Repo;

/**
 * Created by zer0 on 15/11/16.
 */

public class LaunchRepoDetailEvent implements Event<Repo> {

  Repo repo;

  public LaunchRepoDetailEvent(Repo repo) {
    this.repo = repo;
  }

  @Override public Repo data() {
    return repo;
  }

  @Override public int eventType() {
    return LAUNCH_REPO_DETAIL;
  }
}
