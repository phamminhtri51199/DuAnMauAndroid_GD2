package com.example.duanmauandroid.themActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmauandroid.ModelClass.sach;
import com.example.duanmauandroid.ModelClass.theLoai;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.database.SachDAO;
import com.example.duanmauandroid.database.TheLoaiDAO;

import java.util.ArrayList;
import java.util.List;

public class ThemSachActivity extends AppCompatActivity {
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    Spinner spnTheLoai;
    List<theLoai> listTheLoai = new ArrayList<>();
    EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    String maTheLoai = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thêm sách");
        setContentView(R.layout.activity_them_sach);
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edTenSach = (EditText) findViewById(R.id.edTenSach);
        edNXB = (EditText) findViewById(R.id.edNhaXuatBan);
        edTacGia = (EditText) findViewById(R.id.edTacGia);
        edGiaBia = (EditText) findViewById(R.id.edGiaBia);
        edSoLuong = (EditText) findViewById(R.id.edSoLuong);
        spnTheLoai = findViewById(R.id.spTheLoai);
        getTheLoai();
    }

    public void getTheLoai() {
        theLoaiDAO = new TheLoaiDAO(ThemSachActivity.this);
        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<theLoai> dataAdapter = new ArrayAdapter<theLoai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(dataAdapter);
    }

    public void addBook(View view) {
        sachDAO = new SachDAO(ThemSachActivity.this);

        try {
            if (edMaSach.getText().toString().length() == 0 || edTenSach.getText().toString().isEmpty() || edTacGia.getText().toString().isEmpty()
                    || edNXB.getText().toString().isEmpty() || edGiaBia.getText().toString().isEmpty() || edSoLuong.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
            } else {
                sach sach = new sach(edMaSach.getText().toString(), maTheLoai, edTenSach.getText().toString(),
                        edTacGia.getText().toString(), edNXB.getText().toString(),
                        Double.parseDouble(edGiaBia.getText().toString()), Integer.parseInt(edSoLuong.getText().toString()));
                if (sachDAO.inserSach(sach) > 0) {
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

    public void showBook(View view) {
        finish();
    }

    public void themTheLoai1(View view) {
        Intent intent = new Intent(ThemSachActivity.this, ThemTheLoaiActivity.class);
        startActivity(intent);
    }
}