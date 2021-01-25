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

import com.example.duanmauandroid.DoiMatKhauActivity;
import com.example.duanmauandroid.ModelClass.nguoiDung;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.adapter.NguoiDungAdapter;
import com.example.duanmauandroid.database.NguoiDungDAO;
import com.example.duanmauandroid.suaActivity.SuaNguoiDungActivity;
import com.example.duanmauandroid.themActivity.ThemNguoiDungActivity;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungActivity extends AppCompatActivity {
    public static List<nguoiDung> dsNguoiDung = new ArrayList<>();
    ListView lvNguoiDung;
    NguoiDungAdapter adapter = null;
    NguoiDungDAO nguoiDungDAO;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Người dùng");
        setContentView(R.layout.activity_nguoi_dung);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvNguoiDung = findViewById(R.id.lvNguoiDung);
        nguoiDungDAO = new NguoiDungDAO(NguoiDungActivity.this);
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        adapter = new NguoiDungAdapter(NguoiDungActivity.this, dsNguoiDung);
        lvNguoiDung.setAdapter(adapter);

        lvNguoiDung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                return false;
            }
        });

        registerForContextMenu(lvNguoiDung);

    }

    @Override
    protected void onResume() {
        super.onResume();
        dsNguoiDung.clear();
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        adapter.changeDataset(dsNguoiDung);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menunguoidung, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.themNguoiDung) {
            Intent intent = new Intent(NguoiDungActivity.this, ThemNguoiDungActivity.class);
            startActivity(intent);

        }
        if (item.getItemId() == R.id.doiMatKhau) {
            Intent intent = new Intent(NguoiDungActivity.this, DoiMatKhauActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        if (item.getItemId() == R.id.dangXuat) {
            Intent intent = new Intent(NguoiDungActivity.this, LoginActivity.class);
            startActivity(intent);
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
                    Intent(NguoiDungActivity.this, SuaNguoiDungActivity.class);
            Bundle b = new Bundle();
            b.putString("USERNAME", dsNguoiDung.get(index).getUserName());
            b.putString("PHONE", dsNguoiDung.get(index).getPhone());
            b.putString("FULLNAME", dsNguoiDung.get(index).getHoTen());
            intent.putExtras(b);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.xoathongtin) {
            nguoiDungDAO.deleteNguoiDungByID(dsNguoiDung.get(index).getUserName());
            dsNguoiDung.remove(index);
            adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }
}