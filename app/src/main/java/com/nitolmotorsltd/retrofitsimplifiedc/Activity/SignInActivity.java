package com.nitolmotorsltd.retrofitsimplifiedc.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nitolmotorsltd.retrofitsimplifiedc.Api.RetrofitClient;
import com.nitolmotorsltd.retrofitsimplifiedc.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";

    private EditText et_email, et_password;
    private Button btn_signin;
    private TextView txt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        btn_signin = findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        txt_back = findViewById(R.id.txt_back);
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loginUser() {
        Log.d(TAG, "loginUser: ");

        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (email.isEmpty()) {
            et_email.setError("Email is required");
            et_email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            et_password.setError("Password is required");
            et_password.requestFocus();
            return;
        }

        Call<ResponseBody> responseBodyCall = RetrofitClient.getInstance().getApi().loginuser(email, password);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Toast.makeText(SignInActivity.this, "Login successfull.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: " + response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Login failed.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Login failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
