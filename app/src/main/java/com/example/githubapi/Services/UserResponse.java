package com.example.githubapi.Services;

public class UserResponse {
    private String login;
    private int id;
    private String avatar_url;
    private int score;

    public String getLogin() {
        return login;
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatar_url = avatarUrl;
    }
}
