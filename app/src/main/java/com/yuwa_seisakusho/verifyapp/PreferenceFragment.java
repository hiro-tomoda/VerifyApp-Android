package com.yuwa_seisakusho.verifyapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * プリファレンス画面
 */
public class PreferenceFragment extends Fragment {

    MainActivity activity;
    SharedPreferences pref;

    public PreferenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();

        // 設定情報の取得
        pref = PreferenceManager.getDefaultSharedPreferences(activity);

        // レイアウトファイルからビューオブジェクトを生成
        View view = inflater.inflate(R.layout.fragment_preference, container, false);

        // ビューを取得
        final TextView resultText = view.findViewById(R.id.text_result);

        // 保存ボタン押下時の処理定義
        Button saveButton = view.findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // プリファレンスの保存にはEditorクラスを使う
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("key", "Hello UserDefaults!");
                editor.commit();

                resultText.setText("プリファレンスに値を保存しました");
            }
        });

        // 読込ボタン押下時の処理定義
        Button readButton = view.findViewById(R.id.button_read);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText(pref.getString("key", ""));
            }
        });

        // 削除ボタン押下時の処理定義
        Button removeButton = view.findViewById(R.id.button_remove);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // プリファレンスの削除にはEditorクラスを使う
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("key");
//                editor.clear();
                editor.commit();

                resultText.setText("プリファレンスの値を削除しました");
            }
        });

        return view;
    }

}
