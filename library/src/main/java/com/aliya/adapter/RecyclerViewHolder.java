package com.aliya.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * {@link RecyclerView.ViewHolder}的拓展, 结合{@link RecyclerAdapter}使用
 *
 * @param <T> 数据的泛型
 * @author a_liYa
 * @date 16/10/19 09:52.
 */
public abstract class RecyclerViewHolder<T> extends RecyclerView.ViewHolder {

    public T mData;

    public RecyclerViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutRes) {
        this(inflate(layoutRes, parent, false));
    }

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setData(T data) {
        this.mData = data;
        bindView(mData);
        bindView();
    }

    public T getData() {
        return mData;
    }

    /**
     * bind data to view
     *
     * @param data .
     */
    public abstract void bindView(T data);

    /**
     * @deprecated Override use {@link #bindView(Object)}
     */
    @Deprecated
    public void bindView() {
    }

    /**
     * Inflate a new view hierarchy from the specified xml resource
     *
     * @param resource     ID for an XML layout
     * @param parent       the parent of
     * @param attachToRoot .
     * @return The root View of the inflated hierarchy.
     * @see LayoutInflater#inflate(int, ViewGroup, boolean)
     */
    protected static View inflate(@LayoutRes int resource, ViewGroup parent, boolean attachToRoot) {
        return LayoutInflater.from(parent.getContext()).inflate(resource, parent, attachToRoot);
    }

}
