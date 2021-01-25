package com.example.duanmauandroid.suaActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmauandroid.ModelClass.sach;
import com.example.duanmauandroid.ModelClass.theLoai;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.database.SachDAO;
import com.example.duanmauandroid.database.TheLoaiDAO;
import com.example.duanmauandroid.themActivity.ThemSachActivity;
import com.example.duanmauandroid.themActivity.ThemTheLoaiActivity;

import java.util.ArrayList;
import java.util.List;

public class SuaSachActivity extends AppCompatActivity {
    EditText edTenSach, edTacGia, edNXB, edGiaBia, edSoLuong;
    Spinner spMaTen;
    String maSach, tenSach, tacGia, NXB, maTheLoai;
    int soLuong, index;
    double giaBia;
    List<theLoai> listTheLoai = new ArrayList<>();
    TheLoaiDAO theLoaiDAO;
    SachDAO sachDAO;

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
        sachDAO = new SachDAO(SuaSachActivity.this);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        maSach = b.getString("MASACH");
        tenSach = b.getString("TENSACH");
        tacGia = b.getString("TACGIA");
        NXB = b.getString("NXB");
        giaBia = b.getDouble("GIABIA");
        soLuong = b.getInt("SOLUONG");
        maTheLoai = b.getString("MATHELOAI");

        theLoaiDAO = new TheLoaiDAO(SuaSachActivity.this);
        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<theLoai> dataAdapter = new ArrayAdapter<theLoai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaTen.setAdapter(dataAdapter);


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
        if (sachDAO.updateSach(new sach(maSach, maTheLoai, edTenSach.getText().toString(), edTacGia.getText().toString(), edNXB.getText().toString(), Double.parseDouble(edGiaBia.getText().toString()), Integer.parseInt(edSoLuong.getText().toString()))) > 0) {
            Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
        }
    }
}