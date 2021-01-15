package com.example.duanmauandroid.suaActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.duanmauandroid.ModelClass.theLoai;
import com.example.duanmauandroid.R;

import java.util.ArrayList;
import java.util.List;

public class SuaSachActivity extends AppCompatActivity {
    EditText edTenSach, edTacGia, edNXB, edGiaBia, edSoLuong;
    Spinner spMaTen;
    String maSach, tenSach, tacGia, NXB, maTheLoai;
    int soLuong;
    double giaBia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_sach);
        edTenSach = findViewById(R.id.edSuaTenSach);
        edTacGia = findViewById(R.id.edSuaTacGia);
        edNXB = findViewById(R.id.edSuaNhaXuatBan);
        edGiaBia = findViewById(R.id.edSuaGiaBia);
        edSoLuong = findViewById(R.id.edSuaSoLuong);
        spMaTen = findViewById(R.id.spSuaMaTen);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        maSach = b.getString("MASACH");
        tenSach = b.getString("TENSACH");
        tacGia = b.getString("TACGIA");
        NXB = b.getString("NXB");
        giaBia = b.getDouble("GIABIA");
        soLuong = b.getInt("SOLUONG");
        maTheLoai = b.getString("MATHELOAI");

        edTenSach.setText(tenSach);
        edTacGia.setText(tacGia);
        edNXB.setText(NXB);
        edGiaBia.setText(String.valueOf(giaBia));
        edSoLuong.setText(String.valueOf(soLuong));

    }

    public void Huy(View view) {
        finish();
    }

    public void updateSach(View view) {

    }
}