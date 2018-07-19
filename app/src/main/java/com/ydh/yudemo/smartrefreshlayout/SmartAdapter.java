package com.ydh.yudemo.smartrefreshlayout;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ydh.yudemo.R;

import java.util.ArrayList;

/**
 * Created by Android on 2018/3/21.
 */

public class SmartAdapter extends BaseAdapter {
    ArrayList<String> mDataList;
    Context mContext;

    public SmartAdapter(ArrayList<String> mDataList, Context mContext) {
        this.mDataList = mDataList;
        this.mContext = mContext;
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
            convertView = ((Activity) mContext).getLayoutInflater().inflate(R.layout.item_smart_refresh, parent, false);
            mViewHolder = new ViewHolder();
            mViewHolder.mTextView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mTextView.setText(mDataList.get(position));
        return convertView;
    }

    class ViewHolder {
        private TextView mTextView;
    }
}
