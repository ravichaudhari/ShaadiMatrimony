package com.shaadi.matrimony.network;


import com.shaadi.matrimony.holder.UserList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APICall {
    @GET("api")
    Call<UserList> getUsers(@Query("results") String count);
}
