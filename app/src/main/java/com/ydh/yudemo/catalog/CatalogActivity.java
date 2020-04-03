package com.ydh.yudemo.catalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.ydh.yudemo.R;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {
    private TreeRecyclerAdapter mAdapter;
    int num = 0;
    protected List<Node> mDatas = new ArrayList<Node>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        initDatas();
        RecyclerView mTree = (RecyclerView) findViewById(R.id.recyclerView);
        mTree.setLayoutManager(new LinearLayoutManager(this));
        //第一个参数  RecyclerView
        //第二个参数  上下文
        //第三个参数  数据集
        //第四个参数  默认展开层级数 0为不展开
        //第五个参数  展开的图标
        //第六个参数  闭合的图标
        mAdapter = new SimpleTreeRecyclerAdapter(mTree, this,
                mDatas, 1, R.mipmap.tree_ex, R.mipmap.tree_ec);
        mTree.setAdapter(mAdapter);
    }

    /**
     * 添加数据
     *
     * @param v
     */
    public void click(View v) {

        List<Node> mlist = new ArrayList<>();
        if (num == 0)
            mlist.add(new Node("22", "0", "我是添加的root", new FileNode()));
        //
        //        //添加一个根节点
        //        mlist.add(new Node("223","0","我也是添加的root节点",new FileNode()));
        //
        //        //加在新节点上
        mlist.add(new Node("333" + num, "22", "我是添加的1" + num));
        mlist.add(new Node("44444" + num, "22", "我是添加的2" + num));
        //加到现有数据的父节点上
        mlist.add(new Node("444454" + num, "1", "我是添加的3" + num));

        num++;
        mAdapter.addData(0, mlist);
    }

    /**
     * 显示选中数据
     */
    public void clickShow(View v) {
        StringBuilder sb = new StringBuilder();
        final List<Node> allNodes = mAdapter.getAllNodes();
        for (int i = 0; i < allNodes.size(); i++) {
            if (allNodes.get(i).isChecked()) {
                sb.append(allNodes.get(i).getName() + ",");
            }
        }
        String strNodesName = sb.toString();
        if (!TextUtils.isEmpty(strNodesName))
            Toast.makeText(this, strNodesName.substring(0, strNodesName.length() - 1), Toast.LENGTH_SHORT).show();
    }

    public void delete(View view) {
        if (mAdapter.getAllNodes().size() > 0) {
            mAdapter.removeData(mAdapter.getAllNodes().get(mAdapter.getAllNodes().size() - 1));
        }
    }

    private void initDatas() {
        // id , pid , label , 其他属性
        mDatas.add(new Node("1", "-1", "文件管理系统"));

        mDatas.add(new Node(2 + "", 1 + "", "游戏"));
        mDatas.add(new Node(3 + "", 1 + "", "文档"));
        mDatas.add(new Node(4 + "", 1 + "", "程序"));
        mDatas.add(new Node(5 + "", 2 + "", "war3"));
        mDatas.add(new Node(6 + "", 2 + "", "刀塔传奇"));

        mDatas.add(new Node(7 + "", 4 + "", "面向对象"));
        mDatas.add(new Node(8 + "", 4 + "", "非面向对象"));

        mDatas.add(new Node(9 + "", 7 + "", "C++"));
        mDatas.add(new Node(10 + "", 7 + "", "JAVA"));
        mDatas.add(new Node(11 + "", 7 + "", "Javascript"));
        mDatas.add(new Node(12 + "", 8 + "", "C"));
        mDatas.add(new Node(13 + "", 12 + "", "C"));
        mDatas.add(new Node(14 + "", 13 + "", "C"));
        mDatas.add(new Node(15 + "", 14 + "", "C"));
        mDatas.add(new Node(16 + "", 15 + "", "C"));
    }
}
