package com.example.duanmauandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmauandroid.ModelClass.sach;
import com.example.duanmauandroid.R;
import com.example.duanmauandroid.database.SachDAO;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends BaseAdapter implements Filterable {
    List<sach> arrSach;
    List<sach> arrSortSach;
    private Filter sachFilter;
    public Activity context;
    public LayoutInflater inflater;
    SachDAO sachDAO;

    public SachAdapter(Activity context, List<sach> arraySach) {
        super();
        this.context = context;
        this.arrSach = arraySach;
        this.arrSortSach = arraySach;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sachDAO = new SachDAO(context);
    }

    @Override
    public int getCount() {
        return arrSach.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtBookName;
        TextView txtBookPrice;
        TextView txtSoLuong;
        ImageView imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.sach_layout, null);
            holder.img = (ImageView) convertView.findViewById(R.id.imgSach);
            holder.txtBookName = (TextView)
                    convertView.findViewById(R.id.tvMaSach);
            holder.txtBookPrice = (TextView)
                    convertView.findViewById(R.id.tvGia);
            holder.txtSoLuong = (TextView) convertView.findViewById(R.id.tvSoLuong);
//            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ibtnXoaSach);
//            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    sachDAO.deleteSachByID(arrSach.get(position).getMaSach());
//                    arrSach.remove(position);
//                    notifyDataSetChanged();
//                }
//            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        sach _entry = (sach) arrSach.get(position);
        holder.img.setImageResource(R.drawable.bookicon);
        holder.txtBookName.setText("Mã sách: " + _entry.getMaSach());
        holder.txtSoLuong.setText("Số lượng: " + _entry.getSoLuong());
        holder.txtBookPrice.setText("Giá: " + _entry.getGiaBia() + "");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<sach> items) {
        this.arrSach = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrSach = arrSortSach;
    }

    @Override
    public Filter getFilter() {
        if (sachFilter == null)
            sachFilter = new CustomFilter();

        return sachFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortSach;
                results.count = arrSortSach.size();
            } else {
                List<sach> lsSach = new ArrayList<sach>();
                for (sach p : arrSach) {
                    if
                    (p.getMaSach().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsSach.add(p);
                }
                results.values = lsSach;
                results.count = lsSach.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrSach = (List<sach>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
