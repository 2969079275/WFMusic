package com.weifeng.wfmusic;

import android.app.Application;
import android.content.Context;

import com.tencent.mmkv.MMKV;

public class App extends Application {
    private static App mApp;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        mContext = getApplicationContext();
        MMKV.initialize(this);
    }
}
