package com.example.githubapi.Services;

import com.example.githubapi.Adapter.User;
// SearchResponse.java
import java.util.List;

public class SearchResponse {
    private int total_count;
    private boolean incomplete_results;
    private List<UserResponse> items;


    public List<UserResponse> getItems() {
        return items;
    }


}
