package com.example.administrator.test;

import android.app.Application;
import android.os.Environment;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class MyAppcation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("main.db").build();
        Realm.setDefaultConfiguration(configuration);
    }
}
