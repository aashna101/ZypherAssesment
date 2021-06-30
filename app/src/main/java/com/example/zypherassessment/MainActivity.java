package com.example.zypherassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.widget.Toast;

import com.example.zypherassessment.WebServiceUtils.APICall;
import com.example.zypherassessment.WebServiceUtils.IApiCallback;
import com.example.zypherassessment.WebServiceUtils.ResponseModel.testDataModel;

import java.util.ArrayList;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IApiCallback {

    private GestureDetectorCompat gestureDetectorCompat = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APICall.getInstance(this).TestData(this);

    }

    @Override
    public void onSuccess(Object type, Object data) {
        if (type == "testdata") {
            Response<testDataModel> response = (Response<testDataModel>) data;
            if(response.isSuccessful())
            {
                if(response != null)
                {
                    testDataModel model = response.body();
                    ClassB.ShowDialogBox1(model.title, model.imageURL,model.success_url,this);
                }
            }
            else
            {
                Toast.makeText(this,"Api call failed: "+ response.errorBody(),Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Object data) {

    }
}