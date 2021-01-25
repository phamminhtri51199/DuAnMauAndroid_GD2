package com.example.duanmauandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.duanmauandroid.ModelClass.hoaDonChiTiet;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.database.HoaDonChiTietDAO;
import com.example.duanmauandroid.adapter.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListHoaDonChiTietActivity extends AppCompatActivity {
    public List<hoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don_chi_tiet);
        setTitle("HOÁ ĐƠN CHI TIẾT");
        lvCart = (ListView) findViewById(R.id.lvCard);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(ListHoaDonChiTietActivity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            dsHDCT = hoaDonChiTietDAO.getAllHoaDonChiTietByID(b.getString("MAHOADON"));
        }
        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
    }
}