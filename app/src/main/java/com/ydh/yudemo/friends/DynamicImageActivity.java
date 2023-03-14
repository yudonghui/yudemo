package com.ydh.yudemo.friends;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ydh.yudemo.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 用到的第三方jar包
 * compile 'com.zhy:base-rvadapter:3.0.3'
 * compile 'com.github.bumptech.glide:glide:3.7.0'
 *
 * */
public class DynamicImageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CommonAdapter<String> mAdapter;
    List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_image);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mList = Data.getImgs(9);

        initAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

    }

    private void initAdapter() {
        mAdapter = new CommonAdapter<String>(this, R.layout.item_dynamic_image, mList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                MultiImageView multiImage = holder.getView(R.id.multi_image);
                holder.setText(R.id.tv_title, (position + 1) + "张图");
                multiImage.setList(Data.getImgs(position + 1));
                final int pos = position;
                multiImage.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText((Activity) mContext, "position1:" + pos + " , position2:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

    }
}
