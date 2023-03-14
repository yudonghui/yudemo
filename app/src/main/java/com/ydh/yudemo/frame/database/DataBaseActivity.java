package com.ydh.yudemo.frame.database;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ydh.yudemo.App;
import com.ydh.yudemo.R;
import com.ydh.yudemo.frame.BaseRvAdapter;
import com.ydh.yudemo.frame.BaseViewHolder;
import com.ydh.yudemo.frame.database.db.UserDbDao;
import com.ydh.yudemo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataBaseActivity extends AppCompatActivity {

    @BindView(R.id.rv_greendao)
    RecyclerView rvGreendao;
    @BindView(R.id.tv_result)
    TextView tvResult;
    private ArrayList<FrameEntity> datas;
    private FrameAdapter mGreenDaoAdapter;
    private UserDbDao userDbDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        ButterKnife.bind(this);
        datas = new ArrayList<>();
        mGreenDaoAdapter = new FrameAdapter(this, datas, R.layout.item_frame);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rvGreendao.setLayoutManager(gridLayoutManager);
        rvGreendao.setAdapter(mGreenDaoAdapter);
        userDbDao = App.getInstance().getDaoSession().getUserDbDao();
        initData();
    }

    private void initData() {
        datas.clear();
        datas.add(new FrameEntity("增加一条数据", "insert_one_greendao"));
        datas.add(new FrameEntity("删除一条数据", "delete_one_greendao"));
        datas.add(new FrameEntity("修改一条数据", "update_one_greendao"));
        datas.add(new FrameEntity("查询数据name", "select_where_greendao"));
        datas.add(new FrameEntity("查询全部数据", "select_all_greendao"));
        datas.add(new FrameEntity("条件删除数据", "delete_where_greendao"));
        datas.add(new FrameEntity("删除所有数据", "delete_all_greendao"));
        datas.add(new FrameEntity("批量添加数据", "insert_list_greendao"));
        mGreenDaoAdapter.notifyDataSetChanged();
    }

    class FrameAdapter extends BaseRvAdapter<FrameEntity> {

        public FrameAdapter(Context context, List<FrameEntity> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        protected void bindData(BaseViewHolder holder, final FrameEntity data, int position) {
            holder.setText(R.id.tv_content_frame, data.getContent());
            holder.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserDb entity;
                    switch (data.getType()) {
                        case "insert_one_greendao":
                            entity = new UserDb(1L, "令狐冲", "22", "岳不群大徒弟，独孤九剑成名，后学习了吸星大法");
                            userDbDao.insertOrReplace(entity);
                            selectAllGreenDao();
                            break;
                        case "delete_one_greendao":
                            userDbDao.deleteByKey(1L);
                            selectAllGreenDao();
                            break;
                        case "update_one_greendao":
                            entity = new UserDb(1L, "令狐冲", "22", "令狐冲杀了岳不群");
                            userDbDao.update(entity);
                            selectAllGreenDao();
                            break;
                        case "select_where_greendao":
                            UserDb load = userDbDao.load(1L);
                            tvResult.setText(load == null ? "无数据" : load.toString());
                            break;
                        case "select_all_greendao":
                            selectAllGreenDao();
                            break;
                        case "delete_where_greendao":
                            userDbDao.deleteByKey(1L);
                            selectAllGreenDao();
                            break;
                        case "delete_all_greendao":
                            userDbDao.deleteAll();
                            selectAllGreenDao();
                            break;
                        case "insert_list_greendao":
                            ArrayList<UserDb> userDbs = new ArrayList<>();
                            UserDb userDb = new UserDb(2L, "东方不败", "26", "葵花宝典");
                            userDbs.add(userDb);
                            userDb = new UserDb(3L, "任我行", "50", "吸星大法");
                            userDbs.add(userDb);
                            userDb = new UserDb(4L, "风清扬", "100", "独孤九剑");
                            userDbs.add(userDb);
                            userDbDao.insertOrReplaceInTx(userDbs);
                            selectAllGreenDao();
                            break;
                    }
                }
            });
        }
    }

    private void selectAllGreenDao() {
        List<UserDb> userDbs = userDbDao.loadAll();
        tvResult.setText(userDbs == null || userDbs.size() == 0 ? "没有查到数据" : userDbs.toString());
    }

    class FrameEntity {
        private String content;
        private String type;

        public FrameEntity(String content, String type) {
            this.content = content;
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
