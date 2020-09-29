package com.example.socialnetwork.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("picture")
    @Expose
    private Picture picture;
    public Picture getPicture() {
        return picture;
    }
    public void setPicture(Picture picture) {
        this.picture = picture;
    }


}