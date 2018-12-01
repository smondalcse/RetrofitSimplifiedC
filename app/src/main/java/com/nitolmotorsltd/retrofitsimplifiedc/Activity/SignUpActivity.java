package com.nitolmotorsltd.retrofitsimplifiedc.Activity;

import android.content.Intent;
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

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";

    private EditText et_name, et_email, et_password, et_Address;
    private Button btn_signup;
    private TextView txt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_Address = findViewById(R.id.et_Address);

        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
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

    private void createUser() {
        Log.d(TAG, "createUser: ");

        String name = et_name.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String address = et_Address.getText().toString().trim();

        // Validation
        if (name.isEmpty()) {
            et_name.setError("Name is required");
            et_name.requestFocus();
            return;
        }

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

        if (address.isEmpty()) {
            et_Address.setError("Address is required");
            et_Address.requestFocus();
            return;
        }
        // Pass the validation

        Call<ResponseBody> responseBodyCall = RetrofitClient.getInstance().getApi().createUser(name, email, password, address);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Toast.makeText(SignUpActivity.this, "User Create successfull.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: " + response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "User creation failed.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "User creation failed.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
