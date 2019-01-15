package com.ydh.yudemo.retrofitrxjava.dagger;

import com.ydh.yudemo.utils.LogUtils;

import dagger.Module;
import dagger.Provides;

@Module
public class PrivderDemo {
    @Provides
    public DaggerDemo provideDaggerDemo() {
        LogUtils.error("走了provideDaggerDemo");
        return new DaggerDemo("laile");
    }
}
