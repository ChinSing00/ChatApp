package com.example.vogabond.chatapp.discovery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.discovery.model.Datum;
import com.example.vogabond.chatapp.discovery.viewholder.NewListItemHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Selet on 2017/8/20 0020.
 */

public class NewListAdapter extends BaseAdapter {
    private List<Datum> dataLsit;
    private Context context;

    public NewListAdapter(List<Datum> dataLsit, Context context) {
        this.dataLsit = dataLsit;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataLsit.size();
    }

    @Override
    public Datum getItem(int i) {
        return dataLsit.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NewListItemHolder holder = null;
        if(view == null ){
            holder = new NewListItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.new_list_item,null);
            holder.new_imageView = view.findViewById(R.id.new_img);
            holder.new_author = view.findViewById(R.id.new_author);
            holder.new_time = view.findViewById(R.id.new_date);
            holder.new_title = view.findViewById(R.id.new_title);
            view.setTag(holder);
        }else {
            holder = (NewListItemHolder) view.getTag();
        }
        //图片缓存使用了Picasso图片缓存框架
        Picasso.with(context).load(getItem(i).getThumbnailPicS()).into(holder.new_imageView);
        holder.new_author.setText(getItem(i).getAuthorName());
        holder.new_title.setText(getItem(i).getTitle());
        holder.new_time.setText(getItem(i).getDate());
        return view;
    }

}
