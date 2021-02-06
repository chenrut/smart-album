package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.login.response.LoginResponse;
import com.example.login.util.StringUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Route(path = "/Login/LoginActivity")
public class LoginActivity extends AppCompatActivity {

    private EditText edt_account;
    private EditText edt_password;
    private Button btn_login;
    private TextView account_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        account_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = edt_account.getText().toString().trim();
                String password = edt_password.getText().toString().trim();
                login(account,password);
            }
        });

    }
    private void initView(){
        edt_account = findViewById(R.id.et_account);
        edt_password = findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.btn_login);
        account_register = findViewById(R.id.tv_account_register);
    }

    private void login(String account,String password){
        if(StringUtils.isEmpty(account)){
            Toast.makeText(this,"请输入账号",Toast.LENGTH_SHORT).show();
            return;
        }
        if(StringUtils.isEmpty(password)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        RequestBody requestBody = new FormBody.Builder()
                .add("user_name",account)
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url("http:/10.0.2.2:80" + "/user/login")
                .post(requestBody)
                .build();
        //第四步创建call回调对象
        final Call call = client.newCall(request);
        //第五步发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(result,LoginResponse.class);
                if(loginResponse.getData()){

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    String account = data.getStringExtra("user_name");
                    String password = data.getStringExtra("password");
                    edt_account.setText(account);
                    edt_password.setText(password);
                }
                break;
            default:
        }
    }
}