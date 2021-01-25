package com.example.duanmauandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.duanmauandroid.ModelClass.hoaDon;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.adapter.HoaDonAdapter;
import com.example.duanmauandroid.database.HoaDonDAO;
import com.example.duanmauandroid.themActivity.ThemHoaDonActivity;
import com.example.duanmauandroid.themActivity.ThemTheLoaiActivity;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonActivity extends AppCompatActivity {
    public List<hoaDon> dsHoaDon = new ArrayList<>();
    ListView lvHoaDon;
    HoaDonAdapter adapter = null;
    HoaDonDAO hoaDonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        setTitle("HOÁ ĐƠN ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvHoaDon = (ListView) findViewById(R.id.lvHoaDon);
        hoaDonDAO = new HoaDonDAO(HoaDonActivity.this);
        try {
            dsHoaDon = hoaDonDAO.getAllHoaDon();
        } catch (Exception e) {
            Log.d("Error: ", e.toString());
        }
        adapter = new HoaDonAdapter(this, dsHoaDon);
        lvHoaDon.setAdapter(adapter);
        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hoaDon hoaDon = (hoaDon) parent.getItemAtPosition(position);
                Intent intent = new Intent(HoaDonActivity.this, ListHoaDonChiTietActivity.class);

                Bundle b = new Bundle();
                b.putString("MAHOADON", hoaDon.getMaHoaDon());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        // TextFilter
        lvHoaDon.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edTimHoaDon);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before[" + before + "] - Count[" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            dsHoaDon = hoaDonDAO.getAllHoaDon();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter.changeDataset(dsHoaDon);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_the_loai, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.themTheLoai) {
            Intent intent = new Intent(HoaDonActivity.this, ThemHoaDonActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}