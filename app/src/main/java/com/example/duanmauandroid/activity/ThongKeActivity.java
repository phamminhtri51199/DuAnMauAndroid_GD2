package com.example.duanmauandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.duanmauandroid.R;
import com.example.duanmauandroid.database.HoaDonChiTietDAO;

public class ThongKeActivity extends AppCompatActivity {
    TextView tvNgay, tvThang, tvNam;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("DOANH THU ");

        tvNgay = (TextView) findViewById(R.id.tvThongKeNgay);
        tvThang = (TextView) findViewById(R.id.tvThongKeThang);
        tvNam = (TextView) findViewById(R.id.tvThongKeNam);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        tvNgay.setText("Hôm nay: " + hoaDonChiTietDAO.getDoanhThuTheoNgay());
        tvThang.setText("Tháng này: " + hoaDonChiTietDAO.getDoanhThuTheoThang());
        tvNam.setText("Năm này: " + hoaDonChiTietDAO.getDoanhThuTheoNam());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}