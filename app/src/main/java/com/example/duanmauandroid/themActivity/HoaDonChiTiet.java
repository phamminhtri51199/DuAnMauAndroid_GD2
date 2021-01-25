package com.example.duanmauandroid.themActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmauandroid.ModelClass.hoaDon;
import com.example.duanmauandroid.ModelClass.hoaDonChiTiet;
import com.example.duanmauandroid.ModelClass.sach;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.adapter.CartAdapter;
import com.example.duanmauandroid.database.HoaDonChiTietDAO;
import com.example.duanmauandroid.database.SachDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTiet extends AppCompatActivity {
    EditText edMaSach, edMaHoaDon, edSoLuong;
    TextView tvThanhTien;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SachDAO sachDAO;
    public List<hoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter;
    double thanhTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);

        setTitle("CHI TIẾT HOÁ ĐƠN");
        edMaSach = (EditText) findViewById(R.id.edMaSach1);
        edMaHoaDon = (EditText) findViewById(R.id.edMaHoaDon1);
        edSoLuong = (EditText) findViewById(R.id.edSoLuong1);
        lvCart = (ListView) findViewById(R.id.lvHoaDonChiTiet);
        tvThanhTien = (TextView) findViewById(R.id.tvThanhTien1);

        
        adapter = new CartAdapter(HoaDonChiTiet.this,dsHDCT);
        lvCart.setAdapter(adapter);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaHoaDon.setText(b.getString("MAHOADON"));
        }
    }


    public void ADDHoaDonCHITIET(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTiet.this);
        sachDAO = new SachDAO(HoaDonChiTiet.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                sach sach = sachDAO.getSachByID(edMaSach.getText().toString());
                if (sach!=null){
                    int pos = checkMaSach(dsHDCT,edMaSach.getText().toString());
                    hoaDon hoaDon = new hoaDon(edMaHoaDon.getText().toString(),new Date());
                    hoaDonChiTiet hoaDonChiTiet = new hoaDonChiTiet(1,hoaDon,sach,Integer.parseInt(edSoLuong.getText().toString()));
                    if (pos>=0){
                        int soluong = dsHDCT.get(pos).getSoLuongMua();
                        hoaDonChiTiet.setSoLuongMua(soluong +
                                Integer.parseInt(edSoLuong.getText().toString()));
                        dsHDCT.set(pos,hoaDonChiTiet);
                    }else {
                        dsHDCT.add(hoaDonChiTiet);
                    }
                    adapter.changeDataset(dsHDCT);
                }else {
                    Toast.makeText(getApplicationContext(),"Mã sách không tồn tại",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTiet.this);
        //tinh tien
        thanhTien = 0;
        try {
            for (hoaDonChiTiet hd: dsHDCT) {
                hoaDonChiTietDAO.inserHoaDonChiTiet(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() *
                        hd.getSach().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " +thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public int checkMaSach(List<hoaDonChiTiet> lsHD, String maSach){
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++){
            hoaDonChiTiet hd = lsHD.get(i);
            if (hd.getSach().getMaSach().equalsIgnoreCase(maSach)){
                pos = i;
                break;
            }
        }
        return pos;
    }
    public int validation(){
        if
        (edMaSach.getText().toString().isEmpty()||edSoLuong.getText().toString().isEmpty()||
                edMaHoaDon.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}