package com.aliya.adapter.simple.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aliya.adapter.simple.R;

import java.util.List;

/**
 * 原始Adapter常用方式 - 示例
 *
 * @author a_liYa
 * @date 2017/8/23 20:17.
 */
public class OriginalAdapterSimple extends RecyclerView.Adapter {

    List<String> datas;

    public OriginalAdapterSimple(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Holder) holder).tv.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView tv;

        public Holder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demo_simple, parent,
                    false));
            tv = itemView.findViewById(R.id.tv);
        }

    }
}
