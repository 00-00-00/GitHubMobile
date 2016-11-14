package com.ground0.githubmobile.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ground0.githubmobile.R;
import com.ground0.githubmobile.core.components.BaseActivity;
import com.ground0.githubmobile.viewmodel.RepoListActivityViewModel;
import javax.inject.Inject;

/**
 * Created by zer0 on 14/11/16.
 */

public class RepoListActivity extends BaseActivity {

  @Inject RepoListActivityViewModel viewModel;
  @BindView(R.id.a_repo_list_recycler) RecyclerView recyclerView;
  @BindView(R.id.a_repo_list_fab) FloatingActionButton floatingActionButton;

  @Override protected void registerActivityWithViewModel() {
    viewModel.registerActivity(this);
  }

  @Override protected void initializeModuleComponent() {
    getApplicationComponent().inject(this);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repo_list);
    ButterKnife.bind(this);
    initRecyclerView();
    initUI();
  }

  private void initRecyclerView() {
    recyclerView.setLayoutManager(viewModel.getLayoutManager());
    recyclerView.setAdapter(viewModel.getRepoListRecyclerAdapter());
  }

  private void initUI() {
    floatingActionButton.setTransitionName(getString(R.string.activity_fab_trans));
    getSupportActionBar().setTitle(viewModel.getUserName());
  }
}
