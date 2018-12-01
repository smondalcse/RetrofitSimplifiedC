package com.nitolmotorsltd.retrofitsimplifiedc.Api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Api {

    @FormUrlEncoded
    @POST("createusers")
    Call<ResponseBody> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginuser(
            @Field("email") String email,
            @Field("password") String password
    );


}