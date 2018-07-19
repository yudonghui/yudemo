package com.ydh.yudemo.draggridview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ydh.yudemo.R;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Android on 2018/3/9.
 */

public class LotteryFormAdapter extends DragGridAdapter<DragData> {
    Map<String, Integer> mMap = new HashMap<>();
    int week_index;

    public LotteryFormAdapter(List<DragData> mHomeLotteryList) {
        super(mHomeLotteryList);
        mMap.put("0", R.drawable.jczq);
        mMap.put("1", R.drawable.jclq);
        mMap.put("2", R.drawable.jczqd);
        mMap.put("3", R.drawable.jclqd);
        mMap.put("4", R.drawable.dlt);
        mMap.put("5", R.drawable.sfc);
        mMap.put("6", R.drawable.rj);
        mMap.put("7", R.drawable.pls);
        mMap.put("8", R.drawable.plw);
        mMap.put("9", R.drawable.qxc);
        mMap.put("10", R.drawable.qlc);
        mMap.put("11", R.drawable.ssq);
        mMap.put("12", R.drawable.syydj);
        mMap.put("13", R.drawable.fcsd);
        mMap.put("14", R.drawable.jxks);
        mMap.put("15", R.drawable.cqss);
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            week_index = 7;
        } else {
            week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        }
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        List<DragData> list = getList();
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.drag_item, null);
        TextView mTitle = (TextView) convertView.findViewById(R.id.text_title);
        TextView mRule = (TextView) convertView.findViewById(R.id.text_rule);
        ImageView mImageView = (ImageView) convertView.findViewById(R.id.image);

        DragData dataBean = list.get(position);
        String lotCode = dataBean.getLotCode();
        String lotName = dataBean.getLotName();
        String description = dataBean.getDescription();

        mTitle.setText(lotName);
        mRule.setText(description);
        mImageView.setImageResource(mMap.get(lotCode));
        return convertView;
    }
}
