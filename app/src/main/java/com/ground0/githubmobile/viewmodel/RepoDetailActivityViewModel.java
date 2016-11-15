package com.ground0.githubmobile.viewmodel;

import com.ground0.githubmobile.activity.RepoDetailActivity;
import com.ground0.githubmobile.core.viewmodel.BaseActivityViewModel;
import com.ground0.githubmobile.event.LaunchRepoDetailEvent;
import com.ground0.model.Repo;
import javax.inject.Inject;

/**
 * Created by zer0 on 15/11/16.
 */

public class RepoDetailActivityViewModel extends BaseActivityViewModel<RepoDetailActivity> {

  @Inject public RepoDetailActivityViewModel() {
  }
  Repo repo;

  @Override public void afterRegister() {
    super.afterRegister();
    initSubscriptions();
  }

  private void initSubscriptions() {
    getCompositeSubscription().add(
        getSystemBus().filter(event -> event instanceof LaunchRepoDetailEvent)
            .subscribe(getSubscriptionBuilder().builder().onNext(val -> {
              repo = ((LaunchRepoDetailEvent) val).data();
            }).build()));
  }

  public Repo getRepo() {
    return repo;
  }
}
