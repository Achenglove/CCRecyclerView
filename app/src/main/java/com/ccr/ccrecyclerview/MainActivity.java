package com.ccr.ccrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ccr.ccrecyclerviewlibrary.view.refreshlayout.RefreshLayout;

public class MainActivity extends AppCompatActivity {
    protected RefreshLayout refreshLayout;
    protected int pageNo=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 设置上拉和下拉控件，主要用于分页功能
     *
     * @param refreshLayout
     */
    protected void setRefreshLayout(RefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
        refreshLayout.setColorSchemeResources(R.color.colorAccent);
        refreshLayout.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onPullDownToRefresh() {
                onRefresh();
            }

            @Override
            public void onPullUpToRefresh() {

                onLoadMore();

            }
        });
    }

    private void onLoadMore() {

    }

    private void onRefresh() {
    }

}
