package com.ground0.githubmobile.viewmodel;

import android.graphics.drawable.Drawable;
import com.ground0.githubmobile.activity.RepoDetailActivity;
import com.ground0.githubmobile.core.viewmodel.BaseActivityViewModel;
import com.ground0.githubmobile.event.LaunchRepoDetailEvent;
import com.ground0.githubmobile.util.ColorUtil;
import com.ground0.model.Repo;
import javax.inject.Inject;

/**
 * Created by zer0 on 15/11/16.
 */

public class RepoDetailActivityViewModel extends BaseActivityViewModel<RepoDetailActivity> {

  @Inject public RepoDetailActivityViewModel() {
  }

  Repo repo;
  Drawable drawable;

  @Override public void afterRegister() {
    super.afterRegister();
    initSubscriptions();
  }

  private void initSubscriptions() {
    getCompositeSubscription().add(
        getSystemBus().filter(event -> event instanceof LaunchRepoDetailEvent)
            .subscribe(getSubscriptionBuilder().builder().onNext(val -> {
              repo = ((LaunchRepoDetailEvent) val).data();
              drawable = ColorUtil.getMaterialDrawable(getActualActivity(), repo.getFullName());
            }).build()));
  }

  public Repo getRepo() {
    return repo;
  }

  public Drawable getDrawable() {
    return drawable;
  }
}
