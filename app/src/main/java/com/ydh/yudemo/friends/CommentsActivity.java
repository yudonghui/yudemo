package com.ydh.yudemo.friends;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ydh.yudemo.R;

public class CommentsActivity extends AppCompatActivity {
    private CommentsView commentView;
    private LikesView likeView;
    private MyThread thread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        commentView = (CommentsView) findViewById(R.id.commentView);
        likeView = (LikesView) findViewById(R.id.likeView);

        likeView.setList(Data.getLikes());
        likeView.setListener(new LikesView.onItemClickListener() {
            @Override
            public void onItemClick(int position, UserBean bean) {
                Log.i("lfq", "thread status : " + thread.getState());
                if (thread.getState() == Thread.State.RUNNABLE) {
                    thread.handler.sendEmptyMessage(1);
                }
            }
        });
        likeView.notifyDataSetChanged();

        commentView = (CommentsView) findViewById(R.id.commentView);
        commentView.setList(Data.getComments());
        commentView.setOnItemClickListener(new CommentsView.onItemClickListener() {
            @Override
            public void onItemClick(int position, CommentsBean bean) {
                Log.i("lfq", Thread.currentThread().getName());
                thread = new MyThread();
                thread.start();
                Log.i("lfq", "thread status : " + thread.getState());
            }
        });
        commentView.notifyDataSetChanged();
    }

    private class MyThread extends Thread {
        private Handler handler;

        @Override
        public void run() {
            // 方式一， 本质还是子线程，不能用来操作 UI ,
            Looper.prepare();
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Log.i("lfq", msg.what + " , mas.what");
                    if (msg.what == 1) {
                        Log.i("lfq", msg.what + " , mas.what");
                        Toast.makeText(CommentsActivity.this, Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
//                        commentView.setVisibility(View.GONE);
                    }
                }
            };

            Looper.loop();

            // 方式二， 作用于 主线程。可以操作 UI
//            handler = new Handler(getMainLooper()) {
//                @Override
//                public void handleMessage(Message msg) {
//                    Log.i("lfq", msg.what + " , mas.what");
//                    if (msg.what == 1) {
//                        Log.i("lfq", msg.what + " , mas.what");
//                        Toast.makeText(CommentsActivity.this, Thread.currentThread().getName(), Toast.LENGTH_SHORT).show();
//                        commentView.setVisibility(View.GONE);
//                    }
//                }
//            };

        }
    }
}
