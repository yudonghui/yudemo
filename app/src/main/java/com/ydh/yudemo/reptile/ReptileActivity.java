package com.ydh.yudemo.reptile;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.LoadingDialogUtils;
import com.ydh.yudemo.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class ReptileActivity extends BaseActivity {
    private TextView mClear;
    private EditText mStartTime;
    private EditText mEndTime;
    private EditText mPage;
    private TextView mSearch;
    private TextView mSkip;
    private String url = "http://info.sporttery.cn/football/match_result.php";
    private DbManager dbManager;

    @Override
    public int getInflateId() {
        return R.layout.activity_reptile;
    }

    @Override
    public void initView() {
        mClear = (TextView) findViewById(R.id.clear);
        mStartTime = (EditText) findViewById(R.id.startTime);
        mEndTime = (EditText) findViewById(R.id.endTime);
        mPage = (EditText) findViewById(R.id.page);
        mSearch = (TextView) findViewById(R.id.search);
        mSkip = (TextView) findViewById(R.id.skip);
        dbManager = new DbManager();
    }
    private LoadingDialogUtils loadingDialogUtils;
    @Override
    public void addListener() {
        mSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadingDialogUtils = new LoadingDialogUtils(mContext);
                web();
            }
        });
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.deleteAll();
                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        mSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MatchsShowActivity.class));
            }
        });
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                Log.e("请求次数", page + "");
                page++;
                if (page <= totalPage) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    web();
                }else {
                    loadingDialogUtils.setDimiss();
                    Toast.makeText(mContext,"添加成功",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    private int totalPage = 1;
    private int page = 1;

    public void web() {
        final String start_date = mStartTime.getText().toString();
        final String end_date = mEndTime.getText().toString();
        // page = Integer.parseInt(mPage.getText().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {

                String aaa = url + "?page=" + page + "&start_date=" + start_date + "&end_date=" + end_date;
                try {
                    Document doc = Jsoup.connect(aaa).get();
                    Elements match_list = doc.getElementsByClass("match_list");
                    if (page==1) {
                        Elements elementsByClass = doc.getElementsByClass("m-left");
                        Elements elementsByClass1 = elementsByClass.get(0).getElementsByClass("u-org");
                        String text = elementsByClass1.get(0).text();
                        totalPage = (int) Math.ceil(Double.parseDouble(text) / 20.0);
                    }
                    Element element = match_list.get(1);
                    Elements table = element.getElementsByTag("table");
                    Elements tr = table.get(0).getElementsByTag("tr");
                    int length = tr.size() - 2;
                    for (int i = 0; i < length; i++) {
                        ReptilDb reptilDb = new ReptilDb();
                        Element element1 = tr.get(i);
                        Elements td = element1.getElementsByTag("td");
                        String date = td.get(0).text();
                        String week = td.get(1).text();
                        Element team = td.get(3);
                        String halfScore = td.get(4).text();
                        String score = td.get(5).text();
                        String odds3 = td.get(6).text();//胜
                        String odds1 = td.get(7).text();//平
                        String odds0 = td.get(8).text();//负
                        reptilDb.setDate(date);
                        reptilDb.setWeek(week);
                        reptilDb.setIssue(date + week);
                        reptilDb.setHalfScore(halfScore);
                        reptilDb.setScore(score);
                        reptilDb.setOdds3(odds3);
                        reptilDb.setOdds1(odds1);
                        reptilDb.setOdds0(odds0);
                        Elements aClass = element1.getElementsByClass("write");
                        if (aClass.size() == 1) {
                            String text = aClass.get(0).text();
                            reptilDb.setMatch(text);
                        }
                        Elements zhu = element1.getElementsByClass("zhu");
                        String home = zhu.get(0).text();
                        reptilDb.setHome(home);

                        Elements ke = element1.getElementsByClass("ke");
                        String guest = ke.get(0).text();
                        reptilDb.setGuest(guest);

                        Elements styles = team.getElementsByAttribute("style");
                        if (styles.size() == 3) {
                            reptilDb.setIsDg(true);
                        } else reptilDb.setIsDg(false);
                        Log.e("赛事" + i, reptilDb.toString());
                        dbManager.inserts(reptilDb);
                    }
                    mHandler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void addData() {

    }


}
