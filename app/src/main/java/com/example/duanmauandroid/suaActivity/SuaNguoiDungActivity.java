package com.example.duanmauandroid.suaActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmauandroid.R;
import com.example.duanmauandroid.activity.NguoiDungActivity;
import com.example.duanmauandroid.database.NguoiDungDAO;

public class SuaNguoiDungActivity extends AppCompatActivity {
    EditText edFullName, edPhone;
    NguoiDungDAO nguoiDungDAO;
    String username, fullname, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_nguoi_dung);
        setTitle("Sủa thông tin người dùng");

        edFullName = (EditText) findViewById(R.id.edSuaTen);
        edPhone = (EditText) findViewById(R.id.edSuaSoDienThoai);
        nguoiDungDAO = new NguoiDungDAO(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        fullname = b.getString("FULLNAME");
        phone = b.getString("PHONE");
        username = b.getString("USERNAME");
        edFullName.setText(fullname);
        edPhone.setText(phone);
    }

    public void updateUser(View view) {
        if
        (nguoiDungDAO.updateInfoNguoiDung(username, edPhone.getText().toString(), edFullName.getText().toString()) > 0) {
            Toast.makeText(getApplicationContext(), "Lưu thành công!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Huy(View view) {
        finish();
    }
}