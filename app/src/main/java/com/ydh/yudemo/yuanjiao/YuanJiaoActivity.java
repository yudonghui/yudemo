package com.ydh.yudemo.yuanjiao;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.DisplayUtil;
import com.ydh.yudemo.R;

public class YuanJiaoActivity extends BaseActivity {
   private ImageView mImageView;

    @Override
    public int getInflateId() {
        return R.layout.activity_yuan_jiao;
    }

    @Override
    public void initView() {
        mImageView = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public void addListener() {

    }

    @Override
    public void addData() {
        String postPictureUrl = "http://img0.imgtn.bdimg.com/it/u=1223347380,2741136198&fm=26&gp=0.jpg";
        setRoundedImage(postPictureUrl,
                DisplayUtil.dip2px(mContext,10),
                FlexibleRoundedBitmapDisplayer.CORNER_TOP_LEFT | FlexibleRoundedBitmapDisplayer.CORNER_BOTTOM_LEFT,
                R.mipmap.banner, mImageView);
    }

    public void setRoundedImage(String url, int cornerRadius, int corners, int defaultImage, ImageView imageView) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext).imageDownloader(
                new BaseImageDownloader(mContext, 60 * 1000, 60 * 1000)) // connectTimeout超时时间
                .build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultImage).showStubImage(defaultImage)
                .showImageForEmptyUri(defaultImage)//url为空时显示的图片
                .showImageOnFail(defaultImage)//加载失败显示的图片
                .cacheInMemory()//内存缓存
                .cacheOnDisc()//磁盘缓存
                .displayer(new FlexibleRoundedBitmapDisplayer(cornerRadius, corners)) // 自定义增强型BitmapDisplayer
                .build();
        imageLoader.displayImage(url, imageView, options);

    }
}
