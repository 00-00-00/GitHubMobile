package com.ground0.githubmobile.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.ground0.githubmobile.core.viewmodel.ViewModel;
import com.ground0.githubmobile.util.ColorUtil;
import com.ground0.model.Repo;
import java.lang.ref.WeakReference;

/**
 * Created by zer0 on 14/11/16.
 */

public class RepoItemViewModelFactory {

  WeakReference<Context> reference;

  public RepoItemViewModelFactory(Context reference) {
    this.reference = new WeakReference<Context>(reference);
  }

  public RepoItemViewModel createItemViewModel(Repo repo) {
    return new RepoItemViewModel(repo);
  }

  public class RepoItemViewModel implements ViewModel {
    Repo repo;
    Drawable drawable;

    RepoItemViewModel(Repo repo) {
      this.repo = repo;
      this.drawable = ColorUtil.getRandomMaterialDrawable(reference.get());
    }

    public Repo getRepo() {
      return repo;
    }

    public Drawable getDrawable() {
      return drawable;
    }
  }
}
