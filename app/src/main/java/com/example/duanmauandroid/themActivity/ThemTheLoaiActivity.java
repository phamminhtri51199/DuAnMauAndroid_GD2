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
import com.example.duanmauandroid.ModelClass.theLoai;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.activity.NguoiDungActivity;
import com.example.duanmauandroid.activity.TheLoaiActivity;
import com.example.duanmauandroid.database.TheLoaiDAO;

public class ThemTheLoaiActivity extends AppCompatActivity {

    EditText edMaTheLoai, edTenTheLoai, edViTri, edMoTa;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_the_loai);
        setTitle("Thêm thể loại");
        edMaTheLoai = findViewById(R.id.edMaTheLoai);
        edTenTheLoai = findViewById(R.id.edTenTheLoai);
        edViTri = findViewById(R.id.edViTri);
        edMoTa = findViewById(R.id.edMoTa);

    }

    public void xemTheLoai(View view) {
        finish();
    }

    public void huyTheLoai(View view) {
        finish();
    }

    public void themTheLoai(View view) {
        theLoaiDAO = new TheLoaiDAO(ThemTheLoaiActivity.this);

        try {
            if (edViTri.getText().toString().length() == 0 || edMaTheLoai.getText().toString().isEmpty() || edMoTa.getText().toString().isEmpty() || edTenTheLoai.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
            } else {
                theLoai user = new theLoai(edMaTheLoai.getText().toString(), edTenTheLoai.getText().toString(), edMoTa.getText().toString(), Integer.parseInt(edViTri.getText().toString()));
                if (theLoaiDAO.inserTheLoai(user) > 0) {
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
}