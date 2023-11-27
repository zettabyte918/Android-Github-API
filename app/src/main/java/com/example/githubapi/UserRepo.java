package com.example.githubapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.githubapi.Adapter.RepoAdapter;
import com.example.githubapi.Services.GithubApiService;
import com.example.githubapi.Services.Repository;
import com.example.githubapi.Services.UserResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepo extends AppCompatActivity {
    private RecyclerView reposRecyclerView;
    private RepoAdapter repoAdapter;
    private ProgressBar loadingSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_repo);

        loadingSpinner = findViewById(R.id.loadingSpinner);
        ImageView avatarImage = findViewById(R.id.avatar);
        TextView usernameText = findViewById(R.id.username);

        // Get data from the intent
        String username = getIntent().getStringExtra("username");
        String imageUrl = getIntent().getStringExtra("imageUrl");

        // Load the big rounded image using Picasso
        Picasso.get().load(imageUrl).placeholder(R.drawable.user)
                .into(avatarImage);

        // Set the login text
        usernameText.setText(username);

        reposRecyclerView = findViewById(R.id.reposRecyclerView);
        repoAdapter = new RepoAdapter(new ArrayList<>()); // Initialize with an empty list

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reposRecyclerView.setLayoutManager(layoutManager);
        reposRecyclerView.setAdapter(repoAdapter);

        // fetch current use repos
        fetchUserRepos(username);
    }


    private void fetchUserRepos(String username) {
        showLoadingSpinner();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubApiService apiService = retrofit.create(GithubApiService.class);
        Call<List<Repository>> call = apiService.getUserRepos(username);

        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                hideLoadingSpinner();
                if (response.isSuccessful()) {
                    List<Repository> repos = response.body();
                    // Handle the list of repositories as needed
                    repoAdapter.setRepoList(repos);
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                // Handle network errors or API call failure
                hideLoadingSpinner();
            }
        });
    }

    private void showLoadingSpinner() {
        loadingSpinner.setVisibility(View.VISIBLE);
    }

    private void hideLoadingSpinner() {
        loadingSpinner.setVisibility(View.GONE);
    }

}