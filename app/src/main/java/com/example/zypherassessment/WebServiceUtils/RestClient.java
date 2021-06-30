package com.example.zypherassessment.WebServiceUtils;

import android.content.Context;

import com.example.zypherassessment.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import kotlin.collections.CollectionsKt;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static int REST_API_READ_TIMEOUT = 180;
    private static int REST_API_CONNECTION_TIMEOUT = 180;


    // Variables
    private static RestAPIService apiRestInterfaces ;

    public static RestAPIService getClient(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(REST_API_READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .connectionSpecs(CollectionsKt.listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
                .connectTimeout(REST_API_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (apiRestInterfaces == null) {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(context.getResources().getString( R.string.BASE_URL))
                    .addConverterFactory( GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
            apiRestInterfaces = client.create(RestAPIService.class);
        }
        return apiRestInterfaces ;
    }
}