package com.yuwa_seisakusho.verifyapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * メインアクティビティ
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // メニュー画面を表示する
        MenuFragment menuFragment = new MenuFragment();
        setFragment(menuFragment, false);
    }

    /**
     * フラグメントをフレームにセット
     */
    public void setFragment(Fragment fragment, boolean isBackStack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        if (isBackStack) {
            // フラグメントの状態をバックスタックに登録する
            // メニュー画面でバックキーが押された場合はアプリ終了、それ以外の画面では前画面に戻るようにするため
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }


    /**
     * バックキーが押された場合の処理
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
