package com.ydh.yudemo.smartrefreshlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ydh.yudemo.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SmartRefreshLayoutActivity extends AppCompatActivity {
    private SmartRefreshLayout mRefresh;
    private ListView mListView;
    private ArrayList<String> mDataList = new ArrayList<>();
    private boolean isFirstEnter=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_refresh_layout);
        initView();
        addData();
        addListener();
        if (isFirstEnter){
            isFirstEnter=false;
            mRefresh.autoRefresh();
        }
    }

    private void initView() {
        mRefresh = (SmartRefreshLayout) findViewById(R.id.refresh);
        mListView = (ListView) findViewById(R.id.listView);
    }

    private void addData() {
        for (int i = 0; i < 20; i++) {
            mDataList.add("嘿嘿" + i);
        }
        SmartAdapter mAdapter = new SmartAdapter(mDataList, this);
        mListView.setAdapter(mAdapter);
    }

    private void addListener() {
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mRefresh.finishRefresh();
                    }
                },3000);
            }
        });
        mRefresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mRefresh.finishLoadmore();
                    }
                },3000);
            }
        });

    }
}
