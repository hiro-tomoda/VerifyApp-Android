package com.yuwa_seisakusho.verifyapp;


import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

/**
 * Javascriptから呼ばれる関数クラス
 */
public class JavascriptInterface {

    Activity activity;

    // UI操作のためにアクティビティを保持しておく
    public JavascriptInterface(Activity activity) {
        this.activity = activity;
    }

    /**
     * Javascriptから呼ばれる関数
     */
    @android.webkit.JavascriptInterface // このアノテーションが必要
    public String outputTextNative(String param) {
        Log.d("TEST", "ネイティブのメソッドが呼ばれた");

        TextView nativeText = activity.findViewById(R.id.text_native);
        nativeText.setText(param);

        return "ネイティブ側の関数呼び出しに成功しました";
    }


}
