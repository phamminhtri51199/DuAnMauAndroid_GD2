package com.example.duanmauandroid.suaActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmauandroid.ModelClass.theLoai;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.activity.NguoiDungActivity;
import com.example.duanmauandroid.activity.TheLoaiActivity;
import com.example.duanmauandroid.database.DatabaseHelper;
import com.example.duanmauandroid.database.NguoiDungDAO;
import com.example.duanmauandroid.database.TheLoaiDAO;

import java.util.List;

public class SuaTheLoaiActivity extends AppCompatActivity {
    EditText edTenTheLoai, edMota, edViTri;
    TheLoaiDAO theLoaiDAO;
    String maTheLoai, tenTheLoai, moTa, viTri;
    int index;
    List<theLoai> theLoaiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_the_loai);
        setTitle("Sửa thể loại");

        edTenTheLoai = findViewById(R.id.edSuaTenTheLoai);
        edMota = findViewById(R.id.edSuaMoTa);
        edViTri = findViewById(R.id.edSuaViTri);
        theLoaiDAO = new TheLoaiDAO(SuaTheLoaiActivity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        maTheLoai = b.getString("MATHELOAI" );
        tenTheLoai = b.getString("TENTHELOAI");
        moTa = b.getString("MOTA");
        viTri = b.getString("VITRI");
        index = b.getInt("INDEX");
        edTenTheLoai.setText(tenTheLoai);
        edMota.setText(moTa);
        edViTri.setText(viTri);
    }

    public void Huy(View view) {
        finish();
    }

    public void updateTheLoai(View view) {
        if (theLoaiDAO.updateTheLoai(new theLoai(maTheLoai, edTenTheLoai.getText().toString(), edMota.getText().toString(), Integer.parseInt(edViTri.getText().toString()))) > 0) {
            Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
        }
    }
}