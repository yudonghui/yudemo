package com.ydh.yudemo.draggridview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.ydh.yudemo.R;

import java.util.ArrayList;
import java.util.List;

public class DragActivity extends AppCompatActivity {
    private List<DragData> mDataList = new ArrayList<>();
    private DragGridView mGridView;
    private LotteryFormAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        mGridView = (DragGridView) findViewById(R.id.gridView);
        for (int i = 0; i < 16; i++) {
            DragData dragData = new DragData();
            dragData.setDescription("第" + i + "个描述");
            dragData.setLotName("第" + i + "标题");
            dragData.setLotCode(i + "");
            mDataList.add(dragData);
        }
        mAdapter = new LotteryFormAdapter(mDataList);
        mGridView.setAdapter(mAdapter);
        mAdapter.setOnChangeListener(GridViewChangListener);
    }
    DragGridAdapter.OnChanageListener GridViewChangListener = new DragGridAdapter.OnChanageListener() {

        @Override
        public void onChange(int from, int to) {
            Log.e("位置：","从"+from+"到"+to);
        }
    };
    //当返回之后再把集合里面的数据顺序记下来。存到本地，下次进来的时候对比一下显示出来。

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
