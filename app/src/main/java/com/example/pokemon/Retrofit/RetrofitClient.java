package com.example.pokemon.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instace;

    public static Retrofit getInstace() {
        if (instace == null) {
            instace = new Retrofit.Builder()
                    .baseUrl("http://www.json-generator.com/api/json/get/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return instace;

    }
}
