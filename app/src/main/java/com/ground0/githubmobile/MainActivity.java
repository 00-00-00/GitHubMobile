package com.ground0.githubmobile;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.a_landing_frame) FrameLayout mSceneRoot;
  Scene sceneA, sceneB;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_landing);
    ButterKnife.bind(this);
    initScenes();
  }

  private void initScenes() {

    // Create the scenes
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

      sceneA = Scene.getSceneForLayout(mSceneRoot, R.layout.a_landing_scene_a, this);
      sceneB = Scene.getSceneForLayout(mSceneRoot, R.layout.a_landing_scene_b, this);
      sceneB.setEnterAction(() -> {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation_0_1);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setStartOffset(500);
        findViewById(R.id.a_landing_scene_b_button).setAnimation(animation);
        findViewById(R.id.a_landing_scene_b_button).animate();
      });
    }
  }

  @OnClick(R.id.a_landing_scene_a_user) public void onClickUser(View view) {
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Transition transition = new ChangeBounds().removeTarget(R.id.a_landing_scene_b_button);
      transition.setDuration(500);
      transition.setInterpolator(new AccelerateDecelerateInterpolator());
      TransitionManager.go(sceneB, transition);
    }
  }
}
