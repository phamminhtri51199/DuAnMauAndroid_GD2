package com.example.duanmauandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.duanmauandroid.ModelClass.sach;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.adapter.SachAdapter;
import com.example.duanmauandroid.database.SachDAO;
import com.example.duanmauandroid.suaActivity.SuaSachActivity;
import com.example.duanmauandroid.suaActivity.SuaTheLoaiActivity;
import com.example.duanmauandroid.themActivity.ThemSachActivity;
import com.example.duanmauandroid.themActivity.ThemTheLoaiActivity;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity {
    public static List<sach> dsSach = new ArrayList<>();
    ListView lvBook;
    SachAdapter adapter = null;
    SachDAO sachDAO;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("QUẢN LÝ SÁCH ");
        setContentView(R.layout.activity_sach);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvBook = (ListView) findViewById(R.id.lvSach);
        sachDAO = new SachDAO(SachActivity.this);
        dsSach = sachDAO.getAllSach();
        adapter = new SachAdapter(this, dsSach);
        lvBook.setAdapter(adapter);

        lvBook.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                return false;
            }
        });
        registerForContextMenu(lvBook);

        EditText edTimSach = findViewById(R.id.edTimSach);

        lvBook.setTextFilterEnabled(true);
        edTimSach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before[" + before + "] - Count[" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsSach = sachDAO.getAllSach();
        adapter.changeDataset(dsSach);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_the_loai, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.themTheLoai) {
            Intent intent = new Intent(SachActivity.this, ThemSachActivity.class);
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
            Intent intent = new Intent(SachActivity.this, SuaSachActivity.class);
            Bundle b = new Bundle();
            b.putString("MASACH", dsSach.get(index).getMaSach());
            b.putString("MATHELOAI", dsSach.get(index).getMaTheLoai());
            b.putString("TENSACH", dsSach.get(index).getTenSach());
            b.putString("TACGIA", dsSach.get(index).getTacGia());
            b.putString("NXB", dsSach.get(index).getNXB());
            b.putDouble("GIABIA", dsSach.get(index).getGiaBia());
            b.putInt("SOLUONG", dsSach.get(index).getSoLuong());
            intent.putExtras(b);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.xoathongtin) {
            sachDAO.deleteSachByID(dsSach.get(index).getMaTheLoai());
            dsSach.remove(index);
            adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }
}