package com.narendra.travelersbridge.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Api api;
    public  static final String baseUrl="http://traveller.projectnyou.com/api/";
    public static Retrofit retrofit;
    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
public static Api getApi()
{
    return  api= RetrofitClient.getApiClient().create(Api.class);
}
}
