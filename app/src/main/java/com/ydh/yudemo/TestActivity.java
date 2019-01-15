package com.ydh.yudemo;

import android.Manifest;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Rational;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private Context mContext;
    NotificationManager mNotiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mContext = this;
        mNotiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        initView();
        addListener();
        addData();

    }

    private void addData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void enterPicInPic() {
        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
        // 设置宽高比例值，第一个参数表示分子，第二个参数表示分母
        // 下面的10/5=2，表示画中画窗口的宽度是高度的两倍
        Rational aspectRatio = new Rational(10, 5);
        // 设置画中画窗口的宽高比例
        builder.setAspectRatio(aspectRatio);
        // 进入画中画模式，注意enterPictureInPictureMode是Android8.0之后新增的方法
        enterPictureInPictureMode(builder.build());
    }

    Scroller mScroller;
    GestureDetector gestureDetector;
    private String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    private void initView() {
        final TextView mTextView = findViewById(R.id.textView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(TestActivity.this, permissions, 1111);
                } else {
                    try {
                        //  FileUtils.writeTxtToFile("中国人民真争气", "test.txt");
                        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/hall.apk");
                        //安装apk
                        Intent intent = new Intent();
                        //执行动作
                        intent.setAction(Intent.ACTION_VIEW);
                        Uri mUri;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            //添加这一句表示对目标应用临时授权该Uri所代表的文件
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            //通过FileProvider创建一个content类型的Uri
                            mUri = FileProvider.getUriForFile(mContext, "com.ydh.yudemo.FileProvider", file);
                        } else {
                            mUri = Uri.fromFile(file);
                        }
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                        //执行的数据类型
                        intent.setDataAndType(mUri, "application/vnd.android.package-archive");
                        mContext.startActivity(intent);
                    } catch (Exception e) {
                        Log.e("报错", e.toString());
                    }


                }
            }
        });

        int colors[] = {0xff255779, 0xff1e7492, 0xffa6c0cd};
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gradientDrawable.setStroke(10, Color.parseColor("#ff00ff00"));
        gradientDrawable.setCornerRadius(10);
        gradientDrawable.setGradientType(GradientDrawable.RECTANGLE);
        mTextView.setBackgroundDrawable(gradientDrawable);
       /* gestureDetector = new GestureDetector(mContext, GestureListener);
        mTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
        mTextView.setFocusable(true);
        mTextView.setClickable(true);
        mTextView.setLongClickable(true);*/

    }

    public void sendNotification() {
        int id = 1000;
        String channelId = "channel_first"; //这里必须设置chanenelId,要不然该通知在8.0手机上，不能正常显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannelId(channelId);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,
                111,
                new Intent(mContext, MatchActivity.class),
                PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, channelId);
        builder.setContentTitle("简单的通知的标题")
                .setContentText("这是通知内容")
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        mNotiManager.notify(id, builder.build());
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public void createChannelId(String channelId) {

        NotificationChannel chan = new NotificationChannel(channelId, "通知渠道2", NotificationManager.IMPORTANCE_DEFAULT);
        chan.setLightColor(Color.GREEN);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        mNotiManager.createNotificationChannel(chan);
    }

    class MyInterpolator implements TimeInterpolator {

        @Override
        public float getInterpolation(float input) {
            Log.e("值", input + "");
            return input;
        }
    }

    class IntEvaluator implements TypeEvaluator<Integer> {

        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            Log.e("值", fraction + "  " + startValue + "  " + endValue);
            return (int) (startValue + endValue * fraction);
        }
    }

    GestureDetector.OnGestureListener GestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.e("滑动的距离", "onDown");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.e("滑动的距离", "onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.e("滑动的距离", "onSingleTapUp");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e("滑动的距离", "onScroll" + e1.getAction() + " E2的：" + e2.getAction() + " distanceX" + distanceX + " distanceY" + distanceY);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.e("滑动的距离", "onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e("滑动的距离", "velocityX:" + velocityX + "  velocityY: " + velocityY);

            return true;
        }
    };

    public void button(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            showChannel1Notification(mContext);
        }

    }

    private int Notification_ID = 1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showChannel1Notification(Context context) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setContentTitle("")
                .setContentText("正在运行中")
                // .setChannelId(String.valueOf(Notification_ID))//该句适配android 8.0 版本
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(true)//设置是否是一个正在执行的通知
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT));
        mNotificationManager.notify(Notification_ID, builder.build());


    }

    private void addListener() {

    }

    class TestAdapter extends BaseAdapter {
        List<String> mList;

        public TestAdapter(List<String> mList) {
            this.mList = mList;
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
            //convertView = LayoutInflater.from(mContext).inflate(R.layout.item_test, parent, false);
            convertView = View.inflate(mContext, R.layout.item_test, null);
            LinearLayout mLl = (LinearLayout) convertView.findViewById(R.id.ll);
            TextView mTestView = (TextView) convertView.findViewById(R.id.textView);
            mTestView.setText(mList.get(position));
            ViewParent parent1 = mLl.getParent();
           /* ViewGroup.LayoutParams layoutParams;
            if (position % 2 == 0) {
                layoutParams = new ViewGroup.LayoutParams(DisplayUtil.dip2px(mContext, 300), DisplayUtil.dip2px(mContext, 50));
            } else {
                layoutParams = new ViewGroup.LayoutParams(DisplayUtil.dip2px(mContext, 200), DisplayUtil.dip2px(mContext, 30));
            }
            mLl.setLayoutParams(layoutParams);*/
            return convertView;
        }
    }
}
