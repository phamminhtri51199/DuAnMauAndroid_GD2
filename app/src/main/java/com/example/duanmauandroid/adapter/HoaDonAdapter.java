package com.example.duanmauandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmauandroid.ModelClass.hoaDon;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.database.HoaDonChiTietDAO;
import com.example.duanmauandroid.database.HoaDonDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    List<hoaDon> arrHoaDon;
    List<hoaDon> arrSortHoaDon;
    private Filter hoaDonFilter;
    public Activity context;
    public LayoutInflater inflater;
    HoaDonDAO hoadonDAO;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public HoaDonAdapter(Activity context, List<hoaDon> arrayHoaDon) {
        super();
        this.context = context;
        this.arrHoaDon = arrayHoaDon;
        this.arrSortHoaDon = arrayHoaDon;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoadonDAO = new HoaDonDAO(context);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }

    @Override
    public int getCount() {
        return arrHoaDon.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtMaHoaDon;
        TextView txtNgayMua;
        ImageView imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.hoa_don_layout, null);
            holder.img = (ImageView) convertView.findViewById(R.id.imgHoaDon);
            holder.txtMaHoaDon = (TextView)
                    convertView.findViewById(R.id.tvMaHoaDon);
            holder.txtNgayMua = (TextView) convertView.findViewById(R.id.tvNgayMua);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ibtnXoaHoaDon);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if
                    (hoaDonChiTietDAO.checkHoaDon(arrHoaDon.get(position).getMaHoaDon())) {
                        Toast.makeText(context, "Bạn phải xoá hoá đơn chi tiết trước khi xoá hoá đơn này", Toast.LENGTH_LONG).show();
                    } else {
                        hoadonDAO.deleteHoaDonByID(arrHoaDon.get(position).getMaHoaDon());
                        arrHoaDon.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        hoaDon _entry = (hoaDon) arrHoaDon.get(position);
        holder.img.setImageResource(R.drawable.hdicon);
        holder.txtMaHoaDon.setText(_entry.getMaHoaDon());
        holder.txtNgayMua.setText(sdf.format(_entry.getNgayMua()));
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<hoaDon> items) {
        this.arrHoaDon = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrHoaDon = arrSortHoaDon;
    }

    public Filter getFilter() {
        if (hoaDonFilter == null)
            hoaDonFilter = new CustomFilter();
        return hoaDonFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortHoaDon;
                results.count = arrSortHoaDon.size();
            } else {
                List<hoaDon> lsHoaDon = new ArrayList<hoaDon>();
                for (hoaDon p : arrHoaDon) {
                    if
                    (p.getMaHoaDon().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsHoaDon.add(p);
                }
                results.values = lsHoaDon;
                results.count = lsHoaDon.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrHoaDon = (List<hoaDon>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}

