package com.example.githubapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.githubapi.Adapter.User;
import com.example.githubapi.Adapter.UserAdapter;
import com.example.githubapi.Helpers.DebounceTextWatcher;
import com.example.githubapi.Services.GithubApiService;
import com.example.githubapi.Services.SearchResponse;
import com.example.githubapi.Services.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;
    private DebounceTextWatcher debounceTextWatcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernameInput = findViewById(R.id.username);
        // Find the RecyclerView by its id
        recyclerView = findViewById(R.id.recyclerView);

        // Set up the LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        userList = new ArrayList<>();
        recyclerView.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(this, userList);
        recyclerView.setAdapter(userAdapter);

        // Set up a TextWatcher to listen for changes in the usernameEditText
        // Set up the TextWatcher with debounce
        debounceTextWatcher = new DebounceTextWatcher(new DebounceTextWatcher.DebounceListener() {
            @Override
            public void onDebounce(String text) {
                // Perform your API request or any other action here
                if (!TextUtils.isEmpty(text)) {
                    searchGithubUsers(text);
                } else {
                    // Clear the userList if the input is empty
                    userList.clear();
                    userAdapter.notifyDataSetChanged();
                }
            }
        });

        // Add the TextWatcher to your EditText
        usernameInput.addTextChangedListener(debounceTextWatcher);
    }

    private void searchGithubUsers(String query) {
        this.userList.clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubApiService githubApiService = retrofit.create(GithubApiService.class);
        Call<SearchResponse> call = githubApiService.searchUsers(query);

        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UserResponse> users = response.body().getItems();
                    // Now 'users' contains the list of users from the GitHub API response.

                    // Add the users to your RecyclerView adapter or perform any other actions.
                    for (UserResponse user : users) {
                        addUserToList(user.getLogin(), user.getScore(), user.getAvatarUrl());
                    }
                } else {
                    // Handle the error
                    Log.e("MainActivity", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // Handle the failure
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });}

    private void addUserToList(String username, int score, String imageUrl) {
        // Add a dummy number and the image URL for illustration
        String dummyNumber = "123456789";

        // Create a UserData object and add it to the list
        userList.add(new User(username, score, imageUrl));

        // Notify the adapter of the change
        userAdapter.notifyDataSetChanged();
    }
}