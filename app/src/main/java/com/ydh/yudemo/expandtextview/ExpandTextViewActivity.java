package com.ydh.yudemo.expandtextview;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandTextViewActivity extends BaseActivity {


    @Override
    public int getInflateId() {
        return R.layout.activity_expand_text_view;
    }

    @Override
    public void initView() {
        String test = getString(R.string.test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        data.add(test);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerAdapter());
    }

    @Override
    public void addListener() {

    }

    @Override
    public void addData() {

    }

    private List<String> data = new ArrayList<>();

    private class RecyclerAdapter extends RecyclerView.Adapter<Holder> {


        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(View.inflate(mContext, R.layout.item_extext, null));
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            holder.tv.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    class Holder extends RecyclerView.ViewHolder {
        ExpandTextView tv;

        public Holder(View itemView) {
            super(itemView);
            tv = (ExpandTextView) itemView.findViewById(R.id.tv_expand);
        }
    }
}
