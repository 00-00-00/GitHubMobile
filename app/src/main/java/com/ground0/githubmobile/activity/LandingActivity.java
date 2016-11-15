package com.ground0.githubmobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ground0.githubmobile.R;
import com.ground0.githubmobile.core.components.BaseActivity;
import com.ground0.githubmobile.event.LaunchRepoListEvent;
import com.ground0.githubmobile.viewmodel.LandingActivityViewModel;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

public class LandingActivity extends BaseActivity {

  @BindView(R.id.a_landing_frame) FrameLayout mSceneRoot;
  @Inject LandingActivityViewModel viewModel;
  Scene sceneA, sceneB;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_landing);
    ButterKnife.bind(this);
    initScenes();
  }

  @Override protected void registerActivityWithViewModel() {
    viewModel.registerActivity(this);
  }

  @Override protected void initializeModuleComponent() {
    getApplicationComponent().inject(this);
  }

  private void initScenes() {

    // Create the scenes

    sceneA = Scene.getSceneForLayout(mSceneRoot, R.layout.a_landing_scene_a, this);
    sceneB = Scene.getSceneForLayout(mSceneRoot, R.layout.a_landing_scene_b, this);
    sceneB.setEnterAction(() -> {
      Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation_0_1);
      animation.setInterpolator(new AccelerateDecelerateInterpolator());
      animation.setStartOffset(500);
      findViewById(R.id.a_landing_scene_b_button).setAnimation(animation);
      findViewById(R.id.a_landing_scene_b_button).animate();
      findViewById(R.id.a_landing_scene_b_button).setOnClickListener(this::onClickApply);
    });
  }

  @OnClick(R.id.a_landing_scene_a_user) public void onClickUser(@Nullable View view) {

    Transition transition = new ChangeBounds().removeTarget(R.id.a_landing_scene_b_button);
    transition.setDuration(500);
    transition.setInterpolator(new AccelerateDecelerateInterpolator());
    TransitionManager.go(sceneB, transition);
  }

  public void onClickApply(@Nullable View view) {
    EditText userName = ButterKnife.findById(this, R.id.a_landing_scene_b_username);
    TextInputLayout textInputLayout =
        ButterKnife.findById(this, R.id.a_landing_scene_b_username_layout);
    if (StringUtils.isBlank(userName.getText().toString())) {
      textInputLayout.setError(getString(R.string.mandatory_error));
      return;
    } else {
      textInputLayout.setError(null);
    }
    getSystemBus().onNext(new LaunchRepoListEvent(userName.getText().toString()));
    ActivityOptionsCompat options = ActivityOptionsCompat.
        makeSceneTransitionAnimation(this, view, getString(R.string.activity_fab_trans));
    startActivity(new Intent(this, RepoListActivity.class), options.toBundle());
  }
}
