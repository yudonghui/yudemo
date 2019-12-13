package com.ydh.yudemo.common.popup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ydh.yudemo.R;
import com.ydh.yudemo.common.CommonBean;

import java.util.List;

public class ListPopupAdapter extends BaseAdapter {
    List<CommonBean> mList;
    private Context mContext;

    public ListPopupAdapter(List<CommonBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_popup_window, null);
            mViewHolder = new ViewHolder();
            mViewHolder.mTvContent = convertView.findViewById(R.id.tv_content);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        CommonBean mTvContent = mList.get(position);
        mViewHolder.mTvContent.setText(mTvContent.getType() + mTvContent.getContent());
        return convertView;
    }

    class ViewHolder {
        TextView mTvContent;
    }
}
