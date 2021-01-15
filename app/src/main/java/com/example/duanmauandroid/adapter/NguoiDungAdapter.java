package com.example.duanmauandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.duanmauandroid.ModelClass.nguoiDung;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.database.NguoiDungDAO;

import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    List<nguoiDung> arrNguoiDung;
    public Activity context;
    public LayoutInflater inflater;
    NguoiDungDAO nguoiDungDAO;

    public NguoiDungAdapter(Activity context, List<nguoiDung> arrNguoiDung) {
        super();
        this.arrNguoiDung = arrNguoiDung;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.nguoiDungDAO = new NguoiDungDAO(context);
    }

    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoiDung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.nguoi_dung_layout, null);
            holder.img = (ImageView) convertView.findViewById(R.id.imgAnhDaiDien);
            holder.txtName = (TextView) convertView.findViewById(R.id.txtTen);
            holder.txtPhone = (TextView) convertView.findViewById(R.id.txtSoDienThoai);
//            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ibtnXoaNguoiDung);
//            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    nguoiDungDAO.deleteNguoiDungByID(arrNguoiDung.get(position).getUserName());
//                    arrNguoiDung.remove(position);
//                    notifyDataSetChanged();
//                }
//            });
            convertView.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();
        nguoiDung _entry = (nguoiDung) arrNguoiDung.get(position);
        if (position % 3 == 0) {
            holder.img.setImageResource(R.drawable.emone);
        } else if (position % 3 == 1) {
            holder.img.setImageResource(R.drawable.emtwo);
        } else {
            holder.img.setImageResource(R.drawable.emthree);
        }
        holder.txtName.setText("Tên: " + _entry.getHoTen());
        holder.txtPhone.setText("SĐT: " + _entry.getPhone());
        return convertView;
    }

    public void changeDataset(List<nguoiDung> items) {
        this.arrNguoiDung = items;
        notifyDataSetChanged();
    }
}
