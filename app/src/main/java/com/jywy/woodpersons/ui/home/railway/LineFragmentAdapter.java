package com.jywy.woodpersons.ui.home.railway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jywy.woodpersons.R;

import java.util.ArrayList;

/**
 * Created by ren on 2017/3/20.
 */

public class LineFragmentAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<String> list=new ArrayList<String>();
    public LineFragmentAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.line_show_list,null);
            holder.time= (TextView) convertView.findViewById(R.id.time);
            holder.train= (TextView) convertView.findViewById(R.id.train);
            holder.total= (TextView) convertView.findViewById(R.id.raw_wood);
            holder.raw_wood= (TextView) convertView.findViewById(R.id.raw_wood);
            holder.board_wood= (TextView) convertView.findViewById(R.id.board_wood);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
//        holder.time.setText(list.get(position).gettime);
//        holder.train.setText(list.get(position).gettime);
//        holder.total.setText(list.get(position).gettime);
//        holder.board_wood.setText(list.get(position).gettime);
//        holder.raw_wood.setText(list.get(position).gettime);


        return convertView;
    }

    public class ViewHolder{
        TextView time;//时间
        TextView train;//第几列
        TextView total;//到货量
        TextView raw_wood;//原木
        TextView board_wood;//板木
    }
}


