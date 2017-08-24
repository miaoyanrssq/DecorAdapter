package com.aliya.adapter;

import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import java.util.List;

/**
 * Recycler.Adapter的封装
 *
 * @author a_liYa
 * @date 2017/8/24 15:42.
 */
public abstract class BaseRecyclerAdapter<T extends Object> extends DecorAdapter {

    public List<T> datas;

    public BaseRecyclerAdapter(List<T> data) {
        this.datas = data;
    }

    @CallSuper
    @Override
    public int getItemCount() {
        return super.getItemCount() + (datas == null ? 0 : datas.size());
    }

    @Override
    public final void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (isInnerPosition(position)) return; // super.onBindViewHolder已经处理
        if (!onAbsBindViewHolder(holder, cleanPosition(position))) { // 没有拦截
            ((BaseRecyclerViewHolder) holder).setData(getData(cleanPosition(position)));
        }
    }

    public final T getData(int index) {
        if (datas != null && index < datas.size() && index >= 0) {
            return datas.get(index);
        }
        return null;
    }

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = super.onCreateViewHolder(parent, viewType);
        if (holder == null) {
            holder = onAbsCreateViewHolder(parent, viewType);
        }
        return holder;
    }

    @Override
    public final int getItemViewType(int position) {
        int viewType = super.getItemViewType(position);
        if (viewType == DEFAULT_VIEW_TYPE) {
            viewType = getAbsItemViewType(cleanPosition(position));
        }
        return viewType;
    }

    /**
     * 自定义重写getItemViewType
     *
     * @param position 屏蔽了内部其他内部item转换过的索引
     * @return int类型
     */
    public int getAbsItemViewType(int position) {
        return DEFAULT_VIEW_TYPE;
    }

    /**
     * see onBindViewHolder
     *
     * @param holder   ViewHolder
     * @param position 当前绑定的下标
     * @return true 表示拦截的自动bindView方法 需要自己处理, 默认false
     * @see RecyclerView.Adapter#onBindViewHolder(ViewHolder, int)
     */
    public boolean onAbsBindViewHolder(ViewHolder holder, int position) {
        return false;
    }

    /**
     * see onCreateViewHolder
     *
     * @param parent   .
     * @param viewType .
     * @return .
     * @see RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)
     */
    public abstract BaseRecyclerViewHolder onAbsCreateViewHolder(ViewGroup parent, int viewType);

}