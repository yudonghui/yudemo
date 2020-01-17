package com.ydh.yudemo.mpandroidchart;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.ydh.yudemo.R;
import com.ydh.yudemo.utils.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MPAndroidChartActivity extends AppCompatActivity {

    @BindView(R.id.mLineChar)
    LineChart mLineChar;
    @BindView(R.id.mBarChart)
    BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpandroid_chart);
        ButterKnife.bind(this);
        initView();
        initData();
        initBarData();
        //默认动画
        mLineChar.animateX(2000);
        //刷新
        mLineChar.invalidate();
        // 得到这个文字
        Legend l = mLineChar.getLegend();
        // 修改文字 ...
        l.setForm(Legend.LegendForm.LINE);
    }


    private void initView() {
        //设置手势滑动事件
        mLineChar.setOnChartGestureListener(OnchartgestListener);
        //设置数值选择监听
        mLineChar.setOnChartValueSelectedListener(OnChartValueSelecteListener);
        //后台绘制
        mLineChar.setDrawGridBackground(false);
        //设置描述文本
        mLineChar.getDescription().setEnabled(false);
        //设置支持触控手势
        mLineChar.setTouchEnabled(true);
        //设置缩放
        mLineChar.setDragEnabled(true);
        //设置推动
        mLineChar.setScaleEnabled(true);
        //如果禁用,扩展可以在x轴和y轴分别完成
        mLineChar.setPinchZoom(true);
    }

    private void initData() {
        initLineData();
        initBarData();
    }

    private void initLineData() {
        ArrayList<Entry> values = new ArrayList<Entry>();
        values.add(new Entry(5, 50));
        values.add(new Entry(10, 66));
        values.add(new Entry(15, 120));
        values.add(new Entry(20, 50));
        values.add(new Entry(35, 20));
        values.add(new Entry(40, 110));
        values.add(new Entry(45, 30));
        values.add(new Entry(50, 130));
        values.add(new Entry(80, 30));
        values.add(new Entry(100, 120));
        values.add(new Entry(120, 50));
        values.add(new Entry(130, 130));
        values.add(new Entry(140, 80));
        values.add(new Entry(150, 100));
        values.add(new Entry(160, 30));
        values.add(new Entry(170, 110));
        values.add(new Entry(180, 40));
        values.add(new Entry(190, 135));
        if (mLineChar.getData() != null && mLineChar.getData().getDataSetCount() > 0) {
            LineDataSet set1 = (LineDataSet) mLineChar.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mLineChar.getData().notifyDataChanged();
            mLineChar.notifyDataSetChanged();
        } else {
            // 创建一个数据集,并给它一个类型
            LineDataSet set1 = new LineDataSet(values, "年度总结报告");

            // 在这里设置线
            set1.enableDashedLine(10f, 5f, 0f);//虚线 lineLength线长，spaceLength空白长
            set1.enableDashedHighlightLine(30f, 5f, 0f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);//连接线的宽度
            set1.setCircleRadius(3f);//节点处的圆点儿的大小
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);//是否填充颜色
            set1.setFormLineWidth(1f);//表格下面的label前面的线的宽度
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);
            if (Utils.getSDKInt() >= 18) {
                // 填充背景只支持18以上
                //Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_launcher);
                //set1.setFillDrawable(drawable);
                set1.setFillColor(Color.YELLOW);
            } else {
                set1.setFillColor(Color.BLACK);
            }
            //LineDataSet.Mode.LINEAR 直线，LineDataSet.Mode.CUBIC_BEZIER贝塞尔曲线
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            //添加数据集
            dataSets.add(set1);

            //创建一个数据集的数据对象
            LineData data = new LineData(dataSets);

            //设置数据
            mLineChar.setData(data);
        }
    }

    private void initBarData() {
        ArrayList<BarEntry> values = new ArrayList<BarEntry>();
        values.add(new BarEntry(5, 48));
        values.add(new BarEntry(10, 30));
        values.add(new BarEntry(15, 35));
        values.add(new BarEntry(20, 45));
        values.add(new BarEntry(35, 10));
        values.add(new BarEntry(40, 20));
        values.add(new BarEntry(45, 26));
        values.add(new BarEntry(50, 45));
        values.add(new BarEntry(80, 10));
        values.add(new BarEntry(100, 15));
        //设置表格上的点，被点击的时候，的回调函数
        mBarChart.setOnChartValueSelectedListener(OnChartValueSelecteListener);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);
        mBarChart.getDescription().setEnabled(false);
        // 如果60多个条目显示在图表,drawn没有值
        mBarChart.setMaxVisibleValueCount(60);
        // 扩展现在只能分别在x轴和y轴
        mBarChart.setPinchZoom(false);
        //是否显示表格颜色
        mBarChart.setDrawGridBackground(false);
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        // 只有1天的时间间隔
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(10);//x轴方向  分割的个数
        xAxis.setAxisMaximum(105f);//x轴方向 最大值
        xAxis.setAxisMinimum(0f);//x轴方向 最小值
        xAxis.setValueFormatter(null);

        YAxis leftAxis = mBarChart.getAxisLeft();//左边的Y轴
        leftAxis.setLabelCount(10, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        //这个替换setStartAtZero(true)
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(100f);
        YAxis rightAxis = mBarChart.getAxisRight();//右边的Y轴
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(10, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setAxisMaximum(100f);
        float start = 1f;
        BarDataSet set1;
        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "2017年工资涨幅");
            //设置有四种颜色
            set1.setColors(ColorTemplate.MATERIAL_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(2f);//条目的宽度
            //设置数据
            mBarChart.setData(data);
        }
    }

    OnChartGestureListener OnchartgestListener = new OnChartGestureListener() {
        //触摸开始
        @Override
        public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
            LogUtils.error("手势：onChartGestureStart");
        }

        //手指抬起 触摸结束
        @Override
        public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
            LogUtils.error("手势：onChartGestureEnd");
        }

        //长按手势
        @Override
        public void onChartLongPressed(MotionEvent me) {
            LogUtils.error("手势：onChartLongPressed");
        }

        @Override
        public void onChartDoubleTapped(MotionEvent me) {
            LogUtils.error("手势：onChartDoubleTapped");
        }

        @Override
        public void onChartSingleTapped(MotionEvent me) {
            LogUtils.error("手势：onChartSingleTapped");
        }

        //惯性滑动
        @Override
        public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
            LogUtils.error("手势：onChartFling");
        }

        //缩小放大手势
        @Override
        public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
            LogUtils.error("手势：onChartScale");
        }

        @Override
        public void onChartTranslate(MotionEvent me, float dX, float dY) {
            LogUtils.error("手势：onChartTranslate");
        }
    };
    OnChartValueSelectedListener OnChartValueSelecteListener = new OnChartValueSelectedListener() {
        //选择的节点的位置
        @Override
        public void onValueSelected(Entry e, Highlight h) {
            LogUtils.error("手势：onValueSelected  " + e.getX() + "  :  " + e.getY());
        }

        //没有选择的节点
        @Override
        public void onNothingSelected() {
            LogUtils.error("手势：onNothingSelected");
        }
    };
}
