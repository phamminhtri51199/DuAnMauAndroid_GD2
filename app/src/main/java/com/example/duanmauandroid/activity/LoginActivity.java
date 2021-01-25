package com.example.duanmauandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmauandroid.R;
import com.example.duanmauandroid.database.NguoiDungDAO;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnLogin, btnCancel;
    CheckBox chkRememberPass;
    String strUser, strPass;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("ĐĂNG NHẬP ");
        edUserName = (EditText) findViewById(R.id.edUserName);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        chkRememberPass = (CheckBox) findViewById(R.id.chkRememberPass);
        nguoiDungDAO = new NguoiDungDAO(LoginActivity.this);


    }

    public void out(View view) {
        finish();
    }

    public void checkLogin(View v) {
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();
        if (strUser.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được bỏ trống!",
                    Toast.LENGTH_SHORT).show();
        } else {

            if
            (strUser.equalsIgnoreCase("admin") && strPass.equalsIgnoreCase("admin")) {
                rememberUser(strUser, strPass, chkRememberPass.isChecked());
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không đúng",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            //xoa tinh trang luu tru truoc do
            edit.clear();
        } else {
            //luu du lieu
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        //luu lai toan bo
        edit.commit();
    }
}