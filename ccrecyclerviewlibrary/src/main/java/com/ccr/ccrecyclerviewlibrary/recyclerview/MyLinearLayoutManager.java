package com.ccr.ccrecyclerviewlibrary.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ccr.ccrecyclerviewlibrary.util.LogUtil;


/**
 * 计算RecyclerView的实际高度
 * Created by Sunflower on 2015/9/6.
 */
public class MyLinearLayoutManager extends LinearLayoutManager {
    public MyLinearLayoutManager(Context context) {
        //默认方向是VERTICAL,即ListView
        super(context);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        if (canScrollHorizontally()) {
            return;
        }
        int height = 0;
        int childCount = getItemCount();
        for (int i = 0; i < childCount; i++) {
            View child = recycler.getViewForPosition(i);
            measureChild(child, widthSpec, heightSpec);
            int topMargin = Math.abs(getDecoratedTop(child));
            int bottomMargin = Math.abs(getDecoratedBottom(child));
            int measuredHeight = child.getMeasuredHeight() + topMargin + bottomMargin;
            height += measuredHeight;
        }
        height += getPaddingTop() + getPaddingBottom();
        LogUtil.i("msg", "height" + height);
        setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), height);
    }
}
