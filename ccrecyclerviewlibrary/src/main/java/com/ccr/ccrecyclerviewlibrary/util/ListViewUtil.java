package com.ccr.ccrecyclerviewlibrary.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewUtil {
    /**
     * @param @param listView 设定文件
     * @return void 返回类型
     * @throws
     * @Title: setListViewHeightBasedOnChildren
     * @Description: 在ScroolView中需要重新设置ListView的高度
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        int len = listAdapter.getCount();
        for (int i = 0; i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            // LogUtil.i("msg", listItem.getMeasuredHeight() + "");
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // LogUtil.i("msg", params.height + "");
        listView.setLayoutParams(params);
    }

}
