package com.ground0.githubmobile.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ground0.githubmobile.R;
import com.ground0.githubmobile.core.components.BaseActivity;
import com.ground0.githubmobile.viewmodel.RepoDetailActivityViewModel;
import javax.inject.Inject;

/**
 * Created by zer0 on 15/11/16.
 */

public class RepoDetailActivity extends BaseActivity {

  @Inject RepoDetailActivityViewModel viewModel;
  @BindView(R.id.a_repo_detail_imageView) ImageView imageView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repo_detail);
    ButterKnife.bind(this);
    initUI();
  }

  @Override protected void registerActivityWithViewModel() {
    viewModel.registerActivity(this);
  }

  @Override protected void initializeModuleComponent() {
    getApplicationComponent().inject(this);
  }

  private void initUI() {
    imageView.setTransitionName(getString(R.string.activity_fab_trans));
    if (viewModel.getRepo() != null) {
      getSupportActionBar().setTitle(viewModel.getRepo().getFullName());
    }
  }
}
