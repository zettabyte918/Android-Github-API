package com.example.githubapi.Services;

import com.google.gson.annotations.SerializedName;

// Repository.java
import com.google.gson.annotations.SerializedName;

public class Repository {
    @SerializedName("name")
    private String name;

    @SerializedName("visibility")
    private String visibility;

    @SerializedName("language")
    private String language;

    @SerializedName("size")
    private int size;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("created_at")
    private String createdAt;

    // Add other fields as needed

    public String getName() {
        return name;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getLanguage() {
        return language;
    }

    public int getSize() {
        return size;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // Add getters for other fields
}
