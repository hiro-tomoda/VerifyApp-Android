package com.yuwa_seisakusho.verifyapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * FCMとのメッセージ管理サービス
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    /**
     * 新しいトークンが生成された場合に呼ばれる処理
     */
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        Log.d("FCM_TEST", s);
    }

    /**
     * FCMからのメッセージを受信した場合に呼ばれる処理
     * アプリが起動している場合のみ（起動していない場合は通知に表示され、タップするとアプリが起動する）
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // トークンを所得
        Log.d("FCM_TEST", FirebaseInstanceId.getInstance().getInstanceId().toString());

        // メッセージタイトル、本文を取得
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            Log.d("FCM_TEST", title + ":" + body);
        }
    }

}
