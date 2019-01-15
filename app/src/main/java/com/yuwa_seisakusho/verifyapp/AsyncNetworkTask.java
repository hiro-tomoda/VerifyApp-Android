package com.yuwa_seisakusho.verifyapp;

import android.os.AsyncTask;
import android.util.Log;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 非同期通信クラス
 */
public class AsyncNetworkTask extends AsyncTask {

    /**
     * コールバックインタフェース定義
     */
    interface Callback {
        void onSuccess(String json);
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    /**
     * コンストラクタ
     */
    public AsyncNetworkTask() {
        super();
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        String result = null;

        try {
            // パラメータ取得
            String url = (String) objects[0];
            String method = (String) objects[1];
            String jsonString = (String) objects[2];

            // HTTPクライアント設定
            OkHttpClient client = new OkHttpClient();
            Request request = null;
            switch (method) {
                case "GET":
                    request = new Request.Builder().url(url).build();
                    break;

                case "POST":
                    MediaType mediaType = MediaType.parse("application/json");
                    RequestBody requestBody = RequestBody.create(mediaType, jsonString);
                    request = new Request.Builder().url(url).post(requestBody).build();
                    break;

                default:
                    break;
            }

            // WebAPI呼び出し
            Call call = client.newCall(request);
            Response response = call.execute();
            result = response.body().string();

            Log.d("TEST", result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(Object o) {
        // コールバック呼び出し
        callback.onSuccess((String) o);
    }
}
