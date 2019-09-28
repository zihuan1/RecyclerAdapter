package com.zihuan.baseadapter;

import android.app.Application;

/**
 * @author zihuan
 * @Description
 * @date 2019/9/28 12:11
 */
public class MainApplication extends Application {
  public static MainApplication mainApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;
    }
}
