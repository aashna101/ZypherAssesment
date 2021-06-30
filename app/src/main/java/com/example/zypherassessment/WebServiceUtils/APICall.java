package com.example.zypherassessment.WebServiceUtils;

import android.content.Context;

import com.example.zypherassessment.WebServiceUtils.ResponseModel.testDataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APICall {

    private static RestAPIService apiService;
    public static Context context = null;

    public static APICall getInstance(Context context) {
        if (apiService == null) {
            apiService = RestClient.getClient(context);
            context = context;
        }
        return new APICall();
    }
    public void TestData( final IApiCallback iApiCallback) {

        Call<testDataModel> call = apiService.testDataAPI();

        call.enqueue(new Callback<testDataModel>() {
            @Override
            public void onResponse(Call<testDataModel> call, Response<testDataModel> response) {
                iApiCallback.onSuccess("testdata", response);
            }

            @Override
            public void onFailure(Call<testDataModel> call, Throwable t) {
                iApiCallback.onFailure("" + t.getMessage());
            }
        });
    }
}
