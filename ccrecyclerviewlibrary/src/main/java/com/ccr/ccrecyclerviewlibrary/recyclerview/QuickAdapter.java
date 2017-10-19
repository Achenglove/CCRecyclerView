package com.ccr.ccrecyclerviewlibrary.recyclerview;

import android.content.Context;

import java.util.List;

/**
 * Created by Sunflower on 2015/8/17.
 */
public abstract class QuickAdapter<T> extends BaseQuickAdapter<T, BaseAdapterHelper> {
    public QuickAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public QuickAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }



}
