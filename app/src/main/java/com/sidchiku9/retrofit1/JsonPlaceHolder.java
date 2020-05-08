package com.sidchiku9.retrofit1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder {

    @GET("adgMarvel")
    Call<List<Post>> getPosts();
}
