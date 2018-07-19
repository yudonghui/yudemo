package com.ydh.yudemo.reptile;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.R;

import java.util.ArrayList;
import java.util.List;

public class MatchsShowActivity extends BaseActivity implements View.OnClickListener {
    private ListView mListView;
    private EditText mMatch;
    private TextView mAll;
    private TextView mDanguan;
    private TextView mSearch;
    private DbManager dbManager;
    List<ReptilDb> mDataList = new ArrayList<>();
    List<ReptilDb> reptilDbs = new ArrayList<>();

    @Override
    public int getInflateId() {
        return R.layout.activity_matchs_show;
    }

    @Override
    public void initView() {
        mMatch = (EditText) findViewById(R.id.match);
        mAll = (TextView) findViewById(R.id.all);
        mDanguan = (TextView) findViewById(R.id.danguan);
        mSearch = (TextView) findViewById(R.id.search);
        mListView = (ListView) findViewById(R.id.listView);
        dbManager = new DbManager();
        reptilDbs.addAll(dbManager.queryAll());
    }

    @Override
    public void addListener() {
        mDanguan.setOnClickListener(this);
        mAll.setOnClickListener(this);
        mSearch.setOnClickListener(this);
    }

    @Override
    public void addData() {
        mDataList.clear();
        String trim = mMatch.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {//单关所有，或者赛事所有
            if (type == 0) {
                mDataList.addAll(reptilDbs);
            } else {
                for (int i = 0; i < reptilDbs.size(); i++) {
                    ReptilDb reptilDb = reptilDbs.get(i);
                    boolean isDg = reptilDb.getIsDg();
                    if (isDg)
                        mDataList.add(reptilDb);
                }
            }
        } else {
            if (type == 0) {
                for (int i = 0; i < reptilDbs.size(); i++) {
                    ReptilDb reptilDb = reptilDbs.get(i);
                    String match = reptilDb.getMatch();
                    if (trim.equals(match))
                        mDataList.add(reptilDb);
                }
            } else {
                for (int i = 0; i < reptilDbs.size(); i++) {
                    ReptilDb reptilDb = reptilDbs.get(i);
                    boolean isDg = reptilDb.getIsDg();
                    String match = reptilDb.getMatch();
                    if (isDg && trim.equals(match))
                        mDataList.add(reptilDb);
                }
            }
        }
        ReptileAdapter reptileAdapter = new ReptileAdapter();
        mListView.setAdapter(reptileAdapter);
    }

    private int type = 0;//0,搜索所有 1，搜索单关

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.danguan:
                mAll.setTextColor(getResources().getColor(R.color.blackColor));
                mAll.setBackgroundColor(getResources().getColor(R.color.white));
                mDanguan.setTextColor(getResources().getColor(R.color.white));
                mDanguan.setBackgroundColor(getResources().getColor(R.color.red_txt));
                type = 1;
                break;
            case R.id.all:
                mAll.setTextColor(getResources().getColor(R.color.white));
                mAll.setBackgroundColor(getResources().getColor(R.color.red_txt));
                mDanguan.setTextColor(getResources().getColor(R.color.blackColor));
                mDanguan.setBackgroundColor(getResources().getColor(R.color.white));
                type = 0;
                break;
            case R.id.search:
                addData();
                break;
        }
    }

    class ReptileAdapter extends BaseAdapter {


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
                convertView = View.inflate(mContext, R.layout.item_reptile, null);
                mViewHolder = new ViewHolder();
                mViewHolder.mLl = (LinearLayout) convertView.findViewById(R.id.ll);
                mViewHolder.mOne = (TextView) convertView.findViewById(R.id.one);
                mViewHolder.mTwo = (TextView) convertView.findViewById(R.id.two);
                mViewHolder.mThree = (TextView) convertView.findViewById(R.id.three);
                mViewHolder.mFour = (TextView) convertView.findViewById(R.id.four);
                mViewHolder.mFive = (TextView) convertView.findViewById(R.id.five);
                mViewHolder.mSix = (TextView) convertView.findViewById(R.id.six);
                mViewHolder.mSeven = (TextView) convertView.findViewById(R.id.seven);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }
            ReptilDb reptilDb = mDataList.get(position);
            String issue = reptilDb.getIssue();
            String match = reptilDb.getMatch();
            String home = reptilDb.getHome();
            String guest = reptilDb.getGuest();
            String odds3 = reptilDb.getOdds3();
            String odds1 = reptilDb.getOdds1();
            String odds0 = reptilDb.getOdds0();
            String halfScore = reptilDb.getHalfScore();
            String score = reptilDb.getScore();
            boolean isDg = reptilDb.getIsDg();
            if (!TextUtils.isEmpty(score) && score.contains(":")) {
                String[] split = score.split("\\:");
                int zhu = Integer.parseInt(split[0]);
                int ke = Integer.parseInt(split[1]);
                double o0 = Double.parseDouble("--".equals(odds0) ? "0" : odds0);
                double o3 = Double.parseDouble("--".equals(odds3) ? "0" : odds3);
                if (zhu > ke) {
                    if (o3 > o0) {//从赔率上看是客队强。客队赔率低。冷
                        mViewHolder.mLl.setBackgroundColor(getResources().getColor(R.color.orange_bg));
                    } else if (o3 < o0) {//主队强，胜正常
                        mViewHolder.mLl.setBackgroundColor(getResources().getColor(R.color.red_txt));
                    } else {//赔率主客相等。
                        mViewHolder.mLl.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                } else if (zhu < ke) {
                    if (o3 > o0) {//从赔率上看是客队强。客队赔率低。客队胜正常
                        mViewHolder.mLl.setBackgroundColor(getResources().getColor(R.color.red_txt));
                    } else if (o3 < o0) {//主队强，冷
                        mViewHolder.mLl.setBackgroundColor(getResources().getColor(R.color.orange_bg));
                    } else {//赔率主客相等。
                        mViewHolder.mLl.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                } else {//平局
                    mViewHolder.mLl.setBackgroundColor(getResources().getColor(R.color.blue_bg));
                }
            }
            mViewHolder.mOne.setText(TextUtils.isEmpty(issue) ? "--" : issue);
            mViewHolder.mTwo.setText(TextUtils.isEmpty(match) ? "--" : match);
            mViewHolder.mThree.setText(home + "VS" + guest);
            mViewHolder.mFour.setText(TextUtils.isEmpty(odds3) ? "--" : odds3);
            mViewHolder.mFive.setText(TextUtils.isEmpty(odds1) ? "--" : odds1);
            mViewHolder.mSix.setText(TextUtils.isEmpty(odds0) ? "--" : odds0);
            mViewHolder.mSeven.setText((TextUtils.isEmpty(halfScore) ? "--" : halfScore) + "\n" + (TextUtils.isEmpty(score) ? "--" : score));


            return convertView;
        }
    }

    class ViewHolder {
        private LinearLayout mLl;
        private TextView mOne;
        private TextView mTwo;
        private TextView mThree;
        private TextView mFour;
        private TextView mFive;
        private TextView mSix;
        private TextView mSeven;
    }
}
