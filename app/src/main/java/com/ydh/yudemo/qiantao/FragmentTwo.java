package com.ydh.yudemo.qiantao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ydh.yudemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  HONGDA on 2018/6/19.
 */
public class FragmentTwo extends Fragment {

    private RecyclerView recyclerView;
    private List<String> strDatas;
    private AdapterFragment adapterFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_fragment);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        strDatas = new ArrayList<>();
        adapterFragment = new AdapterFragment(getContext(), strDatas);
        recyclerView.setAdapter(adapterFragment);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        initData();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            strDatas.add("item  " + i);
        }
        adapterFragment.notifyDataSetChanged();
    }

}
