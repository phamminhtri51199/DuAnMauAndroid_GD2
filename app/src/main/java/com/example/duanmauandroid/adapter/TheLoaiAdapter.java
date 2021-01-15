package com.example.duanmauandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmauandroid.ModelClass.theLoai;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.database.TheLoaiDAO;

import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    List<theLoai> arrTheLoai;
    public Activity context;
    public LayoutInflater inflater;
    TheLoaiDAO theLoaiDAO;

    public TheLoaiAdapter(Activity context, List<theLoai> arrayTheLoai) {
        super();
        this.context = context;
        this.arrTheLoai = arrayTheLoai;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theLoaiDAO = new TheLoaiDAO(context);
    }

    @Override
    public int getCount() {
        return arrTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arrTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtMaTheLoai;
        TextView txtTenTheLoai;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.the_loai_layout, null);
            holder.img = (ImageView) convertView.findViewById(R.id.imgTheLoai);
            holder.txtMaTheLoai = (TextView)
                    convertView.findViewById(R.id.tvMaTheLoai);
            holder.txtTenTheLoai = (TextView)
                    convertView.findViewById(R.id.tvTenTheLoai);
//            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ibtnXoaTheLoai);
//            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    theLoaiDAO.deleteTheLoaiByID(arrTheLoai.get(position).getMaTheLoai());
//                    arrTheLoai.remove(position);
//                    notifyDataSetChanged();
//                }
//            });
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        theLoai _entry = (theLoai) arrTheLoai.get(position);
        holder.img.setImageResource(R.drawable.cateicon);
        holder.txtMaTheLoai.setText("Mã thể loại: " + _entry.getMaTheLoai());
        holder.txtTenTheLoai.setText("Tên thể loại: " + _entry.getTenTheLoai());
        return convertView;
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<theLoai> items) {
        this.arrTheLoai = items;
        notifyDataSetChanged();
    }
}
