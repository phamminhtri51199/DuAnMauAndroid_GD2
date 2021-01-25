package com.example.duanmauandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duanmauandroid.ModelClass.sach;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.adapter.SachAdapter;
import com.example.duanmauandroid.database.SachDAO;

import java.util.ArrayList;
import java.util.List;

public class SachBanChayActivity extends AppCompatActivity {
    public static List<sach> dsSach = new ArrayList<>();
    ListView lvBook;
    SachAdapter adapter = null;
    SachDAO sachDAO;
    EditText edThang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_ban_chay);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("TOP 10 SÁCH BÁN CHẠY");
        lvBook = (ListView) findViewById(R.id.lvSachBanChay);
        edThang = (EditText) findViewById(R.id.edThang);
    }

    public void VIEW_SACH_TOP_10(View view) {
        if (Integer.parseInt(edThang.getText().toString()) > 13 || Integer.parseInt(edThang.getText().toString()) < 0) {
            Toast.makeText(getApplicationContext(), "Không đúng định dạng tháng (1- 12)", Toast.LENGTH_SHORT).show();
        } else {
            sachDAO = new SachDAO(SachBanChayActivity.this);
            dsSach = sachDAO.getSachTop10(edThang.getText().toString());
            adapter = new SachAdapter(SachBanChayActivity.this, dsSach);
            lvBook.setAdapter(adapter);
        }
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