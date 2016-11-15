package com.ground0.githubmobile.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ground0.githubmobile.R;
import com.ground0.githubmobile.core.components.BaseActivity;
import com.ground0.githubmobile.databinding.ActivityLandingBinding;
import com.ground0.githubmobile.event.LaunchRepoListEvent;
import com.ground0.githubmobile.util.PixelUtil;
import com.ground0.githubmobile.viewmodel.LandingActivityViewModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

public class LandingActivity extends BaseActivity {

  @BindView(R.id.a_landing_frame) ViewGroup mSceneRoot;
  @BindView(R.id.a_landing_scene_a_user) View sceneA;
  @BindView(R.id.a_landing_scene_b) View sceneB;
  @BindView(R.id.a_landing_scene_b_button) View sceneBButton;
  @BindView(R.id.a_landing_scene_b_username) EditText userName;
  @BindView(R.id.a_landing_scene_b_username_layout) TextInputLayout textInputLayout;
  @BindView(R.id.a_landing_image) View imageView;
  @Inject LandingActivityViewModel viewModel;
  @State int activityState;
  ActivityLandingBinding activityLandingBinding;

  @Retention(RetentionPolicy.SOURCE) @IntDef({ SCENE_A, SCENE_B }) @interface State {
  }

  static final int SCENE_A = 0;
  static final int SCENE_B = 1;
  static final String ACTIVITY_STATE_KEY = "LandingActivity.ACTIVITY_STATE";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityLandingBinding = DataBindingUtil.setContentView(this, R.layout.activity_landing);
    ButterKnife.bind(this);
    setState(SCENE_A);
  }

  @Override protected void registerActivityWithViewModel() {
    viewModel.registerActivity(this);
  }

  @Override protected void initializeModuleComponent() {
    getApplicationComponent().inject(this);
  }

  @OnClick(R.id.a_landing_scene_a_user) public void onClickUser(@Nullable View view) {
    setState(SCENE_B);
  }

  @OnClick(R.id.a_landing_scene_b_button) public void onClickApply(@Nullable View view) {
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

  @Override protected void onSaveInstanceState(Bundle outState) {
    outState.putInt(ACTIVITY_STATE_KEY, activityState);
    super.onSaveInstanceState(outState);
  }

  @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    if (savedInstanceState == null) return;
    //noinspection WrongConstant
    activityState = savedInstanceState.getInt(ACTIVITY_STATE_KEY);
    setState(activityState);
  }

  @Override public void onBackPressed() {
    if (activityState == SCENE_A) {
      super.onBackPressed();
    } else {
      setState(SCENE_A);
    }
  }

  private void setState(@State int state) {
    activityState = state;
    sceneA.setVisibility(state == SCENE_A ? View.VISIBLE : View.GONE);
    sceneB.setVisibility(state == SCENE_B ? View.VISIBLE : View.GONE);
    switch (state) {
      case SCENE_A:
        LinearLayout.LayoutParams layoutParams1 =
            (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams1.setMargins(0, PixelUtil.convertDpToPixel(this, 160), 0, 0);
        imageView.setLayoutParams(layoutParams1);
        break;
      case SCENE_B:
        startButtonAnimation();
        LinearLayout.LayoutParams layoutParams2 =
            (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams2.setMargins(0, 0, 0, 0);
        imageView.setLayoutParams(layoutParams2);
        break;
    }
  }

  private void startButtonAnimation() {

    // Create the scenes
    Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation_0_1);
    animation.setInterpolator(new AccelerateDecelerateInterpolator());
    animation.setStartOffset(500);
    sceneBButton.setAnimation(animation);
    sceneBButton.animate();
  }
}
