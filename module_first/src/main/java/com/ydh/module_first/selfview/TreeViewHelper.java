package com.ydh.module_first.selfview;

import com.ydh.module_first.bean.Tree;
import com.ydh.module_first.bean.TreeBean;

import java.util.List;

public class TreeViewHelper {
    Tree dataResurce;

    public void init(TreeView mTreeView, TreeBean treeBean) {
        dataResurce = treeBean.getData();
        //  dataResurce.level_id = 1;
        dataResurce.topPoint.y = mTreeView.mTopBottom;
        dataResurce.bottomPoint.y = dataResurce.topPoint.y + mTreeView.mCardY;
        setLevel(mTreeView, dataResurce);
        setLastItemValueY(dataResurce);
      /* setAllItemValueY(dataResurce);
        mHVScrollView.scrollTo((int) dataResurce.center.x, (int) dataResurce.center.y);
        drawMap(dataResurce);*/
    }

    private void setLevel(TreeView mTreeView, Tree model) {
        if (model.getChilds() != null && model.getChilds().size() > 0) {
            for (int i = 0; i < model.getChilds().size(); i++) {
                List<Tree> childs = model.getChilds();
                Tree childsBean = childs.get(i);
                childsBean.level_id = model.level_id + 1;
                childsBean.topPoint.y = model.bottomPoint.y + mTreeView.mOffSetY;
                childsBean.bottomPoint.y = childsBean.topPoint.y + mTreeView.mCardY;
                if (childsBean.bottomPoint.y + mTreeView.mTopBottom > mTreeView.mMaxY)
                    mTreeView.mMaxY = childsBean.bottomPoint.y + mTreeView.mTopBottom;
                setLevel(mTreeView, childsBean);
            }

        }
    }

    private void setLastItemValueY(Tree model) {
        if (model.getChilds() != null && model.getChilds().size() > 0) {
            for (int i = 0; i < model.getChilds().size(); i++) {
                Tree subTree = model.getChilds().get(i);
                setLastItemValueY(subTree);
            }
        } else {
           /* model.center.y = model.level_id * offSetY;
            model.center.x = preTree.center.x + offSetX;
            model.topPoint.x = preTree.center.x + offSetX;
            maxX = model.bottomPoint.x = preTree.center.x + offSetX;
            //  Log.e("center", model.center.x + "双方都" + model.center.y);
            preTree = model;*/
        }
    }
}
