package com.ydh.yudemo.common.popup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListPopupWindow;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ydh.yudemo.DisplayUtil;
import com.ydh.yudemo.R;
import com.ydh.yudemo.common.CommonBean;
import com.ydh.yudemo.common.fanxing.FanXingActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopupActivity extends AppCompatActivity {

    @BindView(R.id.tv_popup_window)
    TextView tvPopupWindow;
    @BindView(R.id.tv_popup_window_lv)
    TextView tvPopupWindowLv;
    @BindView(R.id.tv_popup_window_list)
    TextView tvPopupWindowList;
    private PopupWindow popupWindow;
    private PopupWindow lvPopupWindow;
    private ListPopupWindow listPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        ButterKnife.bind(this);
        initPopupWindow();
        initLvPopupWindow();
        initListPopupWindow();
    }

    @OnClick({R.id.tv_popup_window, R.id.tv_popup_menu, R.id.tv_popup_window_lv, R.id.tv_popup_window_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_popup_window:
                popupWindow.showAsDropDown(tvPopupWindow, 0, 0);//偏移量  负值是向左或者向上。正值是向右或者向下
                break;
            case R.id.tv_popup_menu:
                popupMenu(view);
                break;
            case R.id.tv_popup_window_lv:
                lvPopupWindow.showAsDropDown(tvPopupWindowLv, 0, 0);//偏移量  负值是向左或者向上。正值是向右或者向下
                break;
            case R.id.tv_popup_window_list:
                listPopupWindow.show();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void popupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setGravity(Gravity.RIGHT);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ToastUtils.showShort(item.getTitle());
                return false;
            }
        });

    }

    /**
     * 初始化弹出框
     * 点击外部弹出框消失需要下面三行设置：
     * popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
     * popupWindow.setOutsideTouchable(true);
     * popupWindow.setTouchable(true);
     */
    private void initPopupWindow() {
        View popupView = getLayoutInflater().inflate(R.layout.popup_window_text, null);
        TextView mTvOne = popupView.findViewById(R.id.tv_one);
        TextView mTvTwo = popupView.findViewById(R.id.tv_two);
        TextView mTvThree = popupView.findViewById(R.id.tv_three);
        TextView mTvFour = popupView.findViewById(R.id.tv_four);
        mTvOne.setOnClickListener(popupClick);
        mTvTwo.setOnClickListener(popupClick);
        mTvThree.setOnClickListener(popupClick);
        mTvFour.setOnClickListener(popupClick);
        popupWindow = new PopupWindow(popupView, DisplayUtil.dip2px(this, 120), DisplayUtil.dip2px(this, 200));
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
    }

    private void initLvPopupWindow() {
        View popupView = getLayoutInflater().inflate(R.layout.popup_window_list, null);
        RecyclerView mRv = popupView.findViewById(R.id.rv_popup);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(linearLayoutManager);
        ArrayList<CommonBean> commonBeans = new ArrayList<>();
        commonBeans.add(new CommonBean("1", "第一条数据"));
        commonBeans.add(new CommonBean("2", "第二条数据"));
        commonBeans.add(new CommonBean("3", "第三条数据"));
        commonBeans.add(new CommonBean("4", "第四条数据"));
        commonBeans.add(new CommonBean("5", "第五条数据"));
        commonBeans.add(new CommonBean("6", "第六条数据"));
        commonBeans.add(new CommonBean("7", "第七条数据"));
        mRv.setAdapter(new CommonAdapter<CommonBean>(this, R.layout.item_popup_window, commonBeans) {

            @Override
            protected void convert(ViewHolder holder, final CommonBean commonBean, int position) {
                holder.setText(R.id.tv_content, commonBean.getType() + commonBean.getContent());
                holder.setOnClickListener(R.id.tv_content, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(PopupActivity.this, "点击了" + commonBean.getContent(), Toast.LENGTH_SHORT).show();
                        lvPopupWindow.dismiss();
                    }
                });
            }
        });
        lvPopupWindow = new PopupWindow(popupView, DisplayUtil.dip2px(this, 120), ViewGroup.LayoutParams.WRAP_CONTENT);
        lvPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        lvPopupWindow.setOutsideTouchable(true);
        lvPopupWindow.setTouchable(true);
    }

    private void initListPopupWindow() {
        final ArrayList<CommonBean> commonBeans = new ArrayList<>();
        commonBeans.add(new CommonBean("1", "第一条数据"));
        commonBeans.add(new CommonBean("2", "第二条数据"));
        commonBeans.add(new CommonBean("3", "第三条数据"));
        commonBeans.add(new CommonBean("4", "第四条数据"));
        commonBeans.add(new CommonBean("5", "第五条数据"));
        commonBeans.add(new CommonBean("6", "第六条数据"));
        commonBeans.add(new CommonBean("7", "第七条数据"));
        listPopupWindow = new ListPopupWindow(this);
        listPopupWindow.setAdapter(new ListPopupAdapter(commonBeans, this));
        listPopupWindow.setAnchorView(tvPopupWindowList);//设置参考控件
        listPopupWindow.setHorizontalOffset(DisplayUtil.dip2px(this, 0));//相对锚点偏移值，正值表示向右偏移
        listPopupWindow.setVerticalOffset(DisplayUtil.dip2px(this, 0));//相对锚点偏移值，正值表示向下偏移
        listPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PopupActivity.this, "点击了" + commonBeans.get(position).getContent(), Toast.LENGTH_SHORT).show();
                listPopupWindow.dismiss();
            }
        });

    }

    View.OnClickListener popupClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            popupWindow.dismiss();
            switch (view.getId()) {
                case R.id.tv_one:
                    Toast.makeText(PopupActivity.this, "点击了one", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_two:
                    Toast.makeText(PopupActivity.this, "点击了two", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_three:
                    Toast.makeText(PopupActivity.this, "点击了three", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_four:
                    Toast.makeText(PopupActivity.this, "点击了four", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}
