package com.yuwa_seisakusho.verifyapp;

import android.app.Application;

import io.realm.Realm;

/**
 * アプリケーションクラス
 */
public class VerifyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Realmの初期設定
        Realm.init(this);
    }

}
