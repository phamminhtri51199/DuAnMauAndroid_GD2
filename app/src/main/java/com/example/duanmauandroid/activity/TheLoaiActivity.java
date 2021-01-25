package com.example.duanmauandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.duanmauandroid.ModelClass.theLoai;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.adapter.TheLoaiAdapter;
import com.example.duanmauandroid.database.TheLoaiDAO;
import com.example.duanmauandroid.suaActivity.SuaTheLoaiActivity;
import com.example.duanmauandroid.themActivity.ThemNguoiDungActivity;
import com.example.duanmauandroid.themActivity.ThemTheLoaiActivity;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiActivity extends AppCompatActivity {
    public static List<theLoai> dsTheLoai = new ArrayList<>();
    ListView lvTheLoai;
    TheLoaiAdapter adapter = null;
    TheLoaiDAO theLoaiDAO;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THỂ LOẠI");
        setContentView(R.layout.activity_the_loai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvTheLoai = (ListView) findViewById(R.id.lvTheLoai);
        registerForContextMenu(lvTheLoai);
        theLoaiDAO = new TheLoaiDAO(TheLoaiActivity.this);
        dsTheLoai = theLoaiDAO.getAllTheLoai();
        adapter = new TheLoaiAdapter(this, dsTheLoai);
        lvTheLoai.setAdapter(adapter);

        registerForContextMenu(lvTheLoai);
        lvTheLoai.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsTheLoai.clear();
        dsTheLoai = theLoaiDAO.getAllTheLoai();
        adapter.changeDataset(dsTheLoai);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_the_loai, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.themTheLoai) {
            Intent intent = new Intent(TheLoaiActivity.this, ThemTheLoaiActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_sua_xoa, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.suathongtin) {
            Intent intent = new
                    Intent(TheLoaiActivity.this, SuaTheLoaiActivity.class);
            Bundle b = new Bundle();
            b.putInt("INDEX", index);
            b.putString("MATHELOAI", dsTheLoai.get(index).getMaTheLoai());
            b.putString("TENTHELOAI", dsTheLoai.get(index).getTenTheLoai());
            b.putString("MOTA", dsTheLoai.get(index).getMoTa());
            b.putString("VITRI",
                    String.valueOf(dsTheLoai.get(index).getViTri()));
            intent.putExtras(b);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.xoathongtin) {
            theLoaiDAO.deleteTheLoaiByID(dsTheLoai.get(index).getMaTheLoai());
            dsTheLoai.remove(index);
            adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }
}