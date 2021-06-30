package com.example.zypherassessment.WebServiceUtils;

public interface IApiCallback {
    void onSuccess(Object type, Object data);

    void onFailure(Object data);
}
