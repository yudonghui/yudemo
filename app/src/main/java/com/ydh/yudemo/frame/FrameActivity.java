package com.ydh.yudemo.frame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ydh.yudemo.R;
import com.ydh.yudemo.frame.database.DataBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrameActivity extends AppCompatActivity {

    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.rv_frame)
    RecyclerView rvFrame;
    private ArrayList<FrameEntity> datas;
    private FrameAdapter frameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        ButterKnife.bind(this);
        datas = new ArrayList<>();
        frameAdapter = new FrameAdapter(this, datas, R.layout.item_frame);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvFrame.setLayoutManager(gridLayoutManager);
        rvFrame.setAdapter(frameAdapter);
        initData();
    }

    @OnClick(R.id.iv_return)
    public void onViewClicked() {
        finish();
    }

    private void initData() {
        datas.clear();
        datas.add(new FrameEntity("数据库", 1));
        datas.add(new FrameEntity("图片加载", 2));
        frameAdapter.notifyDataSetChanged();
    }


    class FrameAdapter extends BaseRvAdapter<FrameEntity> {

        public FrameAdapter(Context context, List<FrameEntity> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        protected void bindData(BaseViewHolder holder, final FrameEntity data, int position) {
            holder.setText(R.id.tv_content_frame, data.getContent());
            holder.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (data.getType()) {
                        case 1://数据库
                            startActivity(new Intent(FrameActivity.this, DataBaseActivity.class));
                            break;
                        case 2://图片加载
                            startActivity(new Intent(FrameActivity.this, DataBaseActivity.class));
                            break;
                    }
                }
            });
        }
    }

    class FrameEntity {
        private String content;
        private int type;

        public FrameEntity(String content, int type) {
            this.content = content;
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

}
