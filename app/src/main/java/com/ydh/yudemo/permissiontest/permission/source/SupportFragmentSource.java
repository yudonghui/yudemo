/*
 * Copyright © Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ydh.yudemo.permissiontest.permission.source;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;

/**
 * <p>androidx.fragment.app.Fragment Wrapper.</p>
 * Created by Yan Zhenjie on 2017/5/1.
 */
public class SupportFragmentSource extends Source {

    private Fragment mFragment;

    public SupportFragmentSource(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Override
    public Context getContext() {
        return mFragment.getContext();
    }

    @Override
    public void startActivity(Intent intent) {
        mFragment.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        mFragment.startActivityForResult(intent, requestCode);
    }
}
