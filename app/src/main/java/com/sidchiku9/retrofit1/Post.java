package com.sidchiku9.retrofit1;

import com.google.gson.annotations.SerializedName;

public class Post {

    private String name;
    private String realname;
    private String team;
    private int firstappearance;
    private String createdby;
    private String publisher;
    private String imageurl;

    @SerializedName("bio")
    private String bio;

    public String getName() {
        return name;
    }

    public String getRealName() {
        return realname;
    }

    public String getTeam() {
        return team;
    }

    public int getFirstAppearance() {
        return firstappearance;
    }

    public String getCreatedBy() {
        return createdby;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getImageURL() {
        return imageurl;
    }

    public String getBio() {
        return bio;
    }
}
