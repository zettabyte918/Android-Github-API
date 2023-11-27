package com.example.githubapi.Services;

import com.example.githubapi.Adapter.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApiService {
    @GET("search/users")
    Call<SearchResponse> searchUsers(@Query("q") String query);

    @GET("users/{username}/repos")
    Call<List<Repository>> getUserRepos(@Path("username") String username);
}