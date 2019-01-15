package com.yuwa_seisakusho.verifyapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * WebAPI検証画面
 */
public class WebApiFragment extends Fragment {

    MainActivity activity;


    // WebAPIのURL 拡張子なしにしようとしたらapacheのhtaccessの設定が必要と思われる
    private final String API_URL = "http://133.167.108.164/api/verify/aisatu.php";

    // HTTPメソッド
    private final String GET_METHOD = "GET";
    private final String POST_METHOD = "POST";


    public WebApiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();

        // レイアウトファイルからビューオブジェクトを生成
        View view = inflater.inflate(R.layout.fragment_web_api, container, false);
        final TextView resultText = view.findViewById(R.id.text_result);

        // GETボタン定義
        Button getButton = view.findViewById(R.id.button_get);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 非同期処理を実行
                AsyncNetworkTask task = new AsyncNetworkTask();
                task.setCallback(new AsyncNetworkTask.Callback() {
                    @Override
                    public void onSuccess(String json) {
                        try {
                            // JSONパース
                            JSONObject jsonObject = new JSONObject(json);
                            String result = jsonObject.getString("result");
                            resultText.setText(result);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                String queryParam = "name=共田";
                task.execute(API_URL + "?" + queryParam, GET_METHOD, null);

            }
        });

        // POSTボタン定義
        Button postButton = view.findViewById(R.id.button_post);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 非同期処理を実行
                AsyncNetworkTask task = new AsyncNetworkTask();
                task.setCallback(new AsyncNetworkTask.Callback() {
                    @Override
                    public void onSuccess(String json) {
                        try {
                            // JSONパース
                            JSONObject jsonObject = new JSONObject(json);
                            String result = jsonObject.getString("result");
                            resultText.setText(result);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", "共田");
                    task.execute(API_URL, POST_METHOD, jsonObject.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

}
