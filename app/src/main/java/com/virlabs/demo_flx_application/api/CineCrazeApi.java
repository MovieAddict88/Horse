package com.virlabs.demo_flx_application.api;

import com.virlabs.demo_flx_application.entity.AuthResponse;
import com.virlabs.demo_flx_application.entity.ContentResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CineCrazeApi {
    
    // Content API endpoints
    @GET("?action=get_all_content")
    Call<ContentResponse> getAllContent();
    
    // Auth API endpoints
    @FormUrlEncoded
    @POST("auth_api.php?action=login")
    Call<AuthResponse> login(
        @Field("username") String username,
        @Field("password") String password
    );
    
    @FormUrlEncoded
    @POST("auth_api.php?action=register")
    Call<AuthResponse> register(
        @Field("username") String username,
        @Field("email") String email,
        @Field("password") String password,
        @Field("confirm_password") String confirmPassword
    );
    
    @GET("auth_api.php?action=check_session")
    Call<AuthResponse> checkSession();
    
    @GET("auth_api.php?action=logout")
    Call<AuthResponse> logout();
}
