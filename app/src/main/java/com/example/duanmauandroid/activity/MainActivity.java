package com.example.duanmauandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.duanmauandroid.R;

public class MainActivity extends AppCompatActivity {

    ImageButton ibtnNguoiDung, ibtnTheLoai, ibtnSach, ibtnHoaDon, ibtnSachBanChay, ibtnThongKe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("QUẢN LÍ SÁCH");
        ibtnNguoiDung = findViewById(R.id.ibtnNguoiDung);
        ibtnTheLoai = findViewById(R.id.ibtnTheLoai);
        ibtnSach = findViewById(R.id.ibtnSach);
        ibtnHoaDon = findViewById(R.id.ibtnHoaDon);
        ibtnSachBanChay = findViewById(R.id.ibtnSachBanChay);
        ibtnThongKe = findViewById(R.id.ibtnThongKe);

        ibtnNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NguoiDungActivity.class);
                startActivity(intent);
            }
        });

        ibtnTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TheLoaiActivity.class);
                startActivity(intent);
            }
        });

        ibtnSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SachActivity.class);
                startActivity(intent);
            }
        });

        ibtnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HoaDonActivity.class);
                startActivity(intent);
            }
        });

        ibtnSachBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SachBanChayActivity.class);
                startActivity(intent);
            }
        });

        ibtnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThongKeActivity.class);
                startActivity(intent);
            }
        });
    }
}