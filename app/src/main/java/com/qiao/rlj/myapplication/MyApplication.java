package com.qiao.rlj.myapplication;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by renlijie on 16/12/26.
 */

public class MyApplication extends Application{
    public static MyApplication myApplication;

    public static Application getContext(){

        return myApplication;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        Fresco.initialize(this);
    }
}
