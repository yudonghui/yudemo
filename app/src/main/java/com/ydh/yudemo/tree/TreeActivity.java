package com.ydh.yudemo.tree;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ydh.yudemo.R;
import com.ydh.yudemo.tree.book.ReadingActivity;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Set;

public class TreeActivity extends AppCompatActivity {
    private TreeView mTreeView;
    int[] frontShadowColors = new int[]{0xfffa4444, 0x00fa4444};//从深到浅   灰色到透明
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
        mTreeView = findViewById(R.id.treeView);
        TextView mTextView=findViewById(R.id.textView);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT,frontShadowColors);
        gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        mTextView.setBackgroundDrawable(gradientDrawable);
        addData();
    }

    public void book(View view) {
        startActivity(new Intent(this, ReadingActivity.class));
    }

    public final static String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOd+Jt7zdzYG/CkPnGTysA191A37FjQ8bznsL8SbqWKoZgceVUYYV8NxXTFzIYnL/ZqFxsgOnu54S4PKh4H5Yjq+GGErQQHm3Io316yL/pERN4cV13dtSil6j2pPEC+x1VvrzVC0U6sB4T/cB/glLkA/Qivn8yo1A4u4O2CmEbULAgMBAAECgYALIx2Zolr85W8iRpb+oFJqA8C8 /3R9BN9RCRTvP5Hxpipqc0IlAizOwVh7dY+Kgp7r3T3ICDQLQwxXBdppHWdlVq1CxbcrC8ub7D9ui3EOLwbHuBIYHg41D6WCqbf3vCohqrAgBR57ISU3RL855jbXRtoqdb2ocnEZuUkENnLIaQJBAPUBVBoH4C1n25jfWmQ/m6A6cb03pDgRcVMQX7gtXQULYWEhfvVn9ML/Ro/hGGWnGQ/5Y+xOErwcYLg9wwKh8+cCQQDx4ZcD6ZUaGlZ1IIcvKrtNBfQ7VDgzAMSreAifR2I2a88eS8rTGNWyjdMj1xcquSC21wFP/ozzImzQ1sPsbdE9AkEA3SOa1nf/VtxkMCKBQvTKsh+uY5xiRB0yTUf9LY78Y7424eXK4xQ2rv6coOcKD054Z5uxHiwF6vYuMn3Ek468RwJABDUcX2EMyutyXY83Ssa/g1N7MB0C7UAyK5lm7P5c4v11GN3QpWNlNDnrVlBDgua9fvC2gSG4afJLWkaiGda/MQJAaW/YOW6puUyW9TgCfiT7ljSOK0/yJwz4LPg8XEJ3UeGzalQw5Vl4vSah9jhn3j9G/ShfZpYLOGe/AulNFtqVnA==";

    private void addData() {
        Bundle params = new Bundle();
        params.putString("familyId", "266");
        RequestParams entity = new RequestParams("http://api.dev.ixungen.cn/glean/getClanTreeTwo");
        Set<String> strings = params.keySet();
        for (String key : strings) {
            entity.addQueryStringParameter(key, String.valueOf(params.get(key)));
        }
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int code = jsonObject.getInt("code");
                    String msg = jsonObject.getString("msg");
                    if (code == 0) {
                        Gson gson = new Gson();
                        TreeBean treeBean = gson.fromJson(result, TreeBean.class);
                        Tree dataResurce = treeBean.getData();
                        if (dataResurce == null) {
                            Intent intent = new Intent();
                            setResult(222, intent);
                            finish();
                            return;
                        }
                        mTreeView.initData(dataResurce);
                    } else {

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
