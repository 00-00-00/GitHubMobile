package com.ground0.githubmobile.viewmodel;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.ground0.githubmobile.activity.RepoListActivity;
import com.ground0.githubmobile.adapter.RepoListRecyclerAdapter;
import com.ground0.githubmobile.core.viewmodel.BaseActivityViewModel;
import com.ground0.githubmobile.event.LaunchRepoDetailEvent;
import com.ground0.githubmobile.event.LaunchRepoListEvent;
import com.ground0.model.Repo;
import com.ground0.repository.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zer0 on 14/11/16.
 */

public class RepoListActivityViewModel extends BaseActivityViewModel<RepoListActivity>
    implements RepoItemViewModelFactory.RepoItemViewModelHandler {

  @Inject public RepoListActivityViewModel() {
  }

  List<Repo> repos = new ArrayList<>();
  RepoListRecyclerAdapter repoListRecyclerAdapter;
  LinearLayoutManager layoutManager;
  String userName;
  @Inject UserRepository userRepository;

  @Override public void afterRegister() {
    super.afterRegister();
    initSubscriptions();
  }

  private void initSubscriptions() {
    getCompositeSubscription().add(
        getSystemBus().filter(event -> event instanceof LaunchRepoListEvent)
            .subscribe(getSubscriptionBuilder().builder().onNext(event -> {
              userName = ((LaunchRepoListEvent) event).data();
              fetchData();
            }).build()));
  }

  public RepoListRecyclerAdapter getRepoListRecyclerAdapter() {
    if (repoListRecyclerAdapter == null) {
      repoListRecyclerAdapter = new RepoListRecyclerAdapter(getActualActivity(), repos, this);
    }
    return repoListRecyclerAdapter;
  }

  public LinearLayoutManager getLayoutManager() {
    if (layoutManager == null) {
      layoutManager =
          new LinearLayoutManager(getActualActivity(), LinearLayoutManager.VERTICAL, false);
    }
    return layoutManager;
  }

  public void setRepos(List<Repo> repos) {
    if (repos == null) return;
    this.repos.clear();
    this.repos.addAll(repos);
    if (repoListRecyclerAdapter != null) repoListRecyclerAdapter.notifyDataSetChanged();
  }

  public void fetchData() {
    getActualActivity().initDataLoad();
    getCompositeSubscription().add(userRepository.getUsers(userName)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(getSubscriptionBuilder().builder().onNext(val -> {
          setRepos((List<Repo>) val);
          getActualActivity().dataLoaded();
        }).onError(e -> {
          getActualActivity().showSnackBar("Error while fetching Repos", "Retry", v -> fetchData());
          getActualActivity().dataLoaded();
        }).setFinishOnComplete().build()));
  }

  public String getUserName() {
    return userName;
  }

  @Override public void openDetail(Repo repo, View sharedView) {
    getActualActivity().getSystemBus().onNext(new LaunchRepoDetailEvent(repo));
    getActualActivity().launchRepoDetailActivity(sharedView);
  }

  public List<Repo> getRepos() {
    return repos;
  }
}
