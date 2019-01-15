package com.ydh.yudemo.retrofitrxjava.dagger;

import dagger.Component;

@Component(modules = PrivderDemo.class)
public interface UserCompoent {
    void inject(DaggerActivity activity);
}
