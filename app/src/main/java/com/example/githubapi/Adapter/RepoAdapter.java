package com.example.githubapi.Adapter;

// RepoAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubapi.R;
import com.example.githubapi.Services.Repository;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private List<Repository> repoList;

    public RepoAdapter(List<Repository> repoList) {
        this.repoList = repoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_repo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repository repo = repoList.get(position);

        holder.repoNameTextView.setText(repo.getName());
        holder.visibilityTextView.setText("Visibility: " + repo.getVisibility());
        holder.languageTextView.setText("Language: " + repo.getLanguage());
        holder.sizeTextView.setText("Size: " + repo.getSize());
        holder.createdAtTextView.setText("Created at: " + repo.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    // Add this method to update the dataset
    public void setRepoList(List<Repository> repoList) {
        this.repoList = repoList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView repoNameTextView;
        public TextView visibilityTextView;
        public TextView languageTextView;
        public TextView sizeTextView;
        public TextView createdAtTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            repoNameTextView = itemView.findViewById(R.id.repoNameTextView);
            visibilityTextView = itemView.findViewById(R.id.visibilityTextView);
            languageTextView = itemView.findViewById(R.id.languageTextView);
            sizeTextView = itemView.findViewById(R.id.sizeTextView);
            createdAtTextView = itemView.findViewById(R.id.createdAtTextView);
        }
    }
}
