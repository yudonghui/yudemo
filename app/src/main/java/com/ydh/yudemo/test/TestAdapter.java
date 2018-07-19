package com.ydh.yudemo.test;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ydh.yudemo.R;

import java.util.List;

/**
 * Created by Android on 2018/4/12.
 */

public class TestAdapter extends BaseAdapter {
    List<String> mDataList;

    public TestAdapter(List<String> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_test, null);
            mViewHolder = new ViewHolder();
            mViewHolder.mTextView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(mViewHolder);
        } else mViewHolder = (ViewHolder) convertView.getTag();
        String s = mDataList.get(position);
        mViewHolder.mTextView.setText(s);
        return convertView;
    }

    class ViewHolder {
        private TextView mTextView;
    }
}
