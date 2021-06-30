package com.example.zypherassessment.WebServiceUtils;

import com.example.zypherassessment.WebServiceUtils.ResponseModel.testDataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestAPIService {


    @POST("testData")
    Call<testDataModel> testDataAPI();
}
