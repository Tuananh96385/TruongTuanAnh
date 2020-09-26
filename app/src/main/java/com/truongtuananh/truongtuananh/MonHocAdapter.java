package com.truongtuananh.truongtuananh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MonHocAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<MonHoc> monHocList;

    public MonHocAdapter(MainActivity context, int layout, List<MonHoc> monHocList) {
        this.context = context;
        this.layout = layout;
        this.monHocList = monHocList;
    }

    @Override
    public int getCount() {
        return monHocList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTenMH;
        TextView txtTenSV;
        TextView txtMaSoSV;
        TextView txtSoTC;
        TextView txtLopHocSV;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if ( view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtTenMH = (TextView) view.findViewById(R.id.textviewTenMH);
            holder.txtTenSV = (TextView) view.findViewById(R.id.textviewTenSV);
            holder.txtMaSoSV = (TextView) view.findViewById(R.id.textviewMaSoSV);
            holder.txtSoTC = (TextView) view.findViewById(R.id.textviewSoTC);
            holder.txtLopHocSV = (TextView) view.findViewById(R.id.textviewLopHocSV);
            holder.imgEdit = (ImageView) view.findViewById(R.id.imageViewEdit);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imageViewDelete);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
       final MonHoc monHoc = monHocList.get(i);

        holder.txtTenMH.setText(monHoc.getTenMH());
        holder.txtTenSV.setText(monHoc.getTenSV());
        holder.txtMaSoSV.setText(monHoc.getMaSoSV());
        holder.txtSoTC.setText(monHoc.getSoTC());
        holder.txtLopHocSV.setText(monHoc.getLopHocSV());

        //bắt sự kiện xoá và sửa
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              context.DialogSuaMonHoc(monHoc.getTenMH(), monHoc.getTenSV(), monHoc.getMaSoSV(), monHoc.getSoTC(), monHoc.getLopHocSV(), monHoc.getIdMH());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoaMonHoc(monHoc.getTenMH(),monHoc.getIdMH());
            }
        });
        return view;
    }
}
