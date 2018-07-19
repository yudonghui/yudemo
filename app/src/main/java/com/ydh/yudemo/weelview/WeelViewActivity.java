package com.ydh.yudemo.weelview;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.ydh.yudemo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeelViewActivity extends AppCompatActivity {
    private Context mContext;
    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private TextView mTextView5;
    private TextView mTextView6;
    private TextView mTextView7;
    private TextView mTextView8;
    private String dateStr, mouth1, day1;
    private Calendar calendar;
    private Calendar calendar2;
    private LinearLayout mFl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weel_view);
        mContext = this;
        calendar = Calendar.getInstance();
        calendar2 = Calendar.getInstance();
        initView();
        addData();
        addListener();
    }

    private void initView() {
        mFl = (LinearLayout) findViewById(R.id.linearLayout);
        mTextView1 = (TextView) findViewById(R.id.textView1);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mTextView3 = (TextView) findViewById(R.id.textView3);
        mTextView4 = (TextView) findViewById(R.id.textView4);
        mTextView5 = (TextView) findViewById(R.id.textView5);
    }

    private void addData() {
        options1Items.clear();
        options1Items.add("托儿索");
        options1Items.add("儿童劫");
        options1Items.add("小学生之手");
        options1Items.add("德玛西亚大保健");
        options1Items.add("面对疾风吧");
        options1Items.add("天王盖地虎");
        options1Items.add("我发一米五");
        options1Items.add("爆刘继芬");
    }

    private void addListener() {
        mTextView1.setOnClickListener(TextView1Listener);
        mTextView2.setOnClickListener(TextView2Listener);
        mTextView3.setOnClickListener(TextView3Listener);
        mTextView4.setOnClickListener(TextView4Listener);
        mTextView5.setOnClickListener(TextView5Listener);
    }

    View.OnClickListener TextView1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePickerDialog dialog = new
                    DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int
                        dayOfMonth) {

                    if (monthOfYear <= 9) {
                        mouth1 = "0" + (monthOfYear + 1);
                    } else {
                        mouth1 = String.valueOf(monthOfYear + 1);
                    }
                    if (dayOfMonth <= 9) {
                        day1 = "0" + dayOfMonth;
                    } else {
                        day1 = String.valueOf(dayOfMonth);
                    }
                    dateStr = String.valueOf(year) + "-" + mouth1 + "-" + day1;
                    mTextView1.setText(dateStr);


                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
    };
    View.OnClickListener TextView2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TimePickerDialog dialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mTextView2.setText(String.valueOf(hourOfDay + ":" + minute));
                }
            }, calendar2.get(Calendar.HOUR), calendar2.get(Calendar.MINUTE), false);
            dialog.show();
        }
    };
    View.OnClickListener TextView3Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //compile 'com.contrarywind:Android-PickerView:3.2.4'
            TimePickerView pvTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date2, View v) {//选中事件回调
                    String time = getTime(date2);
                    mTextView3.setText(time);
                }
            })
                    .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                    .setCancelText("取消")//取消按钮文字
                    .setSubmitText("确定")//确认按钮文字
                    .setContentSize(20)//滚轮文字大小
                    .setTitleSize(20)//标题文字大小
//                        .setTitleText("请选择时间")//标题文字
                    .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                    .isCyclic(true)//是否循环滚动
                    .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                    .setTitleColor(Color.BLACK)//标题文字颜色
                    .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                    .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                        .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                        .setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR) + 20)//默认是1900-2100年
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间
//                        .setRangDate(startDate,endDate)//起始终止年月日设定
//                        .setLabel("年","月","日","时","分","秒")
                    .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .isDialog(true)//是否显示为对话框样式
                    .build();
            pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
            pvTime.show();
        }
    };
    private final List<String> options1Items = new ArrayList<>();
    View.OnClickListener TextView4Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    String s = options1Items.get(options1);
                    mTextView4.setText(s);

                }
            })
//                        .setSubmitText("确定")//确定按钮文字
//                        .setCancelText("取消")//取消按钮文字
//                        .setTitleText("城市选择")//标题
                    .setSubCalSize(20)//确定和取消文字大小
//                        .setTitleSize(20)//标题文字大小
//                        .setTitleColor(Color.BLACK)//标题文字颜色
                    .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                    .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                        .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
//                        .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
//                        .setContentTextSize(18)//滚轮文字大小
//                        .setTextColorCenter(Color.BLUE)//设置选中项的颜色
                    .setTextColorCenter(Color.BLACK)//设置选中项的颜色
//                        .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
//                        .setLinkage(false)//设置是否联动，默认true
//                        .setLabels("省", "市", "区")//设置选择的三级单位
//                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .setCyclic(false, false, false)//循环与否
//                        .setSelectOptions(1, 1, 1)  //设置默认选中项
//                        .setOutSideCancelable(false)//点击外部dismiss default true
//                        .isDialog(true)//是否显示为对话框样式
                    .build();
            pvOptions.setPicker(options1Items);
            pvOptions.show();
        }
    };
    private String selectTime;
    private ChangeDatePopwindow mChangeBirthDialog;
    View.OnClickListener TextView5Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(selectTime)) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                selectTime = sdf.format(date);
            }
            if (mChangeBirthDialog == null)
                mChangeBirthDialog = new ChangeDatePopwindow(mContext);
            mChangeBirthDialog.showAtLocation(mFl, Gravity.BOTTOM, 0, 0);
            String[] split = selectTime.split("\\-");
            mChangeBirthDialog.setDate(split[0], split[1], split[2]);
            mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {
                @Override
                public void onClick(String year, String month, String day) {
                    selectTime = dateByDate(year, month, day);
                    mTextView5.setText(selectTime);
                }
            });
        }
    };

    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    private String dateByDate(String year, String month, String day) {
        String yearStr = year.substring(0, year.length() - 1);
        String monthStr = month.substring(0, month.length() - 1);
        String dayStr = day.substring(0, day.length() - 1);

        if (Integer.parseInt(monthStr) < 10) {
            monthStr = "0" + monthStr;
        }
        if (Integer.parseInt(dayStr) < 10) {
            dayStr = "0" + dayStr;
        }
        return yearStr + "-" + monthStr + "-" + dayStr;
    }
}
