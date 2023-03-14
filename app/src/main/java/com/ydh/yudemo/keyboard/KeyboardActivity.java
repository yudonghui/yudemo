package com.ydh.yudemo.keyboard;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ydh.yudemo.DisplayUtil;
import com.ydh.yudemo.R;

import java.util.ArrayList;

public class KeyboardActivity extends AppCompatActivity {
    private Context mContext;
    private ListView mListView;
    private ArrayList<String> mDataList = new ArrayList<>();
    private LvAdapter mLvAdapter;


    /*
    * 自定义键盘以及键盘上面需要的输入框
    * */
    private LinearLayout mLl_keybard;
    private View mKeyboard_bg;
    private EditText mEdit_buy_times;
    private TextView mMoney;
    private ImageView mCancel;
    private com.ydh.yudemo.keyboard.MyKeyBoardView mKeyboard_view;
    private KeyboardUtil mKeyboardUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        mContext = this;
        initView();
        initKeyView();//自定义键盘的相关控件
        mKeyboardUtil = new KeyboardUtil(this, mKeyboard_view);//自定义键盘的工具类
        mLvAdapter = new LvAdapter(mDataList, ClickListener);
        mListView.setAdapter(mLvAdapter);
        addListener();
        addData();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
    }

    private void initKeyView() {
        mLl_keybard = (LinearLayout) findViewById(R.id.ll_keybard);
        mKeyboard_bg = (View) findViewById(R.id.keyboard_bg);
        mEdit_buy_times = (EditText) findViewById(R.id.edit_buy_times);
        mMoney = (TextView) findViewById(R.id.money);
        mCancel = (ImageView) findViewById(R.id.cancel);
        mKeyboard_view = (com.ydh.yudemo.keyboard.MyKeyBoardView) findViewById(R.id.keyboard_view);
    }

    private void addListener() {
        mEdit_buy_times.addTextChangedListener(TextWatcherListener);
        mKeyboard_bg.setOnClickListener(KeyBoardBgListener);
        mEdit_buy_times.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mKeyboardUtil.attachTo(mEdit_buy_times);
                return false;
            }
        });

    }

    private void addData() {
        for (int i = 0; i < 50; i++) {
            mDataList.add("点击弹出键盘" + i);
        }
        mLvAdapter.notifyDataSetChanged();
    }

    ClickInterface ClickListener = new ClickInterface() {
        @Override
        public void callBack(int position) {
            mLl_keybard.setVisibility(View.VISIBLE);
            View childAt = mListView.getChildAt(position-mListView.getFirstVisiblePosition());
            int[] childLocation = new int[2];
            childAt.getLocationOnScreen(childLocation);
            int y = childLocation[1] + DisplayUtil.getStatusBarHeight(mContext);
            int displayHeight = DisplayUtil.getDisplayHeight(mContext);
            ver = displayHeight - y - DisplayUtil.dip2px(mContext, 100 + 230);
            Log.e("距离：", ver + "");
            if (ver < 0)
                mListView.scrollBy(0, -ver);


            mMoney.setText(2 * mMulti + "元");
            mEdit_buy_times.setText(mMulti + "");
            mEdit_buy_times.setSelection((mMulti + "").length());
            mKeyboardUtil.attachTo(mEdit_buy_times);
            mEdit_buy_times.setFocusable(true);
            mEdit_buy_times.setFocusableInTouchMode(true);
            mEdit_buy_times.requestFocus();
            mCancel.setOnClickListener(CancelListener);
            mKeyboardUtil.setOnOkClick(OkListener);
        }
    };
    private int mMulti;
    TextWatcher TextWatcherListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int length = String.valueOf(s).length();
            if (length == 0) {
                mMulti = 0;
            } else {
                mMulti = Integer.parseInt(String.valueOf(s));
            }
            mMoney.setText(mMulti * 2 + "元");
        }
    };
    View.OnClickListener CancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hintKey();
        }
    };
    KeyboardUtil.OnOkClick OkListener = new KeyboardUtil.OnOkClick() {
        @Override
        public void onOkClick() {
            Toast.makeText(mContext, "点击跟单", Toast.LENGTH_SHORT).show();
        }
    };
    View.OnClickListener KeyBoardBgListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hintKey();
        }
    };
    private int ver;

    private void hintKey() {
        mLl_keybard.setVisibility(View.GONE);
        mKeyboardUtil.hideKeyboard();
        if (ver < 0)
            mListView.scrollBy(0, ver);
    }

    class LvAdapter extends BaseAdapter {
        ArrayList<String> mDataList;
        ClickInterface ClickListener;

        public LvAdapter(ArrayList<String> mDataList, ClickInterface ClickListener) {
            this.mDataList = mDataList;
            this.ClickListener = ClickListener;
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mViewHolder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_keyboard, null);
                mViewHolder = new ViewHolder();
                mViewHolder.mTextView = (TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }
            mViewHolder.mTextView.setText(mDataList.get(position));
            mViewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickListener.callBack(position);
                }
            });
            return convertView;
        }
    }

    class ViewHolder {
        private TextView mTextView;
    }

    public interface ClickInterface {
        void callBack(int position);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mKeyboardUtil != null && mLl_keybard.getVisibility() == View.VISIBLE) {
            hintKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
