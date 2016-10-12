package com.example.andy.hotfix;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.example.andy.rocoofor.RocooManager;

/**
 * Created by liuqiangbin on 2016/10/11.
 */

public class MyApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        RocooManager.getInstance(Environment.getExternalStorageDirectory().getAbsolutePath(),"patch.jar").init(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
