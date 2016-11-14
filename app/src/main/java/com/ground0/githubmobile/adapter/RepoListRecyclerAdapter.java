package com.ground0.githubmobile.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ground0.githubmobile.R;
import com.ground0.githubmobile.databinding.ItemRepoBinding;
import com.ground0.githubmobile.viewmodel.RepoItemViewModelFactory;
import com.ground0.model.Repo;
import java.util.List;

/**
 * Created by zer0 on 14/11/16.
 */

public class RepoListRecyclerAdapter
    extends RecyclerView.Adapter<RepoListRecyclerAdapter.ViewHolder> {

  List<Repo> data;
  RepoItemViewModelFactory repoItemViewModelFactory;

  public RepoListRecyclerAdapter(Context context, List<Repo> data) {
    this.data = data;
    repoItemViewModelFactory = new RepoItemViewModelFactory(context);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.item_repo, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    ItemRepoBinding itemRepoBinding = DataBindingUtil.bind(holder.itemView);
    itemRepoBinding.setViewModel(repoItemViewModelFactory.createItemViewModel(data.get(position)));
  }

  @Override public int getItemCount() {
    return data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
