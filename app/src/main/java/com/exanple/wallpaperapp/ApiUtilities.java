package com.exanple.wallpaperapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {
    private static  Retrofit retrofit = null;
    public static final String API="563492ad6f9170000100000163900834d49d40f6b29da0f18052fb34";

    public static ApiInterface getApiInterface(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(ApiInterface.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(ApiInterface.class);
    }

}
