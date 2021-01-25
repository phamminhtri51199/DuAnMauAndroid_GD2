package com.example.duanmauandroid.themActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmauandroid.ModelClass.nguoiDung;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.activity.NguoiDungActivity;
import com.example.duanmauandroid.database.NguoiDungDAO;

public class ThemNguoiDungActivity extends AppCompatActivity {

    Button btnThemNguoiDung;
    NguoiDungDAO nguoiDungDAO;
    EditText edUser, edPass, edRePass, edPhone, edFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nguoi_dung);
        btnThemNguoiDung = (Button) findViewById(R.id.btnLuuNguoiDung);
        edUser = (EditText) findViewById(R.id.edTenDangNhap);
        edPass = (EditText) findViewById(R.id.edMatKhau);
        edPhone = (EditText) findViewById(R.id.edSoDienThoai);
        edFullName = (EditText) findViewById(R.id.edHoTen);
        edRePass = (EditText) findViewById(R.id.edNhacLaiMatKhau);
    }

    public void showUsers(View view) {
        Intent intent = new Intent(ThemNguoiDungActivity.this, NguoiDungActivity.class);
        startActivity(intent);
    }

    public void huy(View view) {
        finish();
    }

    public void addUser(View view) {
        nguoiDungDAO = new NguoiDungDAO(ThemNguoiDungActivity.this);
        nguoiDung user = new nguoiDung(edUser.getText().toString(),
                edPass.getText().toString(),
                edPhone.getText().toString(), edFullName.getText().toString());
        try {
            if (validateForm() > 0) {
                if (nguoiDungDAO.inserNguoiDung(user) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int validateForm() {
        int check = 1;
        if (edUser.getText().length() == 0 || edFullName.getText().length() == 0
                || edPhone.getText().length() == 0 || edPass.getText().length() == 0
                || edRePass.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin ",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp",
                        Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}