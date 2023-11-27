package com.example.githubapi.Adapter;

// UserData.java
// UserData.java
public class User {

    private String username;
    private int score;
    private String imageUrl;

    public User(String username, int score, String imageUrl) {
        this.username = username;
        this.score = score;
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
