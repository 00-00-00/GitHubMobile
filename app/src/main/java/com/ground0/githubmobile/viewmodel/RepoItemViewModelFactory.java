package com.ground0.githubmobile.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.ground0.githubmobile.R;
import com.ground0.githubmobile.core.viewmodel.ViewModel;
import com.ground0.githubmobile.util.ColorUtil;
import com.ground0.model.Repo;
import java.lang.ref.WeakReference;

/**
 * Created by zer0 on 14/11/16.
 */

public class RepoItemViewModelFactory {

  WeakReference<Context> reference;
  RepoItemViewModelHandler handler;

  public RepoItemViewModelFactory(Context reference, RepoItemViewModelHandler handler) {
    this.reference = new WeakReference<Context>(reference);
    this.handler = handler;
  }

  public RepoItemViewModel createItemViewModel(Repo repo) {
    return new RepoItemViewModel(repo);
  }

  public class RepoItemViewModel implements ViewModel {
    Repo repo;
    Drawable drawable;

    RepoItemViewModel(Repo repo) {
      this.repo = repo;
      this.drawable = ColorUtil.getMaterialDrawable(reference.get(), repo.getFullName());
    }

    public Repo getRepo() {
      return repo;
    }

    public Drawable getDrawable() {
      return drawable;
    }

    public void openDetail(View view) {
      handler.openDetail(repo, view.findViewById(R.id.i_repo_imageView));
    }
  }

  public interface RepoItemViewModelHandler {
    void openDetail(Repo repo, View sharedView);
  }
}
