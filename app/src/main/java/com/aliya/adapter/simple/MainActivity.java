package com.aliya.adapter.simple;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aliya.adapter.RecyclerAdapter;
import com.aliya.adapter.RecyclerViewHolder;
import com.aliya.adapter.divider.ListSpaceDivider;
import com.aliya.adapter.simple.activity.AdapterUnifyDataSimpleActivity;
import com.aliya.adapter.simple.activity.EmptyPageSimpleActivity;
import com.aliya.adapter.simple.activity.OverlaySimpleActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycle;

    private static List<Entity> items = new ArrayList<>();

    static {
        items.add(new Entity("普通用法 - 示例", AdapterUnifyDataSimpleActivity.class));
        items.add(new Entity("Adapter空页面 - 示例", EmptyPageSimpleActivity.class));
        items.add(new Entity("悬浮吸顶 - 示例", OverlaySimpleActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycle = findViewById(R.id.recycler);

        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.addItemDecoration(new ListSpaceDivider(1, Color.parseColor("#cccccc"), false));

        recycle.setAdapter(new Adapter(items));

    }

    static class Entity {

        String content;

        Class activity;

        public Entity(String content, Class activity) {
            this.content = content;
            this.activity = activity;
        }

        public String getContent() {
            return content;
        }

        public Entity setContent(String content) {
            this.content = content;
            return this;
        }

        public Class getActivity() {
            return activity;
        }

        public Entity setActivity(Class activity) {
            this.activity = activity;
            return this;
        }
    }

    static class Adapter extends RecyclerAdapter<Entity> {

        public Adapter(List<Entity> data) {
            super(data);
        }

        @Override
        public RecyclerViewHolder onAbsCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }
    }

    static class ViewHolder extends RecyclerViewHolder<Entity> implements View.OnClickListener {

        TextView mTextView;

        public ViewHolder(@NonNull ViewGroup parent) {
            super(inflate(R.layout.item_main_simple, parent, false));
            mTextView = itemView.findViewById(R.id.tv_item);
            mTextView.setOnClickListener(this);
        }

        @Override
        public void bindView(Entity data) {
            mTextView.setText(data.getContent());
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            context.startActivity(new Intent(context, mData.getActivity()));
        }
    }

}
